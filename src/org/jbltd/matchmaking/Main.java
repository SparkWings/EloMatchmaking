package org.jbltd.matchmaking;

import org.bukkit.plugin.java.JavaPlugin;
import org.jbltd.matchmaking.commands.LeaveQueue;
import org.jbltd.matchmaking.commands.Queue;
import org.jbltd.matchmaking.commands.party.Accept;
import org.jbltd.matchmaking.commands.party.PartyCreate;
import org.jbltd.matchmaking.commands.party.PartyInvite;
import org.jbltd.matchmaking.commands.party.PartyLeave;
import org.jbltd.matchmaking.listeners.Basic;
import org.jbltd.matchmaking.util.F;
import org.jbltd.matchmaking.util.Team;
import org.jbltd.matchmaking.util.UpdateTask;

public class Main extends JavaPlugin {

    public void onEnable() {

	// Managers
	QueueManager q = new QueueManager();
	PlayerManager p = new PlayerManager();
	TeamManager t = new TeamManager();
	new Team(t);

	// Call update task to listen to UpdateEvents
	new UpdateTask(this);

	// Commands
	getCommand("tcreate").setExecutor(new PartyCreate(t));
	getCommand("tinvite").setExecutor(new PartyInvite(t));
	getCommand("tleave").setExecutor(new PartyLeave(t, q));
	getCommand("accept").setExecutor(new Accept());
	getCommand("queue").setExecutor(new Queue(q, p, t));
	getCommand("qleave").setExecutor(new LeaveQueue(q, p));

	// Listeners
	getServer().getPluginManager().registerEvents(q, this);
	getServer().getPluginManager().registerEvents(new Basic(q, p, t), this);

	F.log("Plugin", "Enabled");

    }

}
