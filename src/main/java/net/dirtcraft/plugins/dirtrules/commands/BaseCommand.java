package net.dirtcraft.plugins.dirtrules.commands;

import net.dirtcraft.plugins.dirtrules.data.GuiData;
import net.dirtcraft.plugins.dirtrules.data.Rule;
import net.dirtcraft.plugins.dirtrules.utils.Permissions;
import net.dirtcraft.plugins.dirtrules.utils.Strings;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseCommand implements CommandExecutor, TabCompleter {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Strings.NO_CONSOLE);
			return false;
		}

		if (!sender.hasPermission(Permissions.BASE)) {
			sender.sendMessage(Strings.NO_PERMISSION);
			return true;
		}

		if (args.length == 0) {
			List<TextComponent> listings = getListings(sender);
			sender.sendMessage(Strings.BAR_TOP);
			sender.sendMessage("");
			for (TextComponent listing : listings) {
				sender.spigot().sendMessage(listing);
			}
			sender.sendMessage("");
			sender.sendMessage(Strings.BAR_BOTTOM);

			return true;
		}

		switch (args[0].toLowerCase()) {
			case "add":
				return AddCommand.run(sender, args);
			case "insert":
				return InsertCommand.run(sender, args);
			case "remove":
				return RemoveCommand.run(sender, args);
			case "edit":
				return EditCommand.run(sender, args);
			case "list":
				return ListCommand.run(sender, args);
			case "reload":
				return ReloadCommand.run(sender, args);
			default:
				sender.sendMessage(Strings.UNKNOWN_COMMAND);
				return false;
		}
	}

	private List<TextComponent> getListings(CommandSender sender) {
		List<TextComponent> listings = new ArrayList<>();

		if (sender.hasPermission(Permissions.ADD)) {
			TextComponent add = new TextComponent(ChatColor.GOLD + "  /dirtrules " + ChatColor.YELLOW + "add <message>");
			add.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.GRAY + "Adds a new rules with the specified message.")));
			add.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/dirtrules add <message>"));
			listings.add(add);

			TextComponent insert = new TextComponent(ChatColor.GOLD + "  /dirtrules " + ChatColor.YELLOW + "insert <index> <message>");
			insert.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.GRAY + "Inserts a new rules with the specified message at the specified index.")));
			insert.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/dirtrules insert <index> <message>"));
			listings.add(insert);
		}

		if (sender.hasPermission(Permissions.REMOVE)) {
			TextComponent remove = new TextComponent(ChatColor.GOLD + "  /dirtrules " + ChatColor.YELLOW + "remove <id>");
			remove.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.GRAY + "Removes a specified rule.")));
			remove.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/dirtrules remove <id>"));
			listings.add(remove);
		}

		if (sender.hasPermission(Permissions.EDIT)) {
			TextComponent edit = new TextComponent(ChatColor.GOLD + "  /dirtrules " + ChatColor.YELLOW + "edit <id> <message>");
			edit.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.GRAY + "Edits a rule.")));
			edit.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/dirtrules edit <id> <message>"));
			listings.add(edit);
		}

		if (sender.hasPermission(Permissions.LIST)) {
			TextComponent list = new TextComponent(ChatColor.GOLD + "  /dirtrules " + ChatColor.YELLOW + "list");
			list.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.GRAY + "Opens the GUI with all rules.")));
			list.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/dirtrules list"));
			listings.add(list);
		}

		if (sender.hasPermission(Permissions.RELOAD)) {
			TextComponent reload = new TextComponent(ChatColor.GOLD + "  /dirtrules " + ChatColor.YELLOW + "reload");
			reload.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.GRAY + "Reloads the config.")));
			reload.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/dirtrules reload"));
			listings.add(reload);
		}

		return listings;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		List<String> arguments = new ArrayList<>();

		if (args.length == 1) {
			if (sender.hasPermission(Permissions.LIST)) {
				arguments.add("list");
			}

			if (sender.hasPermission(Permissions.ADD)) {
				arguments.add("add");
				arguments.add("insert");
			}

			if (sender.hasPermission(Permissions.REMOVE)) {
				arguments.add("remove");
			}

			if (sender.hasPermission(Permissions.EDIT)) {
				arguments.add("edit");
			}

			if (sender.hasPermission(Permissions.RELOAD)) {
				arguments.add("reload");
			}
		} else if (args.length > 1 && args[0].equalsIgnoreCase("add") && sender.hasPermission(Permissions.ADD)) {
			arguments.add("<message - use % for linebreak>");
		} else if (args.length == 2 && args[0].equalsIgnoreCase("insert") && sender.hasPermission(Permissions.ADD)) {
			int size = GuiData.getRules().size();
			for (int i = 1; i <= size; i++) {
				arguments.add(String.valueOf(i));
			}
		} else if (args.length > 2 && args[0].equalsIgnoreCase("insert") && sender.hasPermission(Permissions.ADD)) {
			arguments.add("<message - use % for linebreak>");
		} else if (args.length == 2 && args[0].equalsIgnoreCase("remove") && sender.hasPermission(Permissions.REMOVE)) {
			int size = GuiData.getRules().size();
			for (int i = 1; i <= size; i++) {
				arguments.add(String.valueOf(i));
			}
		} else if (args.length == 2 && args[0].equalsIgnoreCase("edit") && sender.hasPermission(Permissions.EDIT)) {
			int size = GuiData.getRules().size();
			for (int i = 1; i <= size; i++) {
				arguments.add(String.valueOf(i));
			}
		} else if (args.length > 2 && args[0].equalsIgnoreCase("edit") && sender.hasPermission(Permissions.EDIT)) {
			arguments.add("<message - use % for linebreak>");
		}

		List<String> tabResults = new ArrayList<>();
		for (String argument : arguments) {
			if (argument.equalsIgnoreCase("<message - use % for linebreak>")) {
				tabResults.add(argument);
				continue;
			}

			if (argument.toLowerCase().startsWith(args[args.length - 1].toLowerCase())) {
				tabResults.add(argument);
			}
		}

		return tabResults;
	}
}
