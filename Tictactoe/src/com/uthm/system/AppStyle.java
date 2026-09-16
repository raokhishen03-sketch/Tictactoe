package com.uthm.system;

import java.awt.*;
import javax.swing.*;

public class AppStyle {
    public static final Color BG_GRAY = new Color(244, 244, 244);       // Soft backdrop gray
    public static final Color BUTTON_BG = Color.WHITE;                  // Solid crisp white interior
    public static final Color BORDER_COLOR = new Color(218, 218, 218);   // Soft thin
    public static final Color TEXT_DARK = new Color(43, 43, 43);        // Charcoal primary text

    // DROPDOWN COLORS
    public static final Color COMBO_SELECTED_BG = new Color(164, 183, 198); // Muted steel blue
    public static final Color COMBO_BORDER = new Color(200, 205, 210);

    // ACCENT / WIN COLOR (Added back for your toggle switch)
    public static final Color WIN_TEAL = new Color(23, 107, 120);

    public static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 24);
    public static final Font BUTTON_FONT = new Font("SansSerif", Font.BOLD, 15);
    public static final Font LABEL_FONT = new Font("SansSerif", Font.BOLD, 13);

    public static JButton createRoundedButton(String text) {
        return new CustomRoundedButton(text, false);
    }

    public static JButton createAccentButton(String text) {
        return new CustomRoundedButton(text, true);
    }

    public static JToggleButton createToggleSwitch(boolean initialState) {
        JToggleButton toggle = new CustomToggleSwitch();
        toggle.setSelected(initialState);
        return toggle;
    }

    public static void styleComboBox(JComboBox<?> comboBox) {
        comboBox.setFont(LABEL_FONT);
        comboBox.setForeground(TEXT_DARK);
        comboBox.setBackground(Color.WHITE);

        // Remove native button arrows and borders
        comboBox.setBorder(BorderFactory.createLineBorder(COMBO_BORDER, 1));

        // Force the dropdown item list to use your precise lecturer color tokens
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected, boolean cellHasFocus) {

                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (isSelected) {
                    setBackground(COMBO_SELECTED_BG); // The steel blue highlight from image_8a6900.png
                    setForeground(TEXT_DARK);
                } else {
                    setBackground(Color.WHITE);
                    setForeground(TEXT_DARK);
                }

                setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
                return this;
            }
        });
    }

    public static class CustomRoundedButton extends JButton {
        private final boolean isAccent;

        public CustomRoundedButton(String text, boolean isAccent) {
            super(text);
            this.isAccent = isAccent;

            setFont(BUTTON_FONT);
            setOpaque(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            if (isAccent) {
                setForeground(Color.WHITE);
                setBackground(WIN_TEAL);
            } else {
                setForeground(TEXT_DARK);
                setBackground(BUTTON_BG);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            // 1. Draw Background Pill
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);

            // 2. Draw Subtle Border Outline (Only for non-accent buttons)
            if (!isAccent) {
                g2.setColor(BORDER_COLOR);
                g2.setStroke(new BasicStroke(1.2f));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 12, 12);
            }

            // 3. Manually Paint Icon and Text to Completely Block System Highlighting
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(getText());
            int textHeight = fm.getAscent();

            int iconWidth = (getIcon() != null) ? getIcon().getIconWidth() : 0;
            int gap = (getIcon() != null) ? getIconTextGap() : 0;

            int totalContentWidth = iconWidth + gap + textWidth;
            int startX = (getWidth() - totalContentWidth) / 2;
            int startY = (getHeight() + textHeight) / 2 - 2;

            // Render Icon if present
            if (getIcon() != null) {
                getIcon().paintIcon(this, g2, startX, (getHeight() - getIcon().getIconHeight()) / 2);
                startX += iconWidth + gap;
            }

            // Render Text
            g2.setColor(getForeground());
            g2.drawString(getText(), startX, startY);

            g2.dispose();
        }
    }

    public static class CustomToggleSwitch extends JToggleButton {
        public CustomToggleSwitch() {
            setPreferredSize(new Dimension(38, 20));
            setOpaque(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            addActionListener(e -> repaint());
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();

            if (isSelected()) {
                g2.setColor(WIN_TEAL);
            } else {
                g2.setColor(new Color(210, 214, 222));
            }
            g2.fillRoundRect(0, 0, w, h, h, h);

            g2.setColor(Color.WHITE);
            int knobSize = h - 4;
            int knobX = isSelected() ? (w - knobSize - 2) : 2;
            g2.fillOval(knobX, 2, knobSize, knobSize);

            g2.dispose();
        }
    }
}