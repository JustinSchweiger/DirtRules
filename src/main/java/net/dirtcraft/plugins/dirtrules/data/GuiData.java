package net.dirtcraft.plugins.dirtrules.data;

import net.dirtcraft.plugins.dirtrules.database.DatabaseOperation;

import java.util.Map;
import java.util.TreeMap;

public class GuiData {
	private static Map<Integer, Rule> rules;

	public static void reloadRulesData() {
		rules = new TreeMap<>();
		DatabaseOperation.loadRules((Map<Integer, Rule> rulesFound) -> {
			for (Integer key : rulesFound.keySet()) {
				rules.put(key, rulesFound.get(key));
			}
		});
	}

	public static Map<Integer, Rule> getRules() {
		return rules;
	}
}
