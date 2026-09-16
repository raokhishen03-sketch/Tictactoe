package com.uthm.system;

import javax.swing.*;
import java.awt.*;

public class WelcomeScreen extends JFrame {
    private JButton btnPlay;
    private JButton btnSettings;
    private JFrame generalSettingsPage;

    public WelcomeScreen() {
        setTitle("Tic-Tac-Toe");
        setSize(290, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(AppStyle.BG_GRAY);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(45, 25, 45, 25));

        JLabel lblTitle = new JLabel("Tic-Tac-Toe");
        lblTitle.setFont(AppStyle.TITLE_FONT);
        lblTitle.setForeground(AppStyle.TEXT_DARK);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitle);

        mainPanel.add(Box.createVerticalStrut(45));

        // 1. PLAY BUTTON (Pulls custom properties live from settings frame)
        btnPlay = AppStyle.createRoundedButton("Play");
        btnPlay.setIcon(SVGIconHelper.getSVGIcon("play.svg", 18, 18));
        btnPlay.setIconTextGap(12);
        btnPlay.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnPlay.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));

        btnPlay.addActionListener(e -> {
            // Lazy initialization of the settings page if the user never clicked "Settings"
            if (generalSettingsPage == null) {
                generalSettingsPage = new GeneralSettingsPage(WelcomeScreen.this, null);
            }

            GeneralSettingsPage gsp = (GeneralSettingsPage) generalSettingsPage;

            // Extract configured setup selections straight from UI configuration states
            int chosenSize = gsp.getSavedBoardSize();
            String chosenMode = gsp.getSavedGamemode();
            int chosenWinTarget = gsp.getCustomWinCondition();
            String chosenDifficulty = gsp.getAiDifficulty();
            String startingSymbol = gsp.getStartingSymbol();

            // Create a custom instance of GamePage passing all state variables through the constructor
            GamePage gamePage = new GamePage(this, chosenSize, chosenMode, startingSymbol);

            // Apply custom targets, bot difficulties, and panel metric states
            gamePage.setCustomMatchRules(chosenWinTarget, chosenDifficulty);
            gamePage.updateMatchInfoSettings(gsp.getMetricTimer(), gsp.getMetricSpaces(), gsp.getMetricCounters());

            // Connect the active game view reference back up to settings
            gsp.setActiveGamePage(gamePage);

            gamePage.setVisible(true);
            this.setVisible(false);
        });
        mainPanel.add(btnPlay);

        mainPanel.add(Box.createVerticalStrut(15));

        // 2. SETTINGS BUTTON
        btnSettings = AppStyle.createRoundedButton("Settings");
        btnSettings.setIcon(SVGIconHelper.getSVGIcon("settings.svg", 24, 24));
        btnSettings.setIconTextGap(12);
        btnSettings.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSettings.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));

        btnSettings.addActionListener(e -> {
            if (generalSettingsPage == null) {
                generalSettingsPage = new GeneralSettingsPage(WelcomeScreen.this, null);
            }
            generalSettingsPage.setVisible(true);
            WelcomeScreen.this.setVisible(false);
        });
        mainPanel.add(btnSettings);

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            WelcomeScreen welcomeScreen = new WelcomeScreen();
            welcomeScreen.setVisible(true);
        });
    }
}