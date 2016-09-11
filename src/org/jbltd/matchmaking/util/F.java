package org.jbltd.matchmaking.util;

import org.bukkit.ChatColor;

public class F {

    public static String AQUA = ChatColor.AQUA + "";
    public static String BLACK = ChatColor.BLACK + "";
    public static String BLUE = ChatColor.BLUE + "";
    public static String BOLD = ChatColor.BOLD + "";
    public static String DARK_AQUA = ChatColor.DARK_AQUA + "";
    public static String DARK_BLUE = ChatColor.DARK_BLUE + "";
    public static String DARK_GRAY = ChatColor.DARK_GRAY + "";
    public static String DARK_GREEN = ChatColor.DARK_GREEN + "";
    public static String DARK_PURPLE = ChatColor.DARK_PURPLE + "";
    public static String DARK_RED = ChatColor.DARK_RED + "";
    public static String GOLD = ChatColor.GOLD + "";
    public static String GRAY = ChatColor.GRAY + "";
    public static String GREEN = ChatColor.GREEN + "";
    public static String ITALIC = ChatColor.ITALIC + "";
    public static String LIGHT_PURPLE = ChatColor.LIGHT_PURPLE + "";
    public static String SCRAMBLE = ChatColor.MAGIC + "";
    public static String RESET = ChatColor.RESET + "";
    public static String RED = ChatColor.RED + "";
    public static String STRIKE = ChatColor.STRIKETHROUGH + "";
    public static String UNDERLINE = ChatColor.UNDERLINE + "";
    public static String WHITE = ChatColor.WHITE + "";
    public static String YELLOW = ChatColor.YELLOW + "";

    public static String info(String head, String message, boolean logSafe) {

	if (!logSafe) {
	    return ChatColor.GRAY + head + ChatColor.DARK_GRAY + "> " + ChatColor.GREEN + message;
	} else {
	    return head + "> " + message;
	}

    }

    public static String error(String head, String message, boolean logSafe)
    {
	
	if (!logSafe) {
	    return ChatColor.GRAY + head + ChatColor.DARK_GRAY + "> " + ChatColor.RED + message;
	} else {
	    return head + "> " + message;
	}
	
    }
    
    public static void log(String head, String message)
    {
	System.out.println(head+"> "+message);
	return;
    }
    
}
