package com.uthm.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MatchInfoSettingsPage extends JFrame {
    private JFrame parentSettingsPage;
    private GamePage activeGamePage;

    private boolean isTimerOn = true;
    private boolean isSpacesOn = true;
    private boolean isCountersOn = true;

    private ToggleSlider sliderTimer;
    private ToggleSlider sliderSpaces;
    private ToggleSlider sliderCounters;

    // Custom Background Color matched exactly to your layouts
    private final Color bgCustomColor = new Color(245, 247, 250);

    public MatchInfoSettingsPage(JFrame parentSettingsPage, GamePage activeGamePage) {
        this.parentSettingsPage = parentSettingsPage;
        this.activeGamePage = activeGamePage;

        // Sync states on initialization from active game page OR the parent settings memory
        if (activeGamePage != null) {
            this.isTimerOn = activeGamePage.isTimerVisible();
            this.isSpacesOn = activeGamePage.isBoardInfoVisible();
            this.isCountersOn = activeGamePage.isPlayerCounterVisible();
        } else if (parentSettingsPage instanceof GeneralSettingsPage) {
            GeneralSettingsPage gsp = (GeneralSettingsPage) parentSettingsPage;
            this.isTimerOn = gsp.getMetricTimer();
            this.isSpacesOn = gsp.getMetricSpaces();
            this.isCountersOn = gsp.getMetricCounters();
        }

        setTitle("Settings");
        setSize(340, 395); // Standardized with GeneralSettingsPage
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(bgCustomColor);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(bgCustomColor);
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 26));
        headerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Header Back Button Navigation
        JLabel lblBack = new JLabel(SVGIconHelper.getSVGIcon("back", 24, 24));
        lblBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                syncMetricsBackToSettings();
                parentSettingsPage.setVisible(true);
                dispose();
            }
        });
        headerPanel.add(lblBack, BorderLayout.WEST);
        mainPanel.add(headerPanel);
        mainPanel.add(Box.createVerticalStrut(4));

        // 1. CENTERED SETTINGS TITLE TITLE
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        titlePanel.setBackground(bgCustomColor);
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        titlePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblTitle = new JLabel("Settings");
        lblTitle.setHorizontalTextPosition(SwingConstants.RIGHT);
        lblTitle.setIconTextGap(8);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(AppStyle.TEXT_DARK);
        titlePanel.add(lblTitle);
        mainPanel.add(titlePanel);

        mainPanel.add(Box.createVerticalStrut(10));

        // 2. MATCH INFO SUBTEXT SUBHEADER (Perfectly mimics "General" section tag style)
        JLabel lblMatchSection = new JLabel("Match Info");
        lblMatchSection.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMatchSection.setForeground(Color.GRAY);
        lblMatchSection.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(lblMatchSection);
        mainPanel.add(Box.createVerticalStrut(4));

        // Toggles Configurations Setup
        sliderTimer = new ToggleSlider(isTimerOn);
        sliderSpaces = new ToggleSlider(isSpacesOn);
        sliderCounters = new ToggleSlider(isCountersOn);

        JPanel boxTimer = createToggleRow(
                "Match Timer",
                "Keep track of how long the match takes",
                "timer",
                sliderTimer,
                1
        );

        JPanel boxSpaces = createToggleRow(
                "Board Info",
                "Displays number of spots taken",
                "block_used",
                sliderSpaces,
                2
        );

        JPanel boxCounters = createToggleRow(
                "Player Counter",
                "Human or bot wins count",
                "person win",
                sliderCounters,
                3
        );

        mainPanel.add(boxTimer);
        mainPanel.add(Box.createVerticalStrut(8));
        mainPanel.add(boxSpaces);
        mainPanel.add(Box.createVerticalStrut(8));
        mainPanel.add(boxCounters);

        mainPanel.add(Box.createVerticalStrut(16));

        // 3. RESET BUTTON ROW CONTAINER (Centered structural row layout)
        JPanel resetWrapperRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        resetWrapperRow.setBackground(bgCustomColor);
        resetWrapperRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        resetWrapperRow.setMaximumSize(new Dimension(316, 32));

        JButton btnReset = new JButton("Reset to Default");
        btnReset.setIcon(SVGIconHelper.getSVGIcon("reset.svg", 14, 14));
        btnReset.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnReset.setIconTextGap(6);
        btnReset.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnReset.setForeground(new Color(0, 0, 0));
        btnReset.setBackground(Color.WHITE);
        btnReset.setBorder(BorderFactory.createLineBorder(new Color(230, 232, 235), 1));
        btnReset.setPreferredSize(new Dimension(135, 30));
        btnReset.setFocusPainted(false);
        btnReset.setFocusable(false);
        btnReset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnReset.addActionListener(e -> {
            isTimerOn = true;
            isSpacesOn = true;
            isCountersOn = true;

            sliderTimer.setOn(true);
            sliderSpaces.setOn(true);
            sliderCounters.setOn(true);

            syncMetricsBackToSettings();
        });

        resetWrapperRow.add(btnReset);
        mainPanel.add(resetWrapperRow);

        mainPanel.add(Box.createVerticalGlue());
        add(mainPanel);
    }

    private JPanel createToggleRow(String titleText, String subtext, String iconName, ToggleSlider slider, int id) {
        JPanel row = new JPanel(new BorderLayout());
        row.setBackground(Color.WHITE);
        row.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 232, 235), 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        row.setMaximumSize(new Dimension(316, 54));
        row.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel leftSide = new JPanel(new GridBagLayout());
        leftSide.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblIcon = new JLabel(SVGIconHelper.getSVGIcon(iconName, 24, 24));
        lblIcon.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        leftSide.add(lblIcon, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel lblMainTitle = new JLabel(titleText);
        lblMainTitle.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblMainTitle.setForeground(AppStyle.TEXT_DARK);
        leftSide.add(lblMainTitle, gbc);

        gbc.gridy = 1;
        JLabel lblSubtext = new JLabel(subtext);
        lblSubtext.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblSubtext.setForeground(new Color(120, 124, 130));
        leftSide.add(lblSubtext, gbc);

        row.add(leftSide, BorderLayout.WEST);
        row.add(slider, BorderLayout.EAST);

        row.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        MouseAdapter rowClickAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean state = !slider.isOn();
                slider.setOn(state);
                if (id == 1) isTimerOn = state;
                if (id == 2) isSpacesOn = state;
                if (id == 3) isCountersOn = state;

                syncMetricsBackToSettings();
            }
        };
        row.addMouseListener(rowClickAdapter);
        leftSide.addMouseListener(rowClickAdapter);
        lblMainTitle.addMouseListener(rowClickAdapter);
        lblSubtext.addMouseListener(rowClickAdapter);
        slider.addMouseListener(rowClickAdapter);

        return row;
    }

    private void syncMetricsBackToSettings() {
        if (parentSettingsPage instanceof GeneralSettingsPage) {
            GeneralSettingsPage gsp = (GeneralSettingsPage) parentSettingsPage;

            gsp.setMetricStates(isTimerOn, isSpacesOn, isCountersOn);

            DBConnection.saveSettings(
                    gsp.getSavedBoardSize(),
                    gsp.getSavedGamemode(),
                    isTimerOn,
                    isSpacesOn,
                    isCountersOn,
                    gsp.getAiDifficulty(),
                    gsp.getStartingSymbol()
            );

            if (activeGamePage != null) {
                activeGamePage.updateMatchInfoSettings(isTimerOn, isSpacesOn, isCountersOn);
            }
        }
    }

    private static class ToggleSlider extends JPanel {
        private boolean isOn;
        private int thumbX;
        private Timer timer;

        public ToggleSlider(boolean startOn) {
            this.isOn = startOn;
            setPreferredSize(new Dimension(34, 20));
            setBackground(Color.WHITE);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            thumbX = isOn ? 16 : 2;
        }

        public boolean isOn() { return isOn; }

        public void setOn(boolean on) {
            if (this.isOn == on) return;
            this.isOn = on;
            if (timer != null && timer.isRunning()) timer.stop();

            int targetX = isOn ? 16 : 2;
            timer = new Timer(15, e -> {
                if (thumbX < targetX) {
                    thumbX += 2;
                    if (thumbX >= targetX) { thumbX = targetX; timer.stop(); }
                } else {
                    thumbX -= 2;
                    if (thumbX <= targetX) { thumbX = targetX; timer.stop(); }
                }
                repaint();
            });
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (isOn) {
                g2.setColor(new Color(32, 102, 123));
            } else {
                g2.setColor(new Color(220, 224, 230));
            }
            g2.fillRoundRect(0, 0, 34, 20, 20, 20);

            g2.setColor(Color.WHITE);
            g2.fillOval(thumbX, 2, 16, 16);
            g2.dispose();
        }
    }
}