package com.uthm.system;

import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class GeneralSettingsPage extends JFrame {
    private JFrame welcomePage;
    private GamePage activeGamePage;

    private JComboBox<String> comboGamemode;
    private JComboBox<String> comboBoard;
    private JComboBox<String> comboDifficulty;
    private JComboBox<String> comboWinCondition;
    private JComboBox<String> comboStartingSymbol;

    private JPanel panelAdvancedGamemode;
    private JPanel panelAdvancedBoard;
    private ArrowLabel lblArrowGamemode;
    private ArrowLabel lblArrowBoard;

    private boolean isGamemodeExpanded = false;
    private boolean isBoardExpanded = false;

    private boolean metricTimer = true;
    private boolean metricSpaces = true;
    private boolean metricCounters = true;

    public void setMetricStates(boolean timer, boolean spaces, boolean counters) {
        this.metricTimer = timer;
        this.metricSpaces = spaces;
        this.metricCounters = counters;
    }

    public boolean getMetricTimer() { return metricTimer; }
    public boolean getMetricSpaces() { return metricSpaces; }
    public boolean getMetricCounters() { return metricCounters; }

    public GeneralSettingsPage(JFrame welcomePage, GamePage activeGamePage) {
        this.welcomePage = welcomePage;
        this.activeGamePage = activeGamePage;

        setTitle("Settings");
        setSize(340, 395);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(245, 247, 250));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        // CENTERED SETTINGS TITLE
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        titlePanel.setBackground(new Color(245, 247, 250));
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        titlePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblTitle = new JLabel("Settings");
        // DESIGN FIX: Upgraded Settings header icon size to prominent 28x28
        lblTitle.setHorizontalTextPosition(SwingConstants.RIGHT);
        lblTitle.setIconTextGap(8);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(AppStyle.TEXT_DARK);
        titlePanel.add(lblTitle);
        mainPanel.add(titlePanel);

        mainPanel.add(Box.createVerticalStrut(10));

        // GENERAL HEADER
        JLabel lblGeneralSection = new JLabel("General");
        lblGeneralSection.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblGeneralSection.setForeground(Color.GRAY);
        lblGeneralSection.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(lblGeneralSection);
        mainPanel.add(Box.createVerticalStrut(4));

        // GAMEMODE CONTAINER
        JPanel panelGamemodeContainer = new JPanel();
        panelGamemodeContainer.setLayout(new BoxLayout(panelGamemodeContainer, BoxLayout.Y_AXIS));
        panelGamemodeContainer.setBackground(Color.WHITE);
        panelGamemodeContainer.setBorder(BorderFactory.createLineBorder(new Color(230, 232, 235), 1));
        panelGamemodeContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelGamemodeContainer.setMaximumSize(new Dimension(316, 160));

        JPanel rowGamemodeHeader = new JPanel(new BorderLayout());
        rowGamemodeHeader.setBackground(Color.WHITE);
        rowGamemodeHeader.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
        rowGamemodeHeader.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel lblGamemodeTag = new JLabel("Gamemode");
        lblGamemodeTag.setIcon(SVGIconHelper.getSVGIcon("gamemode.svg", 16, 16));
        lblGamemodeTag.setHorizontalTextPosition(SwingConstants.RIGHT);
        lblGamemodeTag.setIconTextGap(6);
        lblGamemodeTag.setFont(new Font("Segoe UI", Font.BOLD, 13));

        comboGamemode = createStyledComboBox(new String[]{"Singleplayer", "Multiplayer"});
        comboGamemode.setPreferredSize(new Dimension(110, 25));

        lblArrowGamemode = new ArrowLabel(false);
        lblArrowGamemode.setForeground(Color.GRAY);
        lblArrowGamemode.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel rightGamemodePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 4, 0));
        rightGamemodePanel.setBackground(Color.WHITE);
        rightGamemodePanel.add(comboGamemode);
        rightGamemodePanel.add(lblArrowGamemode);

        rowGamemodeHeader.add(lblGamemodeTag, BorderLayout.WEST);
        rowGamemodeHeader.add(rightGamemodePanel, BorderLayout.EAST);
        panelGamemodeContainer.add(rowGamemodeHeader);

        panelAdvancedGamemode = new JPanel();
        panelAdvancedGamemode.setLayout(new BoxLayout(panelAdvancedGamemode, BoxLayout.Y_AXIS));
        panelAdvancedGamemode.setBackground(new Color(250, 251, 252));
        panelAdvancedGamemode.setVisible(false);
        panelAdvancedGamemode.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(240, 242, 245)));

        JPanel rowDiff = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
        rowDiff.setBackground(new Color(250, 251, 252));
        JLabel lblDifficulty = new JLabel("AI Difficulty:");
        lblDifficulty.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        comboDifficulty = createStyledComboBox(new String[]{"Simple", "Hard", "Impossible"});
        comboDifficulty.setPreferredSize(new Dimension(100, 25));
        rowDiff.add(lblDifficulty);
        rowDiff.add(comboDifficulty);

        JPanel rowStart = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
        rowStart.setBackground(new Color(250, 251, 252));
        JLabel lblStart = new JLabel("Start As:");
        lblStart.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        comboStartingSymbol = createStyledComboBox(new String[]{"X", "O"});
        comboStartingSymbol.setPreferredSize(new Dimension(100, 25));
        rowStart.add(lblStart);
        rowStart.add(comboStartingSymbol);

        panelAdvancedGamemode.add(rowDiff);
        panelAdvancedGamemode.add(rowStart);
        panelGamemodeContainer.add(panelAdvancedGamemode);

        mainPanel.add(panelGamemodeContainer);
        mainPanel.add(Box.createVerticalStrut(6));

        // BOARD CONTAINER
        JPanel panelBoardContainer = new JPanel();
        panelBoardContainer.setLayout(new BoxLayout(panelBoardContainer, BoxLayout.Y_AXIS));
        panelBoardContainer.setBackground(Color.WHITE);
        panelBoardContainer.setBorder(BorderFactory.createLineBorder(new Color(230, 232, 235), 1));
        panelBoardContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelBoardContainer.setMaximumSize(new Dimension(316, 120));

        JPanel rowBoardHeader = new JPanel(new BorderLayout());
        rowBoardHeader.setBackground(Color.WHITE);
        rowBoardHeader.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
        rowBoardHeader.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel lblBoardTag = new JLabel("Board");
        lblBoardTag.setIcon(SVGIconHelper.getSVGIcon("board.svg", 16, 16));
        lblBoardTag.setHorizontalTextPosition(SwingConstants.RIGHT);
        lblBoardTag.setIconTextGap(6);
        lblBoardTag.setFont(new Font("Segoe UI", Font.BOLD, 13));

        comboBoard = createStyledComboBox(new String[]{"Default (3x3)", "4x4 Layout", "5x5 Layout", "6x6 Layout"});
        comboBoard.setPreferredSize(new Dimension(110, 25));

        lblArrowBoard = new ArrowLabel(false);
        lblArrowBoard.setForeground(Color.GRAY);
        lblArrowBoard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel rightBoardPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 4, 0));
        rightBoardPanel.setBackground(Color.WHITE);
        rightBoardPanel.add(comboBoard);
        rightBoardPanel.add(lblArrowBoard);

        rowBoardHeader.add(lblBoardTag, BorderLayout.WEST);
        rowBoardHeader.add(rightBoardPanel, BorderLayout.EAST);
        panelBoardContainer.add(rowBoardHeader);

        panelAdvancedBoard = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
        panelAdvancedBoard.setBackground(new Color(250, 251, 252));
        panelAdvancedBoard.setVisible(false);
        panelAdvancedBoard.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(240, 242, 245)));
        JLabel lblWinCond = new JLabel("Win Condition:");
        lblWinCond.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        comboWinCondition = createStyledComboBox(new String[]{"Default", "3 in a row", "4 in a row", "5 in a row"});
        comboWinCondition.setPreferredSize(new Dimension(100, 25));
        panelAdvancedBoard.add(lblWinCond);
        panelAdvancedBoard.add(comboWinCondition);
        panelBoardContainer.add(panelAdvancedBoard);

        comboBoard.addActionListener(e -> {
            String selection = (String) comboBoard.getSelectedItem();
            Object currentWinOption = comboWinCondition.getSelectedItem();

            comboWinCondition.removeAllItems();
            comboWinCondition.addItem("Default");

            if (selection == null || selection.equalsIgnoreCase("Default (3x3)")) {
                comboWinCondition.addItem("3 in a row");
            } else if (selection.equalsIgnoreCase("4x4 Layout")) {
                comboWinCondition.addItem("3 in a row");
                comboWinCondition.addItem("4 in a row");
            } else if (selection.equalsIgnoreCase("5x5 Layout")) {
                comboWinCondition.addItem("3 in a row");
                comboWinCondition.addItem("4 in a row");
                comboWinCondition.addItem("5 in a row");
            } else if (selection.equalsIgnoreCase("6x6 Layout")) {
                comboWinCondition.addItem("3 in a row");
                comboWinCondition.addItem("4 in a row");
                comboWinCondition.addItem("5 in a row");
            }

            comboWinCondition.setSelectedItem(currentWinOption);
            if (comboWinCondition.getSelectedIndex() == -1) {
                comboWinCondition.setSelectedIndex(0);
            }
        });

        mainPanel.add(panelBoardContainer);
        mainPanel.add(Box.createVerticalStrut(10));

        // MATCH INFO SECTION
        JLabel lblMatchSection = new JLabel("Match Info");
        lblMatchSection.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMatchSection.setForeground(Color.GRAY);
        lblMatchSection.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(lblMatchSection);
        mainPanel.add(Box.createVerticalStrut(4));

        JPanel panelVisibilityContainer = new JPanel(new BorderLayout());
        panelVisibilityContainer.setBackground(Color.WHITE);
        panelVisibilityContainer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 232, 235), 1),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));
        panelVisibilityContainer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelVisibilityContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelVisibilityContainer.setMaximumSize(new Dimension(316, 34));

        JLabel lblVisibilityTag = new JLabel("Match Info Metrics");
        lblVisibilityTag.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblVisibilityTag.setForeground(AppStyle.TEXT_DARK);

        JLabel lblArrowNav = new ArrowLabel(false);
        lblArrowNav.setForeground(Color.GRAY);

        panelVisibilityContainer.add(lblVisibilityTag, BorderLayout.WEST);
        panelVisibilityContainer.add(lblArrowNav, BorderLayout.EAST);
        mainPanel.add(panelVisibilityContainer);

        // OPTIMIZED ACTION BUTTONS REGION (Closer gap to top settings layouts)
        mainPanel.add(Box.createVerticalStrut(16));

        // RESET BUTTON ROW (Perfectly Centered)
        JPanel resetWrapperRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        resetWrapperRow.setBackground(new Color(245, 247, 250));
        resetWrapperRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        resetWrapperRow.setMaximumSize(new Dimension(316, 30));

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
        resetWrapperRow.add(btnReset);
        mainPanel.add(resetWrapperRow);

        mainPanel.add(Box.createVerticalStrut(10));

        // APPLY SETTINGS BUTTON ROW (Perfectly Centered Below Reset)
        JPanel applyWrapperRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        applyWrapperRow.setBackground(new Color(245, 247, 250));
        applyWrapperRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        applyWrapperRow.setMaximumSize(new Dimension(316, 36));

        JButton btnSave = AppStyle.createAccentButton("Apply Settings");
        btnSave.setPreferredSize(new Dimension(135, 36));
        btnSave.setFocusPainted(false);
        btnSave.setFocusable(false);
        applyWrapperRow.add(btnSave);
        mainPanel.add(applyWrapperRow);

        mainPanel.add(Box.createVerticalGlue());
        add(mainPanel);

        // PERSISTENCE ENGINE SYNC
        Map<String, Object> dbValues = DBConnection.loadSettings();
        if (dbValues != null && !dbValues.isEmpty()) {
            if (dbValues.containsKey("is_timer")) this.metricTimer = (boolean) dbValues.get("is_timer");
            if (dbValues.containsKey("is_spaces")) this.metricSpaces = (boolean) dbValues.get("is_spaces");
            if (dbValues.containsKey("is_counters")) this.metricCounters = (boolean) dbValues.get("is_counters");

            if (dbValues.containsKey("gamemode")) {
                comboGamemode.setSelectedItem(dbValues.get("gamemode"));
            }
            if (dbValues.containsKey("board_size")) {
                int size = (int) dbValues.get("board_size");
                if (size == 3) comboBoard.setSelectedIndex(0);
                else if (size == 4) comboBoard.setSelectedIndex(1);
                else if (size == 5) comboBoard.setSelectedIndex(2);
                else if (size == 6) comboBoard.setSelectedIndex(3);
            }
            if (dbValues.containsKey("ai_difficulty")) {
                comboDifficulty.setSelectedItem(dbValues.get("ai_difficulty"));
            }
            if (dbValues.containsKey("starting_symbol")) {
                comboStartingSymbol.setSelectedItem(dbValues.get("starting_symbol"));
            }
        }

        // INPUT EVENT HOOKS
        lblArrowGamemode.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { toggleGamemode(); }
        });
        rowGamemodeHeader.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { toggleGamemode(); }
        });

        lblArrowBoard.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { toggleBoard(); }
        });
        rowBoardHeader.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { toggleBoard(); }
        });

        panelVisibilityContainer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MatchInfoSettingsPage visibilityPage = new MatchInfoSettingsPage(GeneralSettingsPage.this, activeGamePage);
                visibilityPage.setVisible(true);
                setVisible(false);
            }
        });

        btnReset.addActionListener(e -> {
            comboGamemode.setSelectedIndex(0);
            comboBoard.setSelectedIndex(0);
            comboDifficulty.setSelectedIndex(0);
            comboWinCondition.setSelectedIndex(0);
            comboStartingSymbol.setSelectedIndex(0);
            this.metricTimer = true;
            this.metricSpaces = true;
            this.metricCounters = true;
        });

        btnSave.addActionListener(e -> {
            int boardSize = getSavedBoardSize();
            String mode = getSavedGamemode();
            String difficulty = getAiDifficulty();
            String symbol = getStartingSymbol();

            // 1. Save data permanently to local database state profile
            DBConnection.saveSettings(boardSize, mode, metricTimer, metricSpaces, metricCounters, difficulty, symbol);

            // 2. If adjusting parameters during an active game session, run live updates
            if (activeGamePage != null) {
                // Critical Fix: Push board dimensions and gamemode configuration first.
                // This triggers buildBoard() internally to redraw rows/columns and clear old moves!
                activeGamePage.setBoardSizeAndGamemode(boardSize, mode);

                // Push game target matching rule properties
                activeGamePage.setCustomMatchRules(getCustomWinCondition(), difficulty);

                // Toggle display bars visibility metrics
                activeGamePage.updateMatchInfoSettings(metricTimer, metricSpaces, metricCounters);

                // Re-evaluate symbol orientation allocations and initialize board
                if ("O".equals(symbol)) {
                    // X symbol string, O symbol string, starting turn symbol character
                    activeGamePage.updateGameSymbols("X", "O", "O");
                } else {
                    activeGamePage.updateGameSymbols("X", "O", "X");
                }

                // Push game screen frame window back into visibility layer focus
                activeGamePage.setVisible(true);
            } else {
                // Otherwise, safely fallback back out to welcome title view frame
                welcomePage.setVisible(true);
            }

            dispose();
        });
    }

    private void toggleGamemode() {
        isGamemodeExpanded = !isGamemodeExpanded;
        panelAdvancedGamemode.setVisible(isGamemodeExpanded);
        lblArrowGamemode.setExpanded(isGamemodeExpanded);
        updateDynamicWindowSize();
    }

    private void toggleBoard() {
        isBoardExpanded = !isBoardExpanded;
        panelAdvancedBoard.setVisible(isBoardExpanded);
        lblArrowBoard.setExpanded(isBoardExpanded);
        updateDynamicWindowSize();
    }

    private static class ArrowLabel extends JLabel {
        private boolean expanded;
        public ArrowLabel(boolean expanded) {
            this.expanded = expanded;
            setPreferredSize(new Dimension(16, 16));
        }
        public void setExpanded(boolean expanded) { this.expanded = expanded; repaint(); }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getForeground());
            g2.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            int w = getWidth(), h = getHeight();
            int size = 6;
            int cx = w / 2;
            int cy = h / 2;

            if (expanded) {
                g2.drawLine(cx - size / 2, cy + size / 4, cx, cy - size / 4);
                g2.drawLine(cx, cy - size / 4, cx + size / 2, cy + size / 4);
            } else {
                g2.drawLine(cx - size / 2, cy - size / 4, cx, cy + size / 4);
                g2.drawLine(cx, cy + size / 4, cx + size / 2, cy - size / 4);
            }
            g2.dispose();
        }
    }

    private JComboBox<String> createStyledComboBox(String[] items) {
        JComboBox<String> combo = new JComboBox<String>(items) {
            @Override
            public void updateUI() {
                setUI(new BasicComboBoxUI() {
                    @Override
                    protected JButton createArrowButton() {
                        JButton button = new JButton() {
                            @Override
                            protected void paintComponent(Graphics g) {
                                Graphics2D g2 = (Graphics2D) g.create();
                                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                                g2.setColor(getForeground());
                                g2.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

                                int w = getWidth(), h = getHeight();
                                int size = 6;
                                int cx = w / 2;
                                int cy = h / 2;

                                if (comboBox.isPopupVisible()) {
                                    g2.drawLine(cx - size / 2, cy + size / 4, cx, cy - size / 4);
                                    g2.drawLine(cx, cy - size / 4, cx + size / 2, cy + size / 4);
                                } else {
                                    g2.drawLine(cx - size / 2, cy - size / 4, cx, cy + size / 4);
                                    g2.drawLine(cx, cy + size / 4, cx + size / 2, cy - size / 4);
                                }
                                g2.dispose();
                            }
                        };
                        button.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
                        button.setContentAreaFilled(false);
                        button.setFocusPainted(false);
                        button.setFocusable(false);
                        button.setForeground(new Color(140, 144, 150));
                        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        return button;
                    }
                });
            }
        };
        combo.setBackground(Color.WHITE);
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        combo.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 0));
        combo.setFocusable(false);
        return combo;
    }

    private void updateDynamicWindowSize() {
        int height = 395;
        if (isGamemodeExpanded) height += 64;
        if (isBoardExpanded) height += 32;
        setSize(340, height);
        revalidate();
        repaint();
    }

    public void setActiveGamePage(GamePage activeGamePage) { this.activeGamePage = activeGamePage; }

    public int getSavedBoardSize() {
        String selected = (String) comboBoard.getSelectedItem();
        if (selected == null || selected.equalsIgnoreCase("Default (3x3)")) return 3;
        try { return Integer.parseInt(selected.substring(0, 1)); } catch (Exception e) { return 3; }
    }

    public String getSavedGamemode() {
        return (comboGamemode.getSelectedItem() != null) ? (String) comboGamemode.getSelectedItem() : "Singleplayer";
    }

    public String getAiDifficulty() { return (String) comboDifficulty.getSelectedItem(); }
    public String getStartingSymbol() { return (String) comboStartingSymbol.getSelectedItem(); }

    public int getCustomWinCondition() {
        String selected = (String) comboWinCondition.getSelectedItem();
        if (selected == null || selected.equalsIgnoreCase("Default")) return 3;
        try { return Integer.parseInt(selected.substring(0, 1)); } catch (Exception e) { return 3; }
    }
}