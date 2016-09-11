package org.jbltd.matchmaking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jbltd.matchmaking.util.F;
import org.jbltd.matchmaking.util.Team;
import org.jbltd.matchmaking.util.UpdateEvent;
import org.jbltd.matchmaking.util.UpdateType;

@SuppressWarnings("unused")
public class QueueManager implements Listener {

    public static ArrayList<PlayerData> SoloQueue = new ArrayList<>();
    public static ArrayList<Team> TeamQueue = new ArrayList<>();

    @EventHandler
    public void searchSoloQueue(UpdateEvent e) {

	if (!(e.getType() == UpdateType.SEC)) {
	    return;
	}

	if (SoloQueue.size() < 2) {
	    return;
	}

	Map<Double, List<PlayerData>> potentialMatches = SoloQueue.stream()
		.collect(Collectors.groupingBy(p -> p.getCombatValue()));

	TreeMap<Double, List<PlayerData>> sortedMatches = new TreeMap<>(potentialMatches);

	Iterator<Double> o = sortedMatches.keySet().iterator();
	
	while (o.hasNext()) {

		SortedMap<Double, List<PlayerData>> g = sortedMatches.subMap((o.next() - 1.0), (o.next() + 1.0));

		for (List<PlayerData> b : g.values()) {
		    b.get(0).getPlayer().sendMessage(F.info("Queue",
			    "You have been matched with a player for combat. Prepare to be teleported.", false));
		    SoloQueue.remove(b.get(0));
		    
		    //Space for implementation of a Game API
		}

	    }
	}

    

    @EventHandler
    public void searchTeamQueue(UpdateEvent e) {

	if (!(e.getType() == UpdateType.SEC)) {
	    return;
	}

	if (TeamQueue.size() < 2) {
	    return;
	}

	Map<Double, List<Team>> potentialMatches = TeamQueue.stream()
		.collect(Collectors.groupingBy(p -> p.getTeamAveragePing()));

	TreeMap<Double, List<Team>> sortedMatches = new TreeMap<>(potentialMatches);

	Iterator<Double> o = sortedMatches.keySet().iterator();

	while (o.hasNext()) {
	    SortedMap<Double, List<Team>> g = sortedMatches.subMap((o.next() - 1.0), (o.next() + 1.0));

	    for (List<Team> b : g.values()) {
		// Do something with b.get(0);
	    }

	}

    }

}
