package com.uthm.system;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePage extends JFrame {
    private int customWinTarget = 3;
    private String aiDifficulty = "Hard";

    private String symbolX = "X";
    private String symbolO = "O";
    private String initialPlayerSymbol = "X"; // Holds the starting symbol preference chosen in settings ("X" or "O")

    private JFrame welcomePage;
    private JPanel gridPanel;
    private JLabel lblStatus, lblSpots, lblTimer, lblScoreX, lblScoreO;
    private JPanel panelTimer, panelSpots, panelScoreX, panelScoreO;
    private JLabel lblIconScoreO;

    private AppStyle.CustomRoundedButton[][] boardButtons;

    private JButton btnMute, btnBack, btnPlayAgain;
    private javax.swing.Timer matchTimer;

    private int boardSize;
    private String gamemode;
    private boolean isXTurn = true;
    private int totalMoves = 0;
    private int scoreX = 0, scoreO = 0;
    private boolean gameActive = true;
    private int totalSecondsElapsed = 0;
    private int movesMade = 0;

    private boolean isInputLocked = false;

    private boolean isTimerVisible = true;
    private boolean isBoardInfoVisible = true;
    private boolean isPlayerCounterVisible = true;

    private Random random = new Random();
    private ArrayList<AppStyle.CustomRoundedButton> winningButtons = new ArrayList<>();

    private static final Color TILE_PLAYING_GREY = new Color(140, 144, 153);
    private static final Color TILE_WIN_WHITE = Color.WHITE;
    private static final Color TILE_DEFAULT_BG = Color.WHITE;
    private static final Color TILE_SELECTED_BG = new Color(235, 237, 240);

    // UPDATED CONSTRUCTOR: Accept startingSymbol parameter directly from the selection menu
    public GamePage(JFrame welcomePage, int boardSize, String gamemode, String startingSymbol) {
        this.welcomePage = welcomePage;
        this.boardSize = boardSize;
        this.gamemode = gamemode;
        this.initialPlayerSymbol = (startingSymbol != null) ? startingSymbol : "X";

        setTitle("Tic-Tac-Toe");
        setSize(340, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        AudioController.startMusic();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(AppStyle.BG_GRAY);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(4, 15, 10, 15));

        JPanel navRow = new JPanel(new BorderLayout());
        navRow.setBackground(AppStyle.BG_GRAY);
        navRow.setMaximumSize(new Dimension(340, 35));

        btnBack = new JButton();
        btnBack.setIcon(SVGIconHelper.getSVGIcon("back", 22, 22));
        btnBack.setBorderPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setFocusPainted(false);
        btnBack.addActionListener(e -> {
            if (matchTimer != null) matchTimer.stop();
            AudioController.stopMusic();
            welcomePage.setVisible(true);
            dispose();
        });

        btnMute = new JButton();
        btnMute.setIcon(SVGIconHelper.getSVGIcon(AudioController.isMuted() ? "mute" : "unmute", 22, 22));
        btnMute.setBorderPainted(false);
        btnMute.setContentAreaFilled(false);
        btnMute.setFocusPainted(false);
        btnMute.addActionListener(e -> {
            AudioController.toggleMute();
            btnMute.setIcon(SVGIconHelper.getSVGIcon(AudioController.isMuted() ? "mute" : "unmute", 22, 22));
        });

        navRow.add(btnBack, BorderLayout.WEST);
        navRow.add(btnMute, BorderLayout.EAST);
        mainPanel.add(navRow);

        lblStatus = new JLabel("");
        lblStatus.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblStatus);

        JPanel statsRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        statsRow.setBackground(AppStyle.BG_GRAY);
        statsRow.setMaximumSize(new Dimension(340, 30));

        panelTimer = createStatPanel("timer", "0");
        lblTimer = (JLabel) panelTimer.getComponent(1);
        panelSpots = createStatPanel("block_used", "0");
        lblSpots = (JLabel) panelSpots.getComponent(1);
        panelScoreX = createStatPanel("person win", "0");
        lblScoreX = (JLabel) panelScoreX.getComponent(1);

        String secondIconKey = gamemode.equalsIgnoreCase("Multiplayer") ? "person2" : "bot win";
        panelScoreO = createStatPanel(secondIconKey, "0");
        lblIconScoreO = (JLabel) panelScoreO.getComponent(0);
        lblScoreO = (JLabel) panelScoreO.getComponent(1);

        statsRow.add(panelTimer);
        statsRow.add(panelSpots);
        statsRow.add(panelScoreX);
        statsRow.add(panelScoreO);
        mainPanel.add(statsRow);

        gridPanel = new JPanel();
        gridPanel.setBackground(AppStyle.BG_GRAY);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
        mainPanel.add(gridPanel);

        JPanel btnWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 4));
        btnWrapper.setBackground(AppStyle.BG_GRAY);
        btnWrapper.setMaximumSize(new Dimension(340, 50));

        btnPlayAgain = new JButton("Play Again");
        btnPlayAgain.setIcon(SVGIconHelper.getSVGIcon("try again.svg", 16, 16));
        btnPlayAgain.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnPlayAgain.setIconTextGap(8);

        btnPlayAgain.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnPlayAgain.setForeground(AppStyle.TEXT_DARK);
        btnPlayAgain.setBackground(AppStyle.BUTTON_BG);
        btnPlayAgain.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppStyle.BORDER_COLOR, 1, true),
                BorderFactory.createEmptyBorder(6, 20, 6, 20)
        ));
        btnPlayAgain.setFocusPainted(false);
        btnPlayAgain.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnPlayAgain.setOpaque(true);
        btnPlayAgain.setVisible(false);
        btnPlayAgain.addActionListener(e -> resetMatchEngine());
        btnWrapper.add(btnPlayAgain);
        mainPanel.add(btnWrapper);

        add(mainPanel);
        buildBoard();
        startMatchTimer();
        applyVisibilitySettings();
    }

    private JPanel createStatPanel(String iconKey, String text) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        p.setBackground(AppStyle.BG_GRAY);
        p.add(new JLabel(SVGIconHelper.getSVGIcon(iconKey, 20, 20)));
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        p.add(label);
        return p;
    }

    public void setCustomMatchRules(int winTarget, String difficulty) {
        this.customWinTarget = winTarget;
        this.aiDifficulty = difficulty;
    }

    public void updateMatchInfoSettings(boolean timerVisible, boolean spacesVisible, boolean countersVisible) {
        this.isTimerVisible = timerVisible;
        this.isBoardInfoVisible = spacesVisible;
        this.isPlayerCounterVisible = countersVisible;

        if (panelTimer != null) panelTimer.setVisible(timerVisible);
        if (panelSpots != null) panelSpots.setVisible(spacesVisible);
        if (panelScoreX != null) panelScoreX.setVisible(countersVisible);
        if (panelScoreO != null) panelScoreO.setVisible(countersVisible);

        revalidate();
        repaint();
    }

    private void applyVisibilitySettings() {
        if (panelTimer != null) panelTimer.setVisible(isTimerVisible);
        if (panelSpots != null) panelSpots.setVisible(isBoardInfoVisible);
        if (panelScoreX != null) panelScoreX.setVisible(isPlayerCounterVisible);
        if (panelScoreO != null) panelScoreO.setVisible(isPlayerCounterVisible);

        if (getContentPane() != null) {
            getContentPane().revalidate();
            getContentPane().repaint();
        }
    }

    public void updateGameSymbols(String symbolX, String symbolO, String initialPlayerSymbol) {
        this.symbolX = symbolX;
        this.symbolO = symbolO;
        this.initialPlayerSymbol = initialPlayerSymbol;

        if (lblIconScoreO != null) {
            String secondIconKey = gamemode.equalsIgnoreCase("Multiplayer") ? "person2" : "bot win";
            lblIconScoreO.setIcon(SVGIconHelper.getSVGIcon(secondIconKey, 20, 20));
        }

        buildBoard();
    }

    public void setBoardSizeAndGamemode(int size, String mode) {
        this.boardSize = size;
        this.gamemode = mode;
        buildBoard();
    }

    public boolean isTimerVisible() { return this.isTimerVisible; }
    public boolean isBoardInfoVisible() { return this.isBoardInfoVisible; }
    public boolean isPlayerCounterVisible() { return this.isPlayerCounterVisible; }

    private void buildBoard() {
        gridPanel.removeAll();
        gridPanel.setLayout(new GridLayout(boardSize, boardSize, 5, 5));
        boardButtons = new AppStyle.CustomRoundedButton[boardSize][boardSize];
        winningButtons.clear();
        totalMoves = 0;
        movesMade = 0;
        lblSpots.setText("0");
        gameActive = true;
        isInputLocked = false;

        if (gamemode.equalsIgnoreCase("Multiplayer")) {
            if ("X".equalsIgnoreCase(initialPlayerSymbol)) {
                isXTurn = true;
                lblStatus.setText("Player " + symbolX + "'s Turn");
            } else {
                isXTurn = false;
                lblStatus.setText("Player " + symbolO + "'s Turn");
            }
        } else {
            if ("X".equalsIgnoreCase(initialPlayerSymbol)) {
                isXTurn = true;
                lblStatus.setText("Your Turn");
            } else {
                isXTurn = false;
                lblStatus.setText("Bot is thinking...");
            }
        }

        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                AppStyle.CustomRoundedButton btn = new AppStyle.CustomRoundedButton("", false);
                btn.setFont(new Font("SansSerif", Font.BOLD, boardSize == 3 ? 32 : 22));
                btn.setBackground(TILE_DEFAULT_BG);
                btn.setForeground(TILE_PLAYING_GREY);

                final int currRow = r;
                final int currCol = c;
                btn.addActionListener(e -> handleGridSelection(currRow, currCol));
                // Clear any leftover client text attributes
                btn.putClientProperty("internalMark", "");
                boardButtons[r][c] = btn;
                gridPanel.add(btn);
            }
        }

        gridPanel.revalidate();
        gridPanel.repaint();

        if (gamemode.equalsIgnoreCase("Singleplayer") && !isXTurn) {
            isInputLocked = true;
            triggerBotDelayedMove();
        }
    }

    private void handleGridSelection(int r, int c) {
        if (!gameActive || isInputLocked) return;
        JButton btn = boardButtons[r][c];
        String internalMark = (String) btn.getClientProperty("internalMark");
        if (internalMark != null && !internalMark.isEmpty()) return;

        isInputLocked = true;
        String activeSymbol = isXTurn ? "X" : "O";
        executeTurnAction(r, c, activeSymbol);

        if (!gameActive) return;

        if (gamemode.equalsIgnoreCase("Singleplayer")) {
            isXTurn = !isXTurn;
            lblStatus.setText("Bot is thinking...");
            triggerBotDelayedMove();
        } else {
            isXTurn = !isXTurn;
            lblStatus.setText(isXTurn ? "Player " + symbolX + "'s Turn" : "Player " + symbolO + "'s Turn");
            isInputLocked = false;
        }
    }

    private void triggerBotDelayedMove() {
        Timer botDelay = new Timer(450, e -> {
            if (!gameActive) return;
            executeBotStrategy();
        });
        botDelay.setRepeats(false);
        botDelay.start();
    }

    private void executeTurnAction(int r, int c, String internalMark) {
        AppStyle.CustomRoundedButton btn = boardButtons[r][c];
        String displaySymbol = internalMark.equals("X") ? symbolX : symbolO;

        // FIXED: Track backend status with client property flags to keep tactical searches reliable
        btn.putClientProperty("internalMark", internalMark);
        btn.setText(displaySymbol);
        btn.setForeground(TILE_PLAYING_GREY);
        btn.setBackground(TILE_SELECTED_BG);

        totalMoves++;
        movesMade++;
        lblSpots.setText(String.valueOf(movesMade));

        if (evaluateWinner(r, c, internalMark)) {
            gameActive = false;
            isInputLocked = true;
            if (matchTimer != null) matchTimer.stop();
            highlightWinningRun();

            if (gamemode.equalsIgnoreCase("Multiplayer")) {
                if (internalMark.equals("X")) {
                    scoreX++;
                    lblScoreX.setText(String.valueOf(scoreX));
                    lblStatus.setText("Player " + symbolX + " Won!");
                } else {
                    scoreO++;
                    lblScoreO.setText(String.valueOf(scoreO));
                    lblStatus.setText("Player " + symbolO + " Won!");
                }
                AudioController.playWinSound();
            } else {
                boolean playerWon = internalMark.equalsIgnoreCase(initialPlayerSymbol);
                if (playerWon) {
                    if ("X".equalsIgnoreCase(initialPlayerSymbol)) scoreX++; else scoreO++;
                    lblStatus.setText("You Won!");
                } else {
                    if ("X".equalsIgnoreCase(initialPlayerSymbol)) scoreO++; else scoreX++;
                    lblStatus.setText("Bot Won!");
                }
                lblScoreX.setText(String.valueOf(scoreX));
                lblScoreO.setText(String.valueOf(scoreO));
                if (playerWon) {
                    AudioController.playWinSound();
                } else {
                    AudioController.playLoseSound();
                }
            }

            DBConnection.saveMatch(boardSize, gamemode, internalMark + " wins", totalMoves, totalSecondsElapsed);
            showPlayAgainButton();
            return;
        }

        if (totalMoves >= (boardSize * boardSize)) {
            gameActive = false;
            isInputLocked = true;
            if (matchTimer != null) matchTimer.stop();
            lblStatus.setText("Draw Match!");
            DBConnection.saveMatch(boardSize, gamemode, "Draw", totalMoves, totalSecondsElapsed);
            showPlayAgainButton();
            return;
        }

        if (gamemode.equalsIgnoreCase("Multiplayer") || isXTurn == ("X".equalsIgnoreCase(initialPlayerSymbol))) {
            isInputLocked = false;
        }
    }

    private void showPlayAgainButton() {
        btnPlayAgain.setVisible(true);
    }

    private void executeBotStrategy() {
        if (!gameActive) return;
        int[] move = null;
        switch (aiDifficulty) {
            case "Simple":
                move = getSimpleRandomMove();
                break;
            case "Impossible":
                move = getImpossibleMinimaxMove();
                break;
            case "Hard":
            default:
                move = getHardTacticalMove();
                break;
        }

        if (move != null) {
            String botMark = isXTurn ? "X" : "O";
            executeTurnAction(move[0], move[1], botMark);
            if (gameActive) {
                isXTurn = !isXTurn;
                lblStatus.setText("Your Turn");
                isInputLocked = false;
            }
        } else {
            isInputLocked = false;
        }
    }

    private int[] getSimpleRandomMove() {
        ArrayList<int[]> available = new ArrayList<>();
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                String mark = (String) boardButtons[r][c].getClientProperty("internalMark");
                if (mark == null || mark.isEmpty()) {
                    available.add(new int[]{r, c});
                }
            }
        }
        return available.isEmpty() ? null : available.get(random.nextInt(available.size()));
    }

    private int[] getHardTacticalMove() {
        int bestScore = -1;
        ArrayList<int[]> bestMoves = new ArrayList<>();
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                String mark = (String) boardButtons[r][c].getClientProperty("internalMark");
                if (mark == null || mark.isEmpty()) {
                    int score = calculateCellWeight(r, c, customWinTarget);
                    if (score > bestScore) {
                        bestScore = score;
                        bestMoves.clear();
                        bestMoves.add(new int[]{r, c});
                    } else if (score == bestScore) {
                        bestMoves.add(new int[]{r, c});
                    }
                }
            }
        }
        return bestMoves.isEmpty() ? getSimpleRandomMove() : bestMoves.get(random.nextInt(bestMoves.size()));
    }

    private int[] getImpossibleMinimaxMove() {
        int bestVal = Integer.MIN_VALUE;
        int[] bestMove = null;
        int depthLimit = boardSize == 3 ? 6 : 3;
        String botSymbol = initialPlayerSymbol.equalsIgnoreCase("X") ? "O" : "X";
        String humanSymbol = initialPlayerSymbol.equalsIgnoreCase("X") ? "X" : "O";

        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                String mark = (String) boardButtons[r][c].getClientProperty("internalMark");
                if (mark == null || mark.isEmpty()) {
                    boardButtons[r][c].putClientProperty("internalMark", botSymbol);
                    if (evaluateWinner(r, c, botSymbol)) {
                        boardButtons[r][c].putClientProperty("internalMark", "");
                        return new int[]{r, c};
                    }
                    boardButtons[r][c].putClientProperty("internalMark", "");
                }
            }
        }
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                String mark = (String) boardButtons[r][c].getClientProperty("internalMark");
                if (mark == null || mark.isEmpty()) {
                    boardButtons[r][c].putClientProperty("internalMark", humanSymbol);
                    if (evaluateWinner(r, c, humanSymbol)) {
                        boardButtons[r][c].putClientProperty("internalMark", "");
                        return new int[]{r, c};
                    }
                    boardButtons[r][c].putClientProperty("internalMark", "");
                }
            }
        }

        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                String mark = (String) boardButtons[r][c].getClientProperty("internalMark");
                if (mark == null || mark.isEmpty()) {
                    boardButtons[r][c].putClientProperty("internalMark", botSymbol);
                    totalMoves++;
                    int moveVal = minimax(depthLimit, false, Integer.MIN_VALUE, Integer.MAX_VALUE, r, c);
                    boardButtons[r][c].putClientProperty("internalMark", "");
                    totalMoves--;
                    if (moveVal > bestVal) {
                        bestVal = moveVal;
                        bestMove = new int[]{r, c};
                    }
                }
            }
        }
        return bestMove != null ? bestMove : getHardTacticalMove();
    }

    private int minimax(int depth, boolean isMax, int alpha, int beta, int lastR, int lastC) {
        String botSymbol = initialPlayerSymbol.equalsIgnoreCase("X") ? "O" : "X";
        String humanSymbol = initialPlayerSymbol.equalsIgnoreCase("X") ? "X" : "O";

        if (!isMax && evaluateWinner(lastR, lastC, botSymbol)) return 1000 - depth;
        if (isMax && evaluateWinner(lastR, lastC, humanSymbol)) return -1000 + depth;
        if (depth == 0 || totalMoves >= boardSize * boardSize) return 0;

        if (isMax) {
            int maxEval = Integer.MIN_VALUE;
            for (int r = 0; r < boardSize; r++) {
                for (int c = 0; c < boardSize; c++) {
                    String mark = (String) boardButtons[r][c].getClientProperty("internalMark");
                    if (mark == null || mark.isEmpty()) {
                        boardButtons[r][c].putClientProperty("internalMark", botSymbol);
                        totalMoves++;
                        int eval = minimax(depth - 1, false, alpha, beta, r, c);
                        boardButtons[r][c].putClientProperty("internalMark", "");
                        totalMoves--;
                        maxEval = Math.max(maxEval, eval);
                        alpha = Math.max(alpha, eval);
                        if (beta <= alpha) break;
                    }
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int r = 0; r < boardSize; r++) {
                for (int c = 0; c < boardSize; c++) {
                    String mark = (String) boardButtons[r][c].getClientProperty("internalMark");
                    if (mark == null || mark.isEmpty()) {
                        boardButtons[r][c].putClientProperty("internalMark", humanSymbol);
                        totalMoves++;
                        int eval = minimax(depth - 1, true, alpha, beta, r, c);
                        boardButtons[r][c].putClientProperty("internalMark", "");
                        totalMoves--;
                        minEval = Math.min(minEval, eval);
                        beta = Math.min(beta, eval);
                        if (beta <= alpha) break;
                    }
                }
            }
            return minEval;
        }
    }

    private int calculateCellWeight(int r, int c, int target) {
        int score = 0;
        int[][] dirs = {{0,1}, {1,0}, {1,1}, {1,-1}};
        for (int[] dir : dirs) score += evaluateDirection(r, c, dir[0], dir[1], target);
        return score;
    }

    private int evaluateDirection(int r, int c, int dr, int dc, int target) {
        String botSymbol = initialPlayerSymbol.equalsIgnoreCase("X") ? "O" : "X";
        String humanSymbol = initialPlayerSymbol.equalsIgnoreCase("X") ? "X" : "O";

        int maxBotCount = 0;
        int maxHumanCount = 0;

        for (int offset = -(target - 1); offset <= 0; offset++) {
            int oCount = 0;
            int xCount = 0;
            boolean validWindow = true;

            for (int i = 0; i < target; i++) {
                int index = offset + i;
                int nr = r + index * dr;
                int nc = c + index * dc;

                if (nr < 0 || nr >= boardSize || nc < 0 || nc >= boardSize) {
                    validWindow = false;
                    break;
                }

                if (nr != r || nc != c) {
                    String text = (String) boardButtons[nr][nc].getClientProperty("internalMark");
                    if (text != null) {
                        if (text.equals(botSymbol)) oCount++;
                        else if (text.equals(humanSymbol)) xCount++;
                    }
                }
            }

            if (validWindow) {
                if (oCount > 0 && xCount == 0) maxBotCount = Math.max(maxBotCount, oCount);
                if (xCount > 0 && oCount == 0) maxHumanCount = Math.max(maxHumanCount, xCount);
            }
        }

        if (maxBotCount == target - 1) return 100;
        if (maxHumanCount == target - 1) return 50;
        if (maxBotCount > 0) return maxBotCount * 2;
        if (maxHumanCount > 0) return maxHumanCount;
        return 1;
    }

    private boolean evaluateWinner(int row, int col, String internalMark) {
        winningButtons.clear();
        return checkDir(row, col, 0, 1, internalMark) ||
                checkDir(row, col, 1, 0, internalMark) ||
                checkDir(row, col, 1, 1, internalMark) ||
                checkDir(row, col, 1, -1, internalMark);
    }

    private boolean checkDir(int r, int c, int dR, int dC, String internalMark) {
        ArrayList<AppStyle.CustomRoundedButton> currentSequence = new ArrayList<>();

        for (int i = -(customWinTarget - 1); i <= (customWinTarget - 1); i++) {
            int tr = r + (i * dR);
            int tc = c + (i * dC);

            if (tr >= 0 && tr < boardSize && tc >= 0 && tc < boardSize) {
                String mark = (String) boardButtons[tr][tc].getClientProperty("internalMark");
                if (mark != null && mark.equals(internalMark)) {
                    currentSequence.add(boardButtons[tr][tc]);
                    if (currentSequence.size() == customWinTarget) {
                        winningButtons.addAll(currentSequence);
                        return true;
                    }
                } else {
                    currentSequence.clear();
                }
            }
        }
        return false;
    }

    private void highlightWinningRun() {
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                AppStyle.CustomRoundedButton b = boardButtons[r][c];
                String mark = (String) b.getClientProperty("internalMark");
                if (winningButtons.contains(b)) {
                    b.setBackground(AppStyle.WIN_TEAL);
                    b.setForeground(TILE_WIN_WHITE);
                } else {
                    if (mark != null && !mark.isEmpty()) {
                        b.setBackground(TILE_SELECTED_BG);
                    } else {
                        b.setBackground(TILE_DEFAULT_BG);
                    }
                    b.setForeground(new Color(210, 212, 216));
                }
            }
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private void resetMatchEngine() {
        btnPlayAgain.setVisible(false);
        totalSecondsElapsed = 0;
        lblTimer.setText("0");
        buildBoard();
        startMatchTimer();
    }

    private void startMatchTimer() {
        if (matchTimer != null) matchTimer.stop();
        totalSecondsElapsed = 0;
        matchTimer = new javax.swing.Timer(1000, e -> {
            if (gameActive) {
                totalSecondsElapsed++;
                lblTimer.setText(String.valueOf(totalSecondsElapsed));
            }
        });
        matchTimer.start();
    }
}