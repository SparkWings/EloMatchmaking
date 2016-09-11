package org.jbltd.matchmaking;

import org.bukkit.plugin.java.JavaPlugin;
import org.jbltd.matchmaking.commands.Queue;
import org.jbltd.matchmaking.commands.party.Accept;
import org.jbltd.matchmaking.commands.party.PartyCreate;
import org.jbltd.matchmaking.commands.party.PartyInvite;
import org.jbltd.matchmaking.listeners.Basic;
import org.jbltd.matchmaking.util.F;
import org.jbltd.matchmaking.util.UpdateTask;

public class Main extends JavaPlugin {

    public void onEnable() {

	getCommand("tcreate").setExecutor(new PartyCreate());
	getCommand("tinvite").setExecutor(new PartyInvite());
	getCommand("accept").setExecutor(new Accept());
	getCommand("queue").setExecutor(new Queue());

	getServer().getPluginManager().registerEvents(new QueueManager(), this);
	getServer().getPluginManager().registerEvents(new Basic(), this);

	new UpdateTask(this);

	F.log("Plugin", "Enabled");
    }

    public void onDisable() {

	F.log("Plugin", "Disabling");
    }

}
