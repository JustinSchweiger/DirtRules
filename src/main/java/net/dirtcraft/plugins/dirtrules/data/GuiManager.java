package net.dirtcraft.plugins.dirtrules.data;

import net.dirtcraft.plugins.dirtrules.utils.Strings;
import net.dirtcraft.plugins.dirtrules.utils.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GuiManager {
	private static Inventory gui;
	private static int guiSize;
	private static int acceptRulesSlot;
	public static boolean hasUpdates = false;

	public static void showGui(Player player) {
		if (gui == null) initializeGui();
		if (hasUpdates) initializeGui();

		player.openInventory(gui);
	}

	private static void initializeGui() {
		Map<Integer, Rule> rules = GuiData.getRules();

		int size = rules.size();

		if (size <= 9)
			guiSize = 18;
		else if (size <= 18)
			guiSize = 27;
		else if (size <= 27)
			guiSize = 36;
		else if (size <= 36)
			guiSize = 45;
		else
			guiSize = 54;

		gui = Bukkit.createInventory(null, guiSize, Strings.RULES_GUI_TITLE);
		for (Map.Entry<Integer, Rule> entry : rules.entrySet()) {
			int slot = entry.getKey() - 1;
			Rule rule = entry.getValue();

			ItemStack item = createRuleGuiItem(entry.getKey(), rule);
			gui.setItem(slot, item);
		}

		for (int i = 0; i < guiSize; i++) {
			if (gui.getItem(i) == null) {
				gui.setItem(i, createFillGuiItem());
			}
		}

		switch (guiSize) {
			case 18:
				acceptRulesSlot = 13;
				break;
			case 27:
				acceptRulesSlot = 22;
				break;
			case 36:
				acceptRulesSlot = 31;
				break;
			case 45:
				acceptRulesSlot = 40;
				break;
			default:
				acceptRulesSlot = 49;
		}

		gui.setItem(acceptRulesSlot, createAcceptRulesGuiItem());
		hasUpdates = false;
	}

	private static ItemStack createRuleGuiItem(int index, Rule rule) {
		ItemStack item;
		if (Utilities.config.general.incrementingAmountOfItems) {
			item = new ItemStack(Material.RED_TERRACOTTA, index);
		} else {
			item = new ItemStack(Material.RED_TERRACOTTA, 1);
		}
		final ItemMeta meta = item.getItemMeta();

		assert meta != null;
		meta.setDisplayName(ChatColor.GRAY + "\u2219 " + ChatColor.RED + "Rule " + ChatColor.GRAY + "[" + ChatColor.YELLOW + "#" + index + ChatColor.GRAY + "]");

		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY + "[" + ChatColor.AQUA + rule.getLocalDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")) + ChatColor.GRAY + "]");
		lore.add(" ");
		lore.addAll(Arrays.asList(Utilities.format(rule.getMessage()).split("%")));
		meta.setLore(lore);

		item.setItemMeta(meta);

		return item;
	}

	private static ItemStack createFillGuiItem() {
		final ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
		final ItemMeta meta = item.getItemMeta();

		assert meta != null;
		meta.setDisplayName(" ");

		item.setItemMeta(meta);

		return item;
	}

	private static ItemStack createAcceptRulesGuiItem() {
		final ItemStack item = new ItemStack(Material.GREEN_TERRACOTTA, 1);
		final ItemMeta meta = item.getItemMeta();

		assert meta != null;
		meta.setDisplayName(ChatColor.GREEN + "Accept Rules " + ChatColor.DARK_GREEN + "\u2714");
		meta.setLore(Arrays.asList(" ", ChatColor.DARK_AQUA + "Click to accept the rules!"));

		item.setItemMeta(meta);

		return item;
	}

	public static int getGuiSize() {
		return guiSize;
	}

	public static int getAcceptRulesSlot() {
		return acceptRulesSlot;
	}

	public static Inventory getGui() {
		return gui;
	}
}
