package net.dirtcraft.plugins.dirtrules.commands;

import net.dirtcraft.plugins.dirtrules.data.GuiData;
import net.dirtcraft.plugins.dirtrules.data.GuiManager;
import net.dirtcraft.plugins.dirtrules.data.PlayerTracker;
import net.dirtcraft.plugins.dirtrules.database.DatabaseOperation;
import net.dirtcraft.plugins.dirtrules.utils.Permissions;
import net.dirtcraft.plugins.dirtrules.utils.Strings;
import net.dirtcraft.plugins.dirtrules.utils.Utilities;
import org.bukkit.command.CommandSender;

public class EditCommand {
	public static boolean run(CommandSender sender, String[] args) {
		if (!sender.hasPermission(Permissions.EDIT)) {
			sender.sendMessage(Strings.NO_PERMISSION);
			return true;
		}

		if (args.length < 3 || !Utilities.isInteger(args[1])) {
			sender.sendMessage(Strings.INVALID_ARGUMENTS_USAGE + " /dirtrules edit <index> <message>");
		}

		int index = Integer.parseInt(args[1]);
		String message = Utilities.joinString(args, 2);

		DatabaseOperation.editRule(index, message, () -> {
			sender.sendMessage(Strings.EDIT_SUCCESS);
			GuiData.reloadRulesData();
			GuiManager.hasUpdates = true;
			PlayerTracker.resetPlayerTracker();
		});

		return true;
	}
}
