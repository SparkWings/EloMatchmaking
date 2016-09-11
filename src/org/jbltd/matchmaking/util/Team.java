package org.jbltd.matchmaking.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Team {

    private ArrayList<UUID> _players;
    
    public static List<Team> AllTeams = new ArrayList<>();
    public static List<UUID> MasterTeams = new ArrayList<>();
    
    public Team(ArrayList<UUID> players)
    {
	
	this._players = players;
	
    }
    
    public ArrayList<UUID> getTeamPlayers()
    {
	return _players;
    }
    
    public void addPlayer(Player player)
    {
	_players.add(player.getUniqueId());
    }
    
    public void addPlayer(UUID uuid)
    {
	_players.add(uuid);
    }
    
    public static Team searchTeamByPlayer(Player player)
    {
	
	for(Team t : AllTeams)
	{
	    ArrayList<UUID> data = t.getTeamPlayers();
	    
	    if(data.contains(player.getUniqueId()))
	    {
		return t;
	    }
	    else continue;
	}
	
	return null;
    }
    
    public double getTeamAveragePing()
    {
	
	ArrayList<Integer> pings = new ArrayList<>();
	
	for(UUID u : getTeamPlayers())
	{
	    Player p = Bukkit.getPlayer(u);
	    double ping = UtilPing.getPing(p);
	    pings.add((int) ping);
	}
	
	return UtilMath.average(pings);
	
    }
    
}
