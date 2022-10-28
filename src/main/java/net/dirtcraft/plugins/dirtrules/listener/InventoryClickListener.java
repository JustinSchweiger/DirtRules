package net.dirtcraft.plugins.dirtrules.listener;

import net.dirtcraft.plugins.dirtrules.data.GuiManager;
import net.dirtcraft.plugins.dirtrules.data.PlayerTracker;
import net.dirtcraft.plugins.dirtrules.utils.Strings;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (GuiManager.getGui() == null) return;
		if (!event.getInventory().equals(GuiManager.getGui())) return;

		event.setCancelled(true);
		if (event.getSlot() == GuiManager.getAcceptRulesSlot()) {
			Player player = (Player) event.getWhoClicked();
			PlayerTracker.setRulesAccepted(player.getUniqueId());
			player.closeInventory();
			player.sendMessage(Strings.RULES_ACCEPTED);
		}
	}
}
