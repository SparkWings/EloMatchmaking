package org.jbltd.matchmaking.commands.party;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jbltd.matchmaking.QueueManager;
import org.jbltd.matchmaking.TeamManager;
import org.jbltd.matchmaking.util.F;
import org.jbltd.matchmaking.util.Team;

public class PartyLeave implements CommandExecutor {

    private TeamManager manager;
    private QueueManager qmanager;

    public PartyLeave(TeamManager manager, QueueManager qmanager) {

	this.manager = manager;
	this.qmanager = qmanager;

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

	    if (t.getTeamPlayers().size() < 2) {
		t.removePlayer(player);
		manager.AllTeams.remove(t);
		player.sendMessage(F.info("Teams", "Your team has been dissolved due to a lack of players.", false));

		if (qmanager.TeamQueue.contains(t)) {
		    qmanager.TeamQueue.remove(t);
		}

		return false;
	    }

	    if (t.getTeamLeader() == player) {
		t.getTeamPlayers()
			.forEach(
				p -> Bukkit.getPlayer(p)
					.sendMessage(F.info(
						"Teams", "The team leader has left the team, "
							+ Bukkit.getPlayer(t.getTeamPlayers().get(1)).getName() + " has taken control of the team.",
						false)));
	    }

	    t.removePlayer(player);

	    player.sendMessage(F.info("Teams", "You have left your team.", false));

	    if (qmanager.TeamQueue.contains(t)) {
		qmanager.TeamQueue.remove(t);
	    }

	}

	return false;
    }

}
