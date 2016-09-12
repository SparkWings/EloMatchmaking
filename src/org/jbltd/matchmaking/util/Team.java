package org.jbltd.matchmaking.util;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jbltd.matchmaking.TeamManager;

public class Team {

    private ArrayList<UUID> _players;
    private int _elo;

    private static TeamManager manager;

    public Team(TeamManager manager) {
	Team.manager = manager;
    }

    public Team(ArrayList<UUID> players, int elo) {

	this._players = players;
	this._elo = elo;

    }

    private static TeamManager getInstance() {

	return manager;
    }

    public ArrayList<UUID> getTeamPlayers() {
	return _players;
    }

    public int getTeamElo() {
	return _elo;
    }

    public void addPlayer(Player player) {
	_players.add(player.getUniqueId());
	getInstance().MasterTeamPlayerData.add(player.getUniqueId());
    }

    public void addPlayer(UUID uuid) {
	_players.add(uuid);
	getInstance().MasterTeamPlayerData.add(uuid);
    }

    public void removePlayer(Player player) {

	_players.remove(player.getUniqueId());
	getInstance().MasterTeamPlayerData.remove(player.getUniqueId());

    }

    public void removePlayer(UUID uuid) {

	_players.remove(uuid);
	getInstance().MasterTeamPlayerData.remove(uuid);

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

    public double getTeamCombatValue() {

	double phaseOne = getTeamPlayers().size() * (getTeamAveragePing() + new Random().nextInt(2 - 1) + 1);

	return getTeamElo() / phaseOne;

    }

    public Player getTeamLeader() {
	return Bukkit.getPlayer(getTeamPlayers().get(0));
    }

}
