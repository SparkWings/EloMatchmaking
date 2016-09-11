package org.jbltd.matchmaking.commands.party;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jbltd.matchmaking.commands.party.util.Invite;
import org.jbltd.matchmaking.util.F;
import org.jbltd.matchmaking.util.Team;

public class PartyInvite implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

	if (!(sender instanceof Player))
	    return true;

	Player player = (Player) sender;

	if (cmd.getName().equalsIgnoreCase("tinvite")) {

	    if (!Team.MasterTeams.contains(player.getUniqueId())) {
		player.sendMessage(F.error("Teams", "You are not on a team.", false));
		return true;
	    }

	    Team t = Team.searchTeamByPlayer(player);

	    if (t.getTeamPlayers().size() >= 5) {
		player.sendMessage(
			F.error("Teams", "You have reached the maximum number of players on your team.", false));
		return true;
	    }

	    if (args.length < 1) {
		player.sendMessage(F.error("Teams", "You must provide a player argument!", false));
		return true;
	    }

	    Player target = Bukkit.getPlayer(args[0]);

	    if (!target.isOnline()) {
		player.sendMessage(F.error("Teams", "That player is not online.", false));
		return true;
	    }

	    if (!target.hasPlayedBefore()) {
		player.sendMessage(F.error("Teams", "That player has never played before.", false));
		return true;
	    }

	    if (Invite.searchInviteByPlayer(target) == null) {
		Invite i = new Invite(player, target);
		Invite.AllInvites.add(i);
	    }

	}

	return false;
    }

}
