package net.dirtcraft.plugins.dirtrules;

import net.dirtcraft.plugins.dirtrules.data.GuiData;
import net.dirtcraft.plugins.dirtrules.data.PlayerTracker;
import net.dirtcraft.plugins.dirtrules.database.Database;
import net.dirtcraft.plugins.dirtrules.utils.Utilities;
import org.bukkit.plugin.java.JavaPlugin;

public final class DirtRules extends JavaPlugin {
	private static DirtRules plugin;

	public static DirtRules getPlugin() {
		return plugin;
	}

	@Override
	public void onEnable() {
		plugin = this;
		Utilities.loadConfig();
		Utilities.registerCommands();
		Utilities.registerListener();
		Database.initialiseDatabase();
		GuiData.reloadRulesData();
		PlayerTracker.update();
		PlayerTracker.startRulesReminder();
	}

	@Override
	public void onDisable() {
		Database.closeDatabase();
	}
}
