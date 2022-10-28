package net.dirtcraft.plugins.dirtrules.utils;

import org.bukkit.ChatColor;

public class Strings {
	// ---------------------------------------------------------- GENERAL ----------------------------------------------------------
	public static final String PREFIX = ChatColor.GRAY + "[" + ChatColor.RED + "Dirt" + ChatColor.LIGHT_PURPLE + "Rules" + ChatColor.GRAY + "] ";
	public static final String INTERNAL_PREFIX = ChatColor.GRAY + "[" + ChatColor.RED + "Dirt" + ChatColor.LIGHT_PURPLE + "Rules" + ChatColor.GRAY + "] ";
	public static final String NO_PERMISSION = PREFIX + ChatColor.RED + "You do not have permission to use this command.";
	public static final String NO_CONSOLE = PREFIX + ChatColor.RED + "You must be a player to use this command.";
	public static final String INVALID_ARGUMENTS_USAGE = PREFIX + ChatColor.DARK_RED + "Invalid arguments.\n" + ChatColor.GOLD + "Usage" + ChatColor.GRAY + ": " + ChatColor.RED;
	public static final String RELOAD_DONE = PREFIX + ChatColor.GREEN + "Config reloaded.";
	public static final String RULES_GUI_TITLE = ChatColor.GOLD + "Rules" + ChatColor.GRAY + " - " + ChatColor.RED + "Read carefully!";
	public static final String UNKNOWN_COMMAND = PREFIX + ChatColor.RED + "Unknown command. Type " + ChatColor.DARK_RED + "/dirtrules" + ChatColor.RED + " for a list of available commands.";
	public static final String PLEASE_ACCEPT_RULES = PREFIX + ChatColor.RED + "Please accept the rules by using " + ChatColor.AQUA + "/rules" + ChatColor.RED + "!";
	// -----------------
	public static final String BAR_TOP = Utilities.format("&x&f&b&7&9&0&0&m-&x&f&b&7&1&0&0&m-&x&f&b&6&a&0&0&m-&x&f&a&6&2&0&0&m-&x&f&a&5&b&0&0&m-&x&f&a&5&3&0&0&m-&x&f&a&4&c&0&0&m-&x&f&9&4&4&0&0&m-&x&f&9&3&d&0&0&m-&x&f&9&3&5&0&0&m-&x&f&9&2&d&0&0&m-&x&f&8&2&6&0&0&m-&x&f&8&1&e&0&0&m-&x&f&8&1&7&0&0&m-&x&f&8&0&f&0&0&m-&x&f&7&0&8&0&0&m-&x&f&7&0&0&0&0&m-" + ChatColor.GRAY + "[" + ChatColor.BOLD + " " + ChatColor.RED + "DirtCraft " + ChatColor.LIGHT_PURPLE + "Rules" + ChatColor.BOLD + " " + ChatColor.GRAY + "]" + "&x&f&7&0&0&0&0&m-&x&f&7&0&8&0&0&m-&x&f&8&0&f&0&0&m-&x&f&8&1&7&0&0&m-&x&f&8&1&e&0&0&m-&x&f&8&2&6&0&0&m-&x&f&9&2&d&0&0&m-&x&f&9&3&5&0&0&m-&x&f&9&3&d&0&0&m-&x&f&9&4&4&0&0&m-&x&f&a&4&c&0&0&m-&x&f&a&5&3&0&0&m-&x&f&a&5&b&0&0&m-&x&f&a&6&2&0&0&m-&x&f&b&6&a&0&0&m-&x&f&b&7&1&0&0&m-&x&f&b&7&9&0&0&m-");
	// -------------------------- --------------------------
	public static final String BAR_BOTTOM = Utilities.format("&x&f&b&7&9&0&0&m-&x&f&b&7&4&0&0&m-&x&f&b&6&f&0&0&m-&x&f&b&6&a&0&0&m-&x&f&a&6&6&0&0&m-&x&f&a&6&1&0&0&m-&x&f&a&5&c&0&0&m-&x&f&a&5&7&0&0&m-&x&f&a&5&2&0&0&m-&x&f&a&4&d&0&0&m-&x&f&9&4&9&0&0&m-&x&f&9&4&4&0&0&m-&x&f&9&3&f&0&0&m-&x&f&9&3&a&0&0&m-&x&f&9&3&5&0&0&m-&x&f&9&3&0&0&0&m-&x&f&8&2&c&0&0&m-&x&f&8&2&7&0&0&m-&x&f&8&2&2&0&0&m-&x&f&8&1&d&0&0&m-&x&f&8&1&8&0&0&m-&x&f&8&1&3&0&0&m-&x&f&7&0&f&0&0&m-&x&f&7&0&a&0&0&m-&x&f&7&0&5&0&0&m-&x&f&7&0&0&0&0&m-&x&f&7&0&0&0&0&m-&x&f&7&0&5&0&0&m-&x&f&7&0&a&0&0&m-&x&f&7&0&f&0&0&m-&x&f&8&1&3&0&0&m-&x&f&8&1&8&0&0&m-&x&f&8&1&d&0&0&m-&x&f&8&2&2&0&0&m-&x&f&8&2&7&0&0&m-&x&f&8&2&c&0&0&m-&x&f&9&3&0&0&0&m-&x&f&9&3&5&0&0&m-&x&f&9&3&a&0&0&m-&x&f&9&3&f&0&0&m-&x&f&9&4&4&0&0&m-&x&f&9&4&9&0&0&m-&x&f&a&4&d&0&0&m-&x&f&a&5&2&0&0&m-&x&f&a&5&7&0&0&m-&x&f&a&5&c&0&0&m-&x&f&a&6&1&0&0&m-&x&f&a&6&6&0&0&m-&x&f&b&6&a&0&0&m-&x&f&b&6&f&0&0&m-&x&f&b&7&4&0&0&m-&x&f&b&7&9&0&0&m-");
	// --------------------
	public static final String HALF_BAR_ONE = Utilities.format("&x&f&b&7&9&0&0&m-&x&f&b&7&3&0&0&m-&x&f&b&6&c&0&0&m-&x&f&a&6&6&0&0&m-&x&f&a&6&0&0&0&m-&x&f&a&5&9&0&0&m-&x&f&a&5&3&0&0&m-&x&f&a&4&c&0&0&m-&x&f&9&4&6&0&0&m-&x&f&9&4&0&0&0&m-&x&f&9&3&9&0&0&m-&x&f&9&3&3&0&0&m-&x&f&8&2&d&0&0&m-&x&f&8&2&6&0&0&m-&x&f&8&2&0&0&0&m-&x&f&8&1&9&0&0&m-&x&f&8&1&3&0&0&m-&x&f&7&0&d&0&0&m-&x&f&7&0&6&0&0&m-&x&f&7&0&0&0&0&m-");
	// --------------------
	public static final String HALF_BAR_TWO = Utilities.format("&x&f&7&0&0&0&0&m-&x&f&7&0&6&0&0&m-&x&f&7&0&d&0&0&m-&x&f&8&1&3&0&0&m-&x&f&8&1&9&0&0&m-&x&f&8&2&0&0&0&m-&x&f&8&2&6&0&0&m-&x&f&8&2&d&0&0&m-&x&f&9&3&3&0&0&m-&x&f&9&3&9&0&0&m-&x&f&9&4&0&0&0&m-&x&f&9&4&6&0&0&m-&x&f&a&4&c&0&0&m-&x&f&a&5&3&0&0&m-&x&f&a&5&9&0&0&m-&x&f&a&6&0&0&0&m-&x&f&a&6&6&0&0&m-&x&f&b&6&c&0&0&m-&x&f&b&7&3&0&0&m-&x&f&b&7&9&0&0&m-");

	// ---------------------------------------------------------- ADD COMMAND ----------------------------------------------------------
	public static final String RULE_ADDED = PREFIX + ChatColor.GREEN + "Rule added!";
	public static final String RULES_FULL = PREFIX + ChatColor.RED + "No more rules can be added!";

	// ---------------------------------------------------------- REMOVE COMMAND ----------------------------------------------------------
	public static final String RULE_REMOVED = PREFIX + ChatColor.GREEN + "Rule removed!";
	public static final String RULE_NOT_REMOVED = PREFIX + ChatColor.RED + "Rule could not get removed!";

	// ---------------------------------------------------------- EDIT COMMAND ----------------------------------------------------------
	public static final String EDIT_SUCCESS = PREFIX + ChatColor.GREEN + "Rule edited!";

	// ---------------------------------------------------------- RULES COMMAND ----------------------------------------------------------
	public static final String NO_RULES = PREFIX + ChatColor.RED + "There are no rules yet!";
	public static final String RULES_ACCEPTED = PREFIX + ChatColor.GRAY + "You have accepted the rules! Have fun playing!";
}
