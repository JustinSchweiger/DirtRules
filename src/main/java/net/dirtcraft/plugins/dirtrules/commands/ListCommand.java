package net.dirtcraft.plugins.dirtrules.commands;

import net.dirtcraft.plugins.dirtrules.utils.Permissions;
import net.dirtcraft.plugins.dirtrules.utils.Strings;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListCommand {
	public static boolean run(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Strings.NO_CONSOLE);
			return false;
		}

		if (!sender.hasPermission(Permissions.BASE) || !sender.hasPermission(Permissions.LIST)) {
			sender.sendMessage(Strings.NO_PERMISSION);
			return true;
		}

		RulesCommand.showRulesGui((Player) sender);

		return true;
	}
}
