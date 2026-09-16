package com.uthm.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class DBConnection {
    private static final String URL = "jdbc:mariadb://localhost:3306/tic_tac_toe_db";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            // Change this line to see the real culprit in your IDE console
            System.out.println("CRITICAL DB ERROR: " + e.toString());
            e.printStackTrace();
            return null;
        }
    }

    public static void saveSettings(int boardSize, String gamemode, boolean timer, boolean spaces, boolean counters,
                                    String difficulty, String symbol) {
        Connection conn = getConnection();
        if (conn == null) {
            System.out.println("Cannot save settings: database connection is null.");
            return;
        }

        String checkQuery = "SELECT COUNT(*) FROM game_settings WHERE id = 1";
        String insertQuery = "INSERT INTO game_settings (id, board_size, gamemode, is_timer, is_spaces, is_counters, ai_difficulty, starting_symbol) VALUES (1, ?, ?, ?, ?, ?, ?, ?)";
        String updateQuery = "UPDATE game_settings SET board_size = ?, gamemode = ?, is_timer = ?, is_spaces = ?, is_counters = ?, ai_difficulty = ?, starting_symbol = ? WHERE id = 1";

        try {
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            boolean exists = rs.getInt(1) > 0;
            rs.close();
            checkStmt.close();

            if (exists) {
                try (PreparedStatement ps = conn.prepareStatement(updateQuery)) {
                    ps.setInt(1, boardSize);
                    ps.setString(2, gamemode);
                    ps.setBoolean(3, timer);
                    ps.setBoolean(4, spaces);
                    ps.setBoolean(5, counters);
                    ps.setString(6, difficulty);
                    ps.setString(7, symbol);
                    ps.executeUpdate();
                    System.out.println("Settings updated in MySQL successfully.");
                }
            } else {
                try (PreparedStatement ps = conn.prepareStatement(insertQuery)) {
                    ps.setInt(1, boardSize);
                    ps.setString(2, gamemode);
                    ps.setBoolean(3, timer);
                    ps.setBoolean(4, spaces);
                    ps.setBoolean(5, counters);
                    ps.setString(6, difficulty);
                    ps.setString(7, symbol);
                    ps.executeUpdate();
                    System.out.println("Settings inserted in MySQL successfully.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch (Exception ignored) {}
        }
    }

    public static Map<String, Object> loadSettings() {
        Map<String, Object> settings = new HashMap<>();
        Connection conn = getConnection();
        if (conn == null) return settings;

        String query = "SELECT * FROM game_settings WHERE id = 1";
        try (PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                settings.put("board_size", rs.getInt("board_size"));
                settings.put("gamemode", rs.getString("gamemode"));
                settings.put("is_timer", rs.getBoolean("is_timer"));
                settings.put("is_spaces", rs.getBoolean("is_spaces"));
                settings.put("is_counters", rs.getBoolean("is_counters"));
                settings.put("ai_difficulty", rs.getString("ai_difficulty"));
                settings.put("starting_symbol", rs.getString("starting_symbol"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch (Exception ignored) {}
        }
        return settings;
    }

    public static void saveMatch(int boardSize, String mode, String result, int totalMoves, int durationSeconds) {
        Connection conn = getConnection();
        if (conn == null) return;

        String query = "INSERT INTO match_history (board_size, gamemode, match_result, total_moves, duration_seconds) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, boardSize);
            ps.setString(2, mode);
            ps.setString(3, result);
            ps.setInt(4, totalMoves);
            ps.setInt(5, durationSeconds);
            ps.executeUpdate();
            System.out.println("Match saved to database.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch (Exception ignored) {}
        }
    }
}
