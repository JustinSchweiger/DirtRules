package net.dirtcraft.plugins.dirtrules.database;
import net.dirtcraft.plugins.dirtrules.DirtRules;
import net.dirtcraft.plugins.dirtrules.data.Player;
import net.dirtcraft.plugins.dirtrules.data.PlayerTracker;
import net.dirtcraft.plugins.dirtrules.data.Rule;
import net.dirtcraft.plugins.dirtrules.database.callbacks.AddCallback;
import net.dirtcraft.plugins.dirtrules.database.callbacks.GetPlayersCallback;
import net.dirtcraft.plugins.dirtrules.database.callbacks.GetRulesCallback;
import net.dirtcraft.plugins.dirtrules.database.callbacks.RemoveCallback;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DatabaseOperation {

	public static void addRule(final String message, final AddCallback addCallback) {
		Bukkit.getScheduler().runTaskAsynchronously(DirtRules.getPlugin(), () -> {
			try (Connection connection = Database.getConnection();
			     PreparedStatement addStatement = connection.prepareStatement("INSERT INTO RULES VALUES (?, ?, ?)");
				 PreparedStatement getHighestIndexStatement = connection.prepareStatement("SELECT MAX(rule_id) as rule_id FROM RULES")) {
				int highestIndex = 0;
				ResultSet resultSet = getHighestIndexStatement.executeQuery();
				if (resultSet.next()) {
					highestIndex = resultSet.getInt("rule_id");
				}
				addStatement.setInt(1, highestIndex + 1);
				addStatement.setString(2, message);
				addStatement.setString(3, LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
				addStatement.execute();

				Bukkit.getScheduler().runTask(DirtRules.getPlugin(), addCallback::onSuccess);
			} catch (SQLException ignored) {}
		});
	}

	public static void addPlayerToTracker(final UUID uniqueId, final AddCallback addCallback) {
		Bukkit.getScheduler().runTaskAsynchronously(DirtRules.getPlugin(), () -> {
			try (Connection connection = Database.getConnection();
			     PreparedStatement addStatement = connection.prepareStatement("INSERT INTO TRACKER VALUES (?, ?)")) {
				addStatement.setString(1, uniqueId.toString());
				addStatement.setBoolean(2, false);
				addStatement.execute();

				Bukkit.getScheduler().runTask(DirtRules.getPlugin(), addCallback::onSuccess);
			} catch (SQLException ignored) {}
		});
	}

	public static void getAllPlayersFromTracker(final GetPlayersCallback getPlayersCallback) {
		Bukkit.getScheduler().runTaskAsynchronously(DirtRules.getPlugin(), () -> {
			try (Connection connection = Database.getConnection();
			     PreparedStatement getStatement = connection.prepareStatement("SELECT * FROM TRACKER")) {
				ResultSet resultSet = getStatement.executeQuery();
				List<Player> players = new ArrayList<>();
				while (resultSet.next()) {
					players.add(
							new Player(
									UUID.fromString(resultSet.getString("tracker_playerUuid")),
									resultSet.getBoolean("tracker_accepted")
							)
					);
				}

				Bukkit.getScheduler().runTask(DirtRules.getPlugin(), () -> getPlayersCallback.onSuccess(players));
			} catch (SQLException ignored) {}
		});
	}

	public static void removeRule(final int index, final RemoveCallback removeCallback) {
		Bukkit.getScheduler().runTaskAsynchronously(DirtRules.getPlugin(), () -> {
			try (Connection connection = Database.getConnection();
			     PreparedStatement removeStatement = connection.prepareStatement("DELETE FROM RULES WHERE rule_id = ?");
				 PreparedStatement updateStatement = connection.prepareStatement("UPDATE RULES SET rule_id = rule_id - 1 WHERE rule_id > ?")) {
				removeStatement.setInt(1, index);
				removeStatement.execute();

				updateStatement.setInt(1, index);
				updateStatement.execute();

				Bukkit.getScheduler().runTask(DirtRules.getPlugin(), removeCallback::onSuccess);
			} catch (SQLException ignored) {
				Bukkit.getScheduler().runTask(DirtRules.getPlugin(), removeCallback::onFailure);
			}
		});
	}

	public static void insertRule(final int index, final String message, final AddCallback addCallback) {
		Bukkit.getScheduler().runTaskAsynchronously(DirtRules.getPlugin(), () -> {
			try (Connection connection = Database.getConnection();
			     PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO RULES VALUES (?, ?, ?)");
				 PreparedStatement updateStatement = connection.prepareStatement("UPDATE RULES SET rule_id = rule_id + 1 WHERE rule_id >= ?")) {
				updateStatement.setInt(1, index);
				updateStatement.execute();

				insertStatement.setInt(1, index);
				insertStatement.setString(2, message);
				insertStatement.setString(3, LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
				insertStatement.execute();

				Bukkit.getScheduler().runTask(DirtRules.getPlugin(), addCallback::onSuccess);
			} catch (SQLException e) {e.printStackTrace();}
		});
	}

	public static void editRule(final int index, final String message, final AddCallback addCallback) {
		Bukkit.getScheduler().runTaskAsynchronously(DirtRules.getPlugin(), () -> {
			try (Connection connection = Database.getConnection();
			     PreparedStatement editStatement = connection.prepareStatement("UPDATE RULES SET rule_message = ? WHERE rule_id = ?")) {
				editStatement.setString(1, message);
				editStatement.setInt(2, index);
				editStatement.execute();

				Bukkit.getScheduler().runTask(DirtRules.getPlugin(), addCallback::onSuccess);
			} catch (SQLException ignored) {}
		});
	}

	public static void loadRules(final GetRulesCallback getRulesCallback) {
		Bukkit.getScheduler().runTaskAsynchronously(DirtRules.getPlugin(), () -> {
			try (Connection connection = Database.getConnection();
			     PreparedStatement getStatement = connection.prepareStatement("SELECT * FROM RULES")) {
				ResultSet resultSet = getStatement.executeQuery();
				Map<Integer, Rule> rules = new TreeMap<>();
				while (resultSet.next()) {
					rules.put(
							resultSet.getInt("rule_id"),
							new Rule(
									resultSet.getString("rule_message"),
									LocalDateTime.parse(resultSet.getString("rule_dateAdded"), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
							));
				}

				Bukkit.getScheduler().runTask(DirtRules.getPlugin(), () -> getRulesCallback.onSuccess(rules));
			} catch (SQLException ignored) {}
		});
	}

	public static void updatePlayerTrackerAgree(final UUID player) {
		Bukkit.getScheduler().runTaskAsynchronously(DirtRules.getPlugin(), () -> {
			try (Connection connection = Database.getConnection();
			     PreparedStatement updateStatement = connection.prepareStatement("UPDATE TRACKER SET tracker_accepted = true WHERE tracker_playerUuid = ?")) {
				updateStatement.setString(1, player.toString());
				updateStatement.execute();
			} catch (SQLException ignored) {}
		});
	}

	public static void resetPlayerTracker() {
		Bukkit.getScheduler().runTaskAsynchronously(DirtRules.getPlugin(), () -> {
			try (Connection connection = Database.getConnection();
			     PreparedStatement resetStatement = connection.prepareStatement("UPDATE TRACKER set tracker_accepted = false")) {
				resetStatement.execute();
				Bukkit.getScheduler().runTask(DirtRules.getPlugin(), PlayerTracker::update);
			} catch (SQLException ignored) {}
		});
	}
}
