package org.jbltd.matchmaking.commands.party;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jbltd.matchmaking.TeamManager;
import org.jbltd.matchmaking.util.F;
import org.jbltd.matchmaking.util.Team;

public class PartyLeave implements CommandExecutor {

    private TeamManager manager;
    
    public PartyLeave(TeamManager manager)
    {
	
	this.manager = manager;
	
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

	if (!(sender instanceof Player))
	    return true;

	Player player = (Player) sender;

	if (cmd.getName().equalsIgnoreCase("tleave")) {

	    if (!manager.MasterTeamPlayerData.contains(player.getUniqueId())) {

		player.sendMessage(F.error("Teams", "You are not on a team.", false));

	    }
	    
	    Team t = Team.searchTeamByPlayer(player);
	    t.removePlayer(player);
	    
	    player.sendMessage(F.info("Teams", "You have left your team.", false));
	    
	    	    

	}

	return false;
    }

}
