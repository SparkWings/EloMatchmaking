package org.jbltd.matchmaking.util;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Small utility class to pull player's ping (clean)
 * @param org.bukkit.entity.Player
 *
 */
public class UtilPing {

    
    public static int getPing(Player player)
    {
	
	CraftPlayer cp = (CraftPlayer) player;
	
	return cp.getHandle().ping;
	
    }
    
    
    public static boolean isLagging(Player player)
    {
	
	if(getPing(player) >= 200)
	{
	    return true;
	}
	
	return false;
    }
    
}
