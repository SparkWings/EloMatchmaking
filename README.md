# EloMatchmaking


A simple matchmaking system for Minecraft.
Uses Spigot 1.8.8-R0.1-SNAPSHOT for building.

#How it Works

When a player joins the server, they are given the option to either queue for the solo queue, or create a party (called a team). If they choose to go solo, they are matched to another player in the queue that is the closest possible Combat Value to them. Combat Value is what the system reads is how "prepared" the player is for their fight. Players with lower combat values are seen as less prepared.

Combat value is determined by the player's elo divided by the player's ping.

If the player chooses to start a team, the team leader (and only the team leader) may add his/her team to the Team Queue. The team queue matches teams based on the team combat value, or: The Team's elo divided by the team size multiplied by the team's average ping.

Once a match is found in either queue, the players, or teams, are alerted that they are about to be teleported to the arena, where a Game API would take over and dictate the outcome.

Once the queue tells a player they are to be teleported, the player(s) are removed from their respective queue.

For demonstration purposes, in the code when the player either joins the server or creates a team, they are given a random elo.

In the event of an odd number of players or teams in the queue, the last person listed in the queue, or as the system reads it, the most unprepared individual(s) are left out, only to automatically be readded to the combat queue.
