package net.dirtcraft.plugins.dirtrules.commands;

import net.dirtcraft.plugins.dirtrules.data.GuiManager;
import net.dirtcraft.plugins.dirtrules.data.PlayerTracker;
import net.dirtcraft.plugins.dirtrules.utils.Permissions;
import net.dirtcraft.plugins.dirtrules.utils.Strings;
import net.dirtcraft.plugins.dirtrules.utils.Utilities;
import org.bukkit.command.CommandSender;

public class ReloadCommand {

	public static boolean run(CommandSender sender, String[] args) {
		if (!sender.hasPermission(Permissions.RELOAD)) {
			sender.sendMessage(Strings.NO_PERMISSION);
			return true;
		}

		sender.sendMessage(Strings.RELOAD_DONE);
		Utilities.loadConfig();
		GuiManager.hasUpdates = true;
		PlayerTracker.reminder.cancel();
		PlayerTracker.startRulesReminder();
		return true;
	}
}
