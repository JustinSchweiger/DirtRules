package net.dirtcraft.plugins.dirtrules.listener;

import net.dirtcraft.plugins.dirtrules.data.PlayerTracker;
import net.dirtcraft.plugins.dirtrules.database.DatabaseOperation;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		DatabaseOperation.addPlayerToTracker(event.getPlayer().getUniqueId(), PlayerTracker::update);
	}
}
