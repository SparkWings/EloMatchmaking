package org.jbltd.matchmaking.listeners;

import java.util.Random;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jbltd.matchmaking.PlayerData;
import org.jbltd.matchmaking.QueueManager;
import org.jbltd.matchmaking.util.F;
import org.jbltd.matchmaking.util.Team;
import org.jbltd.matchmaking.util.UtilPing;

public class Basic implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

	e.setJoinMessage(F.GRAY + "<" + F.GREEN + "+" + F.GRAY + ">" + F.DARK_GRAY + e.getPlayer().getName());
	
	PlayerData d = new PlayerData(e.getPlayer(), UtilPing.getPing(e.getPlayer()) + 1, new Random().nextInt(2000 - 1000) + 1000);
	PlayerData.MasterData.put(e.getPlayer().getUniqueId(), d);
	

    }
    
    @EventHandler
    public void onLeave(PlayerQuitEvent e)
    {
	
	if(QueueManager.SoloQueue.contains(PlayerData.MasterData.get(e.getPlayer().getUniqueId())))
	{
	    QueueManager.SoloQueue.remove(PlayerData.MasterData.get(e.getPlayer().getUniqueId()));
	}
	
	if(PlayerData.MasterData.containsKey(e.getPlayer().getUniqueId()))
	{
	    PlayerData.MasterData.remove(e.getPlayer().getUniqueId());
	}
	
	if(Team.MasterTeams.contains(e.getPlayer().getUniqueId()))
	{
	    Team.MasterTeams.remove(e.getPlayer().getUniqueId());
	}
	
    }
    

}
