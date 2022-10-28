package net.dirtcraft.plugins.dirtrules.data;

import java.time.LocalDateTime;

public class Rule {
	private final String message;
	private final LocalDateTime localDateTime;

	public Rule(String message, LocalDateTime localDateTime) {
		this.message = message;
		this.localDateTime = localDateTime;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
}
