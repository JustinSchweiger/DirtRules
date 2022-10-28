package net.dirtcraft.plugins.dirtrules.database.callbacks;

import net.dirtcraft.plugins.dirtrules.data.Player;

import java.util.List;

public interface GetPlayersCallback {
	void onSuccess(List<Player> players);
}
