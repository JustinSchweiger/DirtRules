package net.dirtcraft.plugins.dirtrules.commands;

import net.dirtcraft.plugins.dirtrules.data.GuiData;
import net.dirtcraft.plugins.dirtrules.data.GuiManager;
import net.dirtcraft.plugins.dirtrules.data.PlayerTracker;
import net.dirtcraft.plugins.dirtrules.database.DatabaseOperation;
import net.dirtcraft.plugins.dirtrules.database.callbacks.RemoveCallback;
import net.dirtcraft.plugins.dirtrules.utils.Permissions;
import net.dirtcraft.plugins.dirtrules.utils.Strings;
import net.dirtcraft.plugins.dirtrules.utils.Utilities;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class RemoveCommand {
	public static boolean run(CommandSender sender, String[] args) {
		if (!sender.hasPermission(Permissions.REMOVE)) {
			sender.sendMessage(Strings.NO_PERMISSION);
			return true;
		}

		if (args.length != 2 || !Utilities.isInteger(args[1])) {
			sender.sendMessage(Strings.INVALID_ARGUMENTS_USAGE + ChatColor.RED + "/dirtrules remove <index>");
			return true;
		}

		int index = Integer.parseInt(args[1]);
		if (index < 1 || index > GuiData.getRules().size()) {
			sender.sendMessage(Strings.INVALID_ARGUMENTS_USAGE + ChatColor.RED + "/dirtrules remove <index>");
			return true;
		}

		DatabaseOperation.removeRule(index, new RemoveCallback() {
			@Override
			public void onSuccess() {
				sender.sendMessage(Strings.RULE_REMOVED);
				GuiData.reloadRulesData();
				GuiManager.hasUpdates = true;
				PlayerTracker.resetPlayerTracker();
			}

			@Override
			public void onFailure() {
				sender.sendMessage(Strings.RULE_NOT_REMOVED);
			}
		});

		return true;
	}
}
