package net.dirtcraft.plugins.dirtrules.utils;

import com.moandjiezana.toml.Toml;
import net.dirtcraft.plugins.dirtrules.DirtRules;
import net.dirtcraft.plugins.dirtrules.commands.BaseCommand;
import net.dirtcraft.plugins.dirtrules.commands.RulesCommand;
import net.dirtcraft.plugins.dirtrules.config.Config;
import net.dirtcraft.plugins.dirtrules.listener.InventoryClickListener;
import net.dirtcraft.plugins.dirtrules.listener.PlayerListener;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;

public class Utilities {
	public static Config config;

	public static void loadConfig() {
		if (!DirtRules.getPlugin().getDataFolder().exists()) {
			DirtRules.getPlugin().getDataFolder().mkdirs();
		}
		File file = new File(DirtRules.getPlugin().getDataFolder(), "config.toml");
		if (!file.exists()) {
			try {
				Files.copy(DirtRules.getPlugin().getResource("config.toml"), file.toPath());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		config = new Toml(new Toml().read(DirtRules.getPlugin().getResource("config.toml"))).read(file).to(Config.class);
	}

	public static void registerListener() {
		DirtRules.getPlugin().getServer().getPluginManager().registerEvents(new PlayerListener(), DirtRules.getPlugin());
		DirtRules.getPlugin().getServer().getPluginManager().registerEvents(new InventoryClickListener(), DirtRules.getPlugin());
	}

	public static void registerCommands() {
		DirtRules.getPlugin().getCommand("dirtrules").setExecutor(new BaseCommand());
		DirtRules.getPlugin().getCommand("dirtrules").setTabCompleter(new BaseCommand());
		DirtRules.getPlugin().getCommand("rules").setExecutor(new RulesCommand());
	}

	public static void log(Level level, String msg) {
		String consoleMessage;
		if (Level.INFO.equals(level)) {
			consoleMessage = Strings.INTERNAL_PREFIX + ChatColor.WHITE + msg;
		} else if (Level.WARNING.equals(level)) {
			consoleMessage = Strings.INTERNAL_PREFIX + ChatColor.YELLOW + msg;
		} else if (Level.SEVERE.equals(level)) {
			consoleMessage = Strings.INTERNAL_PREFIX + ChatColor.RED + msg;
		} else {
			consoleMessage = Strings.INTERNAL_PREFIX + ChatColor.GRAY + msg;
		}

		DirtRules.getPlugin().getServer().getConsoleSender().sendMessage(consoleMessage);
	}

	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException | NullPointerException e) {
			return false;
		}

		return true;
	}

	public static void disablePlugin() {
		DirtRules.getPlugin().getServer().getPluginManager().disablePlugin(DirtRules.getPlugin());
	}

	public static String format(String message) {
		return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', message);
	}

	public static String joinString(String[] args, int startIndex) {
		StringBuilder builder = new StringBuilder();
		for (int i = startIndex; i < args.length; i++) {
			if (i == args.length - 1) {
				builder.append(args[i]);
			} else {
				builder.append(args[i]).append(" ");
			}
		}
		return builder.toString();
	}

	public static void playReminderSound(Player player) {
		if (Utilities.config.sound.playReminderSound) {
			String sound = Utilities.config.sound.reminderSound;
			if (sound == null) {
				sound = "minecraft:block.anvil.place";
			}
			player.playSound(player.getLocation(), sound, 1, 1);
		}
	}
}
