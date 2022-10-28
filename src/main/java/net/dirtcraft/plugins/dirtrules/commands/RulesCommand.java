package net.dirtcraft.plugins.dirtrules.commands;

import net.dirtcraft.plugins.dirtrules.data.GuiData;
import net.dirtcraft.plugins.dirtrules.data.GuiManager;
import net.dirtcraft.plugins.dirtrules.utils.Permissions;
import net.dirtcraft.plugins.dirtrules.utils.Strings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RulesCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Strings.NO_CONSOLE);
			return false;
		}

		if (!sender.hasPermission(Permissions.BASE) || !sender.hasPermission(Permissions.LIST)) {
			sender.sendMessage(Strings.NO_PERMISSION);
			return true;
		}

		showRulesGui((Player) sender);

		return true;
	}

	public static void showRulesGui(Player player) {
		if (GuiData.getRules().isEmpty()) {
			player.sendMessage(Strings.NO_RULES);
			return;
		}

		GuiManager.showGui(player);
	}
}
