package org.jbltd.matchmaking.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jbltd.matchmaking.PlayerManager;
import org.jbltd.matchmaking.QueueManager;
import org.jbltd.matchmaking.TeamManager;
import org.jbltd.matchmaking.util.F;
import org.jbltd.matchmaking.util.Team;

public class Queue implements CommandExecutor {

    private QueueManager manager;
    private PlayerManager pmanager;
    private TeamManager tmanager;

    public Queue(QueueManager manager, PlayerManager pmanager, TeamManager tmanager) {
	this.manager = manager;
	this.pmanager = pmanager;
	this.tmanager = tmanager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

	if (!(sender instanceof Player))
	    return true;

	Player player = (Player) sender;

	if (cmd.getName().equalsIgnoreCase("queue")) {

	    if (!manager.SoloQueue.contains(pmanager.MasterData.get(player.getUniqueId()))) {

		if (tmanager.MasterTeamPlayerData.contains(player.getUniqueId())) {
		    Team t = Team.searchTeamByPlayer(player);
		    if (t.getTeamLeader() != player) {
			player.sendMessage(F.error("Queue", "Only the team leader can queue for a game.", false));
			return true;
		    }

		    manager.TeamQueue.add(t);
		    t.getTeamPlayers().forEach(p -> Bukkit.getPlayer(p)
			    .sendMessage(F.info("Queue", "Your team leader has added you to the Team Queue.", false)));
		    return false;

		}

		manager.SoloQueue.add(pmanager.MasterData.get(player.getUniqueId()));
		player.sendMessage(F.info("Queue", "Successfully joined the Solo Queue", false));
	    }

	}

	return false;
    }

}
