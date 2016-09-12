package org.jbltd.matchmaking.commands.party;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jbltd.matchmaking.TeamManager;
import org.jbltd.matchmaking.util.F;
import org.jbltd.matchmaking.util.Team;

public class PartyCreate implements CommandExecutor {

    private TeamManager manager;

    public PartyCreate(TeamManager manager) {

	this.manager = manager;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

	if (!(sender instanceof Player))
	    return true;

	Player player = (Player) sender;

	if (cmd.getName().equalsIgnoreCase("tcreate")) {
	    if (manager.MasterTeamPlayerData.contains(player.getUniqueId())) {
		player.sendMessage(F.error("Teams", "You are already on a team.", false));
		return true;
	    }

	    //For demonstration purposes, the team is given a random elo between 1000 and 2000
	    Team team = new Team(new ArrayList<UUID>(), new Random().nextInt(2000 - 1000) + 1000);
	    team.addPlayer(player);
	    manager.AllTeams.add(team);


	    player.sendMessage(F.info("Team", "You created a team. Use " + F.GOLD + "/tinvite <player>" + F.GREEN
		    + " to invite your friends (Max 5).", false));

	}

	return false;
    }

}
