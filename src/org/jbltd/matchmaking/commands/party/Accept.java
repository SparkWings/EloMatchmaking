package org.jbltd.matchmaking.commands.party;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jbltd.matchmaking.commands.party.util.Invite;
import org.jbltd.matchmaking.util.F;
import org.jbltd.matchmaking.util.Team;

public class Accept implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

	if (!(sender instanceof Player))
	    return true;

	Player player = (Player) sender;

	if (cmd.getName().equalsIgnoreCase("accept")) {

	    //Loop through invites to find this player's invite
	    for (Invite i : Invite.AllInvites) {
		if (i.getInvitee() == player) {
		    Invite.AllInvites.remove(i);
		    player.sendMessage(F.info("Teams",
			    "You joined " + (i.getInvitationSender().getName().endsWith("s")
				    ? i.getInvitationSender().getName() + "' "
				    : i.getInvitationSender().getName() + "'s ") + "team.",
			    false));

		    Team t = Team.searchTeamByPlayer(i.getInvitationSender());

		    t.getTeamPlayers().forEach(p -> Bukkit.getPlayer(p)
			    .sendMessage(F.info("Teams", player.getName() + " has joined your team.", false)));

		    if (t != null) {
			t.addPlayer(player);
		    }

		    return false;
		}

		else
		    continue;
	    }

	    player.sendMessage(F.error("Teams", "You do not have any pending invites.", false));

	}

	return false;
    }

}
