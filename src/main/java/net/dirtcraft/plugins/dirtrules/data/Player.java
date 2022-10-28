package net.dirtcraft.plugins.dirtrules.data;

import java.util.UUID;

public class Player {
	private final UUID uuid;
	private boolean rulesAccepted;

	public Player(UUID uuid, boolean rulesAccepted) {
		this.uuid = uuid;
		this.rulesAccepted = rulesAccepted;
	}

	public UUID getUuid() {
		return uuid;
	}

	public boolean isRulesAccepted() {
		return rulesAccepted;
	}

	public void setRulesAccepted(boolean rulesAccepted) {
		this.rulesAccepted = rulesAccepted;
	}
}
