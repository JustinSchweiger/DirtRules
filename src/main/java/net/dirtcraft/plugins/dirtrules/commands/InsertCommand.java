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

public class InsertCommand {
	public static boolean run(CommandSender sender, String[] args) {
		if (!sender.hasPermission(Permissions.ADD)) {
			sender.sendMessage(Strings.NO_PERMISSION);
			return true;
		}

		if (args.length < 3) {
			sender.sendMessage(Strings.INVALID_ARGUMENTS_USAGE + ChatColor.RED + "/dirtrules insert <index> <message>");
			return true;
		}

		if (!Utilities.isInteger(args[1])) {
			sender.sendMessage(Strings.INVALID_ARGUMENTS_USAGE + ChatColor.RED + "/dirtrules insert <index> <message>");
			return true;
		}

		if (GuiData.getRules().size() == 45) {
			sender.sendMessage(Strings.RULES_FULL);
			return true;
		}

		String message = Utilities.joinString(args, 2);

		insertRule(sender, Integer.parseInt(args[1]), message);
		return true;
	}

	private static void insertRule(CommandSender sender, int index, String message) {
		DatabaseOperation.insertRule(index, message, () -> {
			sender.sendMessage(Strings.RULE_ADDED);
			GuiData.reloadRulesData();
			GuiManager.hasUpdates = true;
			PlayerTracker.resetPlayerTracker();
		});
	}
}
