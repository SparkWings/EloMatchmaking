package org.jbltd.matchmaking.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jbltd.matchmaking.PlayerManager;
import org.jbltd.matchmaking.QueueManager;
import org.jbltd.matchmaking.util.F;

public class LeaveQueue implements CommandExecutor {

    private QueueManager manager;
    private PlayerManager pmanager;

    public LeaveQueue(QueueManager manager, PlayerManager pmanager) {

	this.manager = manager;
	this.pmanager = pmanager;

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

	if (!(sender instanceof Player))
	    return true;

	Player player = (Player) sender;

	if (cmd.getName().equalsIgnoreCase("qleave")) {
	    if (!manager.SoloQueue.contains(pmanager.MasterData.get(player.getUniqueId()))) {

		player.sendMessage(F.error("Queue", "You are not in the Solo Queue.", false));
		return true;

	    }

	    manager.SoloQueue.remove(pmanager.MasterData.get(player.getUniqueId()));

	}

	return false;
    }

}
