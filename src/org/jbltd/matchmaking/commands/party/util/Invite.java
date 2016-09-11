package org.jbltd.matchmaking.commands.party.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.jbltd.matchmaking.util.F;

public class Invite {

    private Player _from, _to;
    
    public static List<Invite> AllInvites = new ArrayList<Invite>();
    
    public Invite(Player from, Player to)
    {
	this._from = from;
	this._to = to;
	
	_from.sendMessage(F.info("Teams", "You invited "+_to.getName()+" to your team.", false));
	_to.sendMessage(F.info("Teams", _from.getName()+" has invited you to their team. Type /accept "+_from.getName()+" to join their team.", false));
	
    }
    
    public Player getInvitationSender()
    {
	return _from;
    }
    
    public Player getInvitee()
    {
	return _to;
    }
    
    public static Invite searchInviteByPlayer(Player player)
    {
	
	for(Invite i : AllInvites)
	{
	    
	    if(i._to == player)
	    {
		return i;
	    }
		
	    else continue;
	}
	
	return null;
    }

}
