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

    /**
     * TODO 
     * More dependency injection
     * Less static
     * Team queuing by team size
     */

    public void onEnable() {

	QueueManager q = new QueueManager();
	PlayerManager p = new PlayerManager();
	TeamManager t = new TeamManager();

	getCommand("tcreate").setExecutor(new PartyCreate(t));
	getCommand("tinvite").setExecutor(new PartyInvite(t));
	getCommand("accept").setExecutor(new Accept());
	getCommand("queue").setExecutor(new Queue(q, p, t));

	getServer().getPluginManager().registerEvents(new QueueManager(), this);
	getServer().getPluginManager().registerEvents(new Basic(q, p, t), this);

	new UpdateTask(this);

	F.log("Plugin", "Enabled");
    }

    public void onDisable() {

	F.log("Plugin", "Disabling");
    }

}
