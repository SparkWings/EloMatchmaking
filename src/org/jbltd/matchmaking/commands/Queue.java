package org.jbltd.matchmaking.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jbltd.matchmaking.PlayerData;
import org.jbltd.matchmaking.QueueManager;
import org.jbltd.matchmaking.util.F;

public class Queue implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

	if (!(sender instanceof Player))
	    return true;

	Player player = (Player) sender;

	if (cmd.getName().equalsIgnoreCase("queue")) {

	    if (!QueueManager.SoloQueue.contains(PlayerData.MasterData.get(player.getUniqueId()))) {
		QueueManager.SoloQueue.add(PlayerData.MasterData.get(player.getUniqueId()));
		player.sendMessage(F.info("Queue", "Successfully joined the Solo Queue", false));
	    }

	}

	return false;
    }

}
