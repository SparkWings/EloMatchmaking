package org.jbltd.matchmaking.listeners;

import java.util.Random;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jbltd.matchmaking.PlayerManager;
import org.jbltd.matchmaking.QueueManager;
import org.jbltd.matchmaking.TeamManager;
import org.jbltd.matchmaking.util.F;
import org.jbltd.matchmaking.util.PlayerData;
import org.jbltd.matchmaking.util.UtilPing;

public class Basic implements Listener {

    private QueueManager manager;
    private PlayerManager pmanager;
    private TeamManager tmanager;

    public Basic(QueueManager manager, PlayerManager pmanager, TeamManager tmanager) {
	this.manager = manager;
	this.pmanager = pmanager;
	this.tmanager = tmanager;

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

	e.setJoinMessage(F.GRAY + "<" + F.GREEN + "+" + F.GRAY + ">" + F.DARK_GRAY + e.getPlayer().getName());

	PlayerData d = new PlayerData(e.getPlayer(), UtilPing.getPing(e.getPlayer()) + 1,
		new Random().nextInt(2000 - 1000) + 1000);
	pmanager.MasterData.put(e.getPlayer().getUniqueId(), d);

    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {

	if (manager.SoloQueue.contains(pmanager.MasterData.get(e.getPlayer().getUniqueId()))) {
	    manager.SoloQueue.remove(pmanager.MasterData.get(e.getPlayer().getUniqueId()));
	}

	if (pmanager.MasterData.containsKey(e.getPlayer().getUniqueId())) {
	    pmanager.MasterData.remove(e.getPlayer().getUniqueId());
	}

	if (tmanager.MasterTeamPlayerData.contains(e.getPlayer().getUniqueId())) {
	    tmanager.MasterTeamPlayerData.remove(e.getPlayer().getUniqueId());
	}

    }

}
