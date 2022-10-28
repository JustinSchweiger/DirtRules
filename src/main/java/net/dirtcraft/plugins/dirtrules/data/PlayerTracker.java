package net.dirtcraft.plugins.dirtrules.data;

import net.dirtcraft.plugins.dirtrules.DirtRules;
import net.dirtcraft.plugins.dirtrules.database.DatabaseOperation;
import net.dirtcraft.plugins.dirtrules.utils.Strings;
import net.dirtcraft.plugins.dirtrules.utils.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class PlayerTracker {
	private static List<Player> playerTracker;
	public static BukkitTask reminder;

	public static void update() {
		playerTracker = new ArrayList<>();
		DatabaseOperation.getAllPlayersFromTracker((List<Player> players) -> {
			playerTracker.addAll(players);
		});
	}

	public static void resetPlayerTracker() {
		DatabaseOperation.resetPlayerTracker();
	}

	public static void setRulesAccepted(UUID player) {
		for (Player p : playerTracker) {
			if (p.getUuid().equals(player)) {
				p.setRulesAccepted(true);
				DatabaseOperation.updatePlayerTrackerAgree(player);
				break;
			}
		}
	}

	public static List<org.bukkit.entity.Player> getPlayersNotAccepted() {
		List<org.bukkit.entity.Player> players = new ArrayList<>();
		Collection<? extends org.bukkit.entity.Player> onlinePlayers = Bukkit.getOnlinePlayers();
		for (Player player : playerTracker) {
			if (!player.isRulesAccepted() && onlinePlayers.contains(Bukkit.getPlayer(player.getUuid()))) {
				players.add(Bukkit.getPlayer(player.getUuid()));
			}
		}
		return players;
	}

	public static void startRulesReminder() {
		int interval = Utilities.config.general.rulesReminderInterval;
		boolean ignoreStaff = Utilities.config.general.ignoreStaff;

		reminder = Bukkit.getScheduler().runTaskTimer(DirtRules.getPlugin(), () -> {
			if (GuiData.getRules().isEmpty()) return;

			List<org.bukkit.entity.Player> playersToRemind = getPlayersNotAccepted();
			for (org.bukkit.entity.Player player : playersToRemind) {
				if (player.hasPermission("dirtchat.staff") && ignoreStaff) continue;

				player.sendMessage(Strings.PLEASE_ACCEPT_RULES);
				Utilities.playReminderSound(player);
			}
		}, 20L * 20L, 20L * interval);
	}
}
