package net.dirtcraft.plugins.dirtrules.database.callbacks;

import net.dirtcraft.plugins.dirtrules.data.Rule;

import java.util.Map;

public interface GetRulesCallback {
	void onSuccess(Map<Integer, Rule> rulesFound);
}
