package org.jbltd.matchmaking;

import java.util.HashSet;
import java.util.UUID;

import org.jbltd.matchmaking.util.Team;

/**
 * Manager class that stores team information in HashSets.
 * 
 * @see java.util.HashSet
 *
 */
public class TeamManager {

    public HashSet<Team> AllTeams = new HashSet<>();
    public HashSet<UUID> MasterTeamPlayerData = new HashSet<>();
    
}
