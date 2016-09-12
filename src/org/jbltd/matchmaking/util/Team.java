package org.jbltd.matchmaking.util;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jbltd.matchmaking.TeamManager;

public class Team {

    private UUID _teamUID;
    private ArrayList<UUID> _players;

    private static TeamManager manager;

    public Team(UUID teamUID, ArrayList<UUID> players) {

	this._teamUID = teamUID;
	this._players = players;

    }

    public static TeamManager getInstance() {
	if (manager == null) {
	    manager = new TeamManager();
	}
	return manager;
    }

    public ArrayList<UUID> getTeamPlayers() {
	return _players;
    }

    public UUID getTeamUniqueId()
    {
	return _teamUID;
    }
    
    public void addPlayer(Player player) {
	_players.add(player.getUniqueId());
	manager.MasterTeamPlayerData.add(player.getUniqueId());
    }

    public void addPlayer(UUID uuid) {
	_players.add(uuid);
	manager.MasterTeamPlayerData.add(uuid);
    }

    public void removePlayer(Player player) {

	_players.remove(player.getUniqueId());
	manager.MasterTeamPlayerData.remove(player.getUniqueId());

    }

    public void removePlayer(UUID uuid) {

	_players.remove(uuid);
	manager.MasterTeamPlayerData.remove(uuid);

    }

    public static Team searchTeamByPlayer(Player player) {

	for (Team t : getInstance().AllTeams) {
	    ArrayList<UUID> data = t.getTeamPlayers();

	    if (data.contains(player.getUniqueId())) {
		return t;
	    } else
		continue;
	}

	return null;
    }

    public double getTeamAveragePing() {

	ArrayList<Integer> pings = new ArrayList<>();

	for (UUID u : getTeamPlayers()) {
	    Player p = Bukkit.getPlayer(u);
	    double ping = UtilPing.getPing(p);
	    pings.add((int) ping);
	}

	return UtilMath.average(pings);

    }

    public Player getTeamLeader() {
	return Bukkit.getPlayer(getTeamPlayers().get(0));
    }

}
