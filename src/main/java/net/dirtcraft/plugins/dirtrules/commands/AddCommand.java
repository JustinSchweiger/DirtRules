package net.dirtcraft.plugins.dirtrules.commands;

import net.dirtcraft.plugins.dirtrules.data.GuiData;
import net.dirtcraft.plugins.dirtrules.data.GuiManager;
import net.dirtcraft.plugins.dirtrules.data.PlayerTracker;
import net.dirtcraft.plugins.dirtrules.database.DatabaseOperation;
import net.dirtcraft.plugins.dirtrules.utils.Permissions;
import net.dirtcraft.plugins.dirtrules.utils.Strings;
import net.dirtcraft.plugins.dirtrules.utils.Utilities;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddCommand {

	public static boolean run(CommandSender sender, String[] args) {
		if (!sender.hasPermission(Permissions.ADD)) {
			sender.sendMessage(Strings.NO_PERMISSION);
			return true;
		}

		if (args.length < 2) {
			sender.sendMessage(Strings.INVALID_ARGUMENTS_USAGE + ChatColor.RED + "/dirtrules add <message>");
			return true;
		}

		if (GuiData.getRules().size() == 45) {
			sender.sendMessage(Strings.RULES_FULL);
			return true;
		}

		String message = Utilities.joinString(args, 1);

		addRule(sender, message);
		return true;
	}

	private static void addRule(CommandSender sender, String message) {
		DatabaseOperation.addRule(message, () -> {
			sender.sendMessage(Strings.RULE_ADDED);
			GuiData.reloadRulesData();
			GuiManager.hasUpdates = true;
			PlayerTracker.resetPlayerTracker();
		});
	}
}
