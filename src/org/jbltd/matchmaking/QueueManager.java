package org.jbltd.matchmaking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jbltd.matchmaking.util.F;
import org.jbltd.matchmaking.util.PlayerData;
import org.jbltd.matchmaking.util.Team;
import org.jbltd.matchmaking.util.UpdateEvent;
import org.jbltd.matchmaking.util.UpdateType;
import org.jbltd.matchmaking.util.UtilMethodTiming;

/**
 * Manager class that stores queue data <b>and</b> processes both the Solo and Team Queues.
 *
 */
public class QueueManager implements Listener {

    public ArrayList<PlayerData> SoloQueue = new ArrayList<>();
    public ArrayList<Team> TeamQueue = new ArrayList<>();

    @EventHandler
    public void searchSoloQueue(UpdateEvent e) {

	if (!(e.getType() == UpdateType.FIVESEC)) {
	    return;
	}

	if (SoloQueue.size() < 2) {
	    return;
	}

	UtilMethodTiming uti = new UtilMethodTiming();
	uti.start();

	Map<Double, List<PlayerData>> potentialMatches = SoloQueue.stream()
		.collect(Collectors.groupingBy(p -> p.getCombatValue()));

	TreeMap<Double, List<PlayerData>> sortedMatches = new TreeMap<>(potentialMatches);

	if (sortedMatches.size() % 2 != 0) {

	    sortedMatches.remove(sortedMatches.lastKey());

	}

	Iterator<Double> o = sortedMatches.keySet().iterator();

	while (o.hasNext()) {

	    double next = o.next();

	    SortedMap<Double, List<PlayerData>> g = sortedMatches.subMap((next - 1.0), (next + 1.0));

	    for (List<PlayerData> b : g.values()) {
		b.get(0).getPlayer().sendMessage(F.info("Queue",
			"You have been matched with a player for combat. Prepare to be teleported.", false));
		SoloQueue.remove(b.get(0));

		uti.stop();
		// Average time taken to process method with 3 players: 0.007
		// seconds
	    }

	}

    }

    @EventHandler
    public void searchTeamQueue(UpdateEvent e) {

	if (!(e.getType() == UpdateType.FIVESEC)) {
	    return;
	}

	if (TeamQueue.size() < 2) {
	    return;
	}

	Map<Double, List<Team>> potentialMatches = TeamQueue.stream()
		.collect(Collectors.groupingBy(p -> p.getTeamCombatValue()));

	TreeMap<Double, List<Team>> sortedMatches = new TreeMap<>(potentialMatches);

	if (sortedMatches.size() % 2 != 0) {

	    sortedMatches.remove(sortedMatches.lastKey());

	}

	Iterator<Double> o = sortedMatches.keySet().iterator();

	while (o.hasNext()) {

	    double next = o.next();

	    SortedMap<Double, List<Team>> g = sortedMatches.subMap((next - 1.0), (next + 1.0));

	    for (List<Team> b : g.values()) {

		Team t = b.get(0);
		t.getTeamPlayers().forEach(p -> Bukkit.getPlayer(p).sendMessage(F.info("Queue",
			"Your team has been matched with another team. Prepare to be teleported.", false)));

		TeamQueue.remove(t);

	    }

	}

    }

}
