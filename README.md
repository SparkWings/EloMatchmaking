# EloMatchmaking
# Matchmaking


A simple matchmaking system for Minecraft.

Uses Spigot 1.8.8-R0.1-SNAPSHOT for building.

Features:
- Solo and Team Queueing for games
- Game API functionality
#How it Works

When a player joins the server, they are given the option to either queue for the solo queue, or create a party (called a team). If they choose to go solo, they are matched to another player in the queue that is the closest possible Combat Value to them. Combat Value is what the system reads is how "prepared" the player is for their fight. Players with lower combat values are seen as less prepared.

TODO:
- Team Queueing
- Disconnect Handling
- Improved AI
Combat value is determined by the player's elo divided by the player's ping.

If the player chooses to start a team, the team leader (and only the team leader) may add his/her team to the Team Queue. The team queue matches teams based on the team combat value, or: The Team's elo divided by the team size multiplied by the team's average ping.

Once a match is found in either queue, the players, or teams, are alerted that they are about to be teleported to the arena, where a Game API would take over and dictate the outcome.

Once the queue tells a player they are to be teleported, the player(s) are removed from their respective queue.

For demonstration purposes, in the code when the player either joins the server or creates a team, they are given a random elo.

In the event of an odd number of players or teams in the queue, the last person listed in the queue, or as the system reads it, the most unprepared individual(s) are left out, only to automatically be readded to the combat queue.



#Analytics

The average amount of time for a queue processing to occur is 0.007 seconds for 3 players.
The average amount of time it takes for the plugin to register, enable, and configure its modules is 0.0113 seconds
Quadratic Big O Notation was not used.
HashSets are used to store data tables which could be considerably large, whereas ArrayLists are used to store smaller data