package com.uthm.system;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class SVGIconHelper {

    public static Icon getSVGIcon(String iconName, int width, int height) {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.translate(x, y);

                // Dynamically apply current UI colors matching theme states
                if (c != null && !c.isEnabled()) {
                    g2.setColor(Color.LIGHT_GRAY);
                } else if (c instanceof JButton) {
                    g2.setColor(((JButton) c).getForeground());
                } else {
                    g2.setColor(AppStyle.TEXT_DARK);
                }

                String key = (iconName == null) ? "" : iconName.toLowerCase().trim();
                if (key.endsWith(".png")) key = key.substring(0, key.length() - 4);
                if (key.endsWith(".svg")) key = key.substring(0, key.length() - 4);

                switch (key) {
                    case "block_used":
                    case "board_info":
                    case "spaces":
                        g2.scale(width / 24.0, height / 24.0);
                        Path2D.Double blockPath = new Path2D.Double(Path2D.WIND_NON_ZERO);
                        blockPath.moveTo(20.4961766, 5.62668182);
                        blockPath.curveTo(21.3720675, 5.93447702, 22, 6.76890777, 22, 7.75);
                        blockPath.lineTo(22, 17.75);
                        blockPath.curveTo(22, 20.0972102, 20.0972102, 22, 17.75, 22);
                        blockPath.lineTo(7.75, 22);
                        blockPath.curveTo(6.76890777, 22, 5.93447702, 21.3720675, 5.62668182, 20.4961766);
                        blockPath.lineTo(7.72396188, 20.4995565);
                        blockPath.lineTo(17.75, 20.5);
                        blockPath.curveTo(19.2687831, 20.5, 20.5, 19.2687831, 20.5, 17.75);
                        blockPath.lineTo(20.5, 7.75);
                        blockPath.lineTo(20.4960194, 7.69901943);
                        blockPath.lineTo(20.4961766, 5.62668182);
                        blockPath.closePath();
                        blockPath.moveTo(17.246813, 2);
                        blockPath.curveTo(18.4894537, 2, 19.496813, 3.00735931, 19.496813, 4.25);
                        blockPath.lineTo(19.496813, 17.246813);
                        blockPath.curveTo(19.496813, 18.4894537, 18.4894537, 19.496813, 17.246813, 19.496813);
                        blockPath.lineTo(4.25, 19.496813);
                        blockPath.curveTo(3.00735931, 19.496813, 2, 18.4894537, 2, 17.246813);
                        blockPath.lineTo(2, 4.25);
                        blockPath.curveTo(2, 3.00735931, 3.00735931, 2, 4.25, 2);
                        blockPath.lineTo(17.246813, 2);
                        blockPath.closePath();
                        blockPath.moveTo(17.246813, 3.5);
                        blockPath.lineTo(4.25, 3.5);
                        blockPath.curveTo(3.83578644, 3.5, 3.5, 3.83578644, 3.5, 4.25);
                        blockPath.lineTo(3.5, 17.246813);
                        blockPath.curveTo(3.5, 17.6610266, 3.83578644, 17.996813, 4.25, 17.996813);
                        blockPath.lineTo(17.246813, 17.996813);
                        blockPath.curveTo(17.6610266, 17.996813, 17.996813, 17.6610266, 17.996813, 17.246813);
                        blockPath.lineTo(17.996813, 4.25);
                        blockPath.curveTo(17.996813, 3.83578644, 17.6610266, 3.5, 17.246813, 3.5);
                        blockPath.closePath();
                        g2.fill(blockPath);
                        break;

                    case "bot win":
                    case "score_o":
                        g2.scale(width / 24.0, height / 24.0);
                        Path2D.Double botPath = new Path2D.Double();
                        botPath.moveTo(17.7529, 14.0004);
                        botPath.curveTo(18.9956, 14.0004, 20.0029, 15.0078, 20.0029, 16.2504);
                        botPath.lineTo(20.0029, 17.1555);
                        botPath.curveTo(20.0029, 18.2492, 19.5255, 19.2883, 18.6957, 20.0008);
                        botPath.curveTo(17.1302, 21.3447, 14.8899, 22.0016, 11.9999, 22.0016);
                        botPath.curveTo(9.11038, 22.0016, 6.87156, 21.345, 5.30869, 20.0013);
                        botPath.curveTo(4.48007, 19.2889, 4.00342, 18.2505, 4.00342, 17.1577);
                        botPath.lineTo(4.00342, 16.2504);
                        botPath.curveTo(4.00342, 15.0078, 5.01078, 14.0004, 6.25342, 14.0004);
                        botPath.lineTo(17.7529, 14.0004);
                        botPath.closePath();
                        botPath.moveTo(17.7529, 15.5004);
                        botPath.lineTo(6.25342, 15.5004);
                        botPath.curveTo(5.8392, 15.5004, 5.50342, 15.8362, 5.50342, 16.2504);
                        botPath.lineTo(5.50342, 17.1577);
                        botPath.curveTo(5.50342, 17.8134, 5.78941, 18.4364, 6.28658, 18.8638);
                        botPath.curveTo(7.54467, 19.9455, 9.44068, 20.5016, 11.9999, 20.5016);
                        botPath.curveTo(14.5599, 20.5016, 16.4577, 19.9451, 17.7186, 18.8626);
                        botPath.curveTo(18.2165, 18.4352, 18.5029, 17.8117, 18.5029, 17.1555);
                        botPath.lineTo(18.5029, 16.2504);
                        botPath.curveTo(18.5029, 15.8362, 18.1671, 15.5004, 17.7529, 15.5004);
                        botPath.closePath();
                        botPath.moveTo(11.8984, 2.00782);
                        botPath.lineTo(12.0002, 2.00098);
                        botPath.curveTo(12.3799, 2.00098, 12.6937, 2.28313, 12.7434, 2.64921);
                        botPath.lineTo(12.7502, 2.75098);
                        botPath.lineTo(12.7494, 3.49998);
                        botPath.lineTo(16.2499, 3.50048);
                        botPath.curveTo(17.4925, 3.50048, 18.4999, 4.50784, 18.4999, 5.75048);
                        botPath.lineTo(18.4999, 10.2551);
                        botPath.curveTo(18.4999, 11.4977, 17.4925, 12.5051, 16.2499, 12.5051);
                        botPath.lineTo(7.74988, 12.5051);
                        botPath.curveTo(6.50724, 12.5051, 5.49988, 11.4977, 5.49988, 10.2551);
                        botPath.lineTo(5.49988, 5.75048);
                        botPath.curveTo(5.49988, 4.50784, 6.50724, 3.50048, 7.74988, 3.50048);
                        botPath.lineTo(11.2494, 3.49998);
                        botPath.lineTo(11.2502, 2.75098);
                        botPath.curveTo(11.2502, 2.37128, 11.5324, 2.05749, 11.8984, 2.00782);
                        botPath.lineTo(12.0002, 2.00098);
                        botPath.lineTo(11.8984, 2.00782);
                        botPath.closePath();
                        botPath.moveTo(16.2499, 5.00048);
                        botPath.lineTo(7.74988, 5.00048);
                        botPath.curveTo(7.33566, 5.00048, 6.99988, 5.33627, 6.99988, 5.75048);
                        botPath.lineTo(6.99988, 10.2551);
                        botPath.curveTo(6.99988, 10.6693, 7.33566, 11.0051, 7.74988, 11.0051);
                        botPath.lineTo(16.2499, 11.0051);
                        botPath.curveTo(16.6641, 11.0051, 16.9999, 10.6693, 16.9999, 10.2551);
                        botPath.lineTo(16.9999, 5.75048);
                        botPath.curveTo(16.9999, 5.33627, 16.6641, 5.00048, 16.2499, 5.00048);
                        botPath.closePath();
                        botPath.moveTo(9.74917, 6.50048);
                        botPath.curveTo(10.4391, 6.50048, 10.9985, 7.05981, 10.9985, 7.74977);
                        botPath.curveTo(10.9985, 8.43973, 10.4391, 8.99906, 9.74917, 8.99906);
                        botPath.curveTo(9.0592, 8.99906, 8.49988, 8.43973, 8.49988, 7.74977);
                        botPath.curveTo(8.49988, 7.05981, 9.0592, 6.50048, 9.74917, 6.50048);
                        botPath.closePath();
                        botPath.moveTo(14.2419, 6.50048);
                        botPath.curveTo(14.9319, 6.50048, 15.4912, 7.05981, 15.4912, 7.74977);
                        botPath.curveTo(15.4912, 8.43973, 14.9319, 8.99906, 14.2419, 8.99906);
                        botPath.curveTo(13.5519, 8.99906, 12.9926, 8.43973, 12.9926, 7.74977);
                        botPath.curveTo(12.9926, 7.05981, 13.5519, 6.50048, 14.2419, 6.50048);
                        botPath.closePath();
                        g2.fill(botPath);
                        break;

                    case "gamemode":
                        g2.scale(width / 192.0, height / 192.0);
                        g2.setStroke(new BasicStroke(12f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 10f));
                        g2.draw(new RoundRectangle2D.Double(22, 59, 148, 74, 37, 37));

                        Path2D.Double gmLine = new Path2D.Double();
                        gmLine.moveTo(59, 84);
                        gmLine.lineTo(59, 108.43);
                        gmLine.moveTo(71.36, 96.46);
                        gmLine.lineTo(46.93, 96.46);
                        g2.draw(gmLine);

                        g2.fill(new Ellipse2D.Double(108, 103, 12, 12));
                        g2.fill(new Ellipse2D.Double(136, 77, 12, 12));
                        break;

                    case "mute":
                        // Mirror the exact same shapes but add the clean, non-filled sharp slash line
                        g2.scale(width / 24.0, height / 24.0);

                        // 1. Draw the sharp hollow speaker body outline
                        g2.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
                        Path2D.Double muteBodyHollow = new Path2D.Double();
                        muteBodyHollow.moveTo(3.5, 9.5);
                        muteBodyHollow.lineTo(7.5, 9.5);
                        muteBodyHollow.lineTo(12.5, 4.5);
                        muteBodyHollow.lineTo(12.5, 19.5);
                        muteBodyHollow.lineTo(7.5, 14.5);
                        muteBodyHollow.lineTo(3.5, 14.5);
                        muteBodyHollow.closePath();
                        g2.draw(muteBodyHollow);

                        // 2. Render the exact same solid inner thick wave
                        Path2D.Double innerMuteWave = new Path2D.Double();
                        innerMuteWave.moveTo(14.5, 8.5);
                        innerMuteWave.quadTo(17.0, 12.0, 14.5, 15.5);
                        innerMuteWave.lineTo(15.8, 16.2);
                        innerMuteWave.quadTo(18.8, 12.0, 15.8, 7.8);
                        innerMuteWave.closePath();
                        g2.fill(innerMuteWave);

                        // 3. Render the exact same solid outer thick wave
                        Path2D.Double outerMuteWave = new Path2D.Double();
                        outerMuteWave.moveTo(17.5, 6.0);
                        outerMuteWave.quadTo(21.5, 12.0, 17.5, 18.0);
                        outerMuteWave.lineTo(18.8, 18.7);
                        outerMuteWave.quadTo(23.2, 12.0, 18.8, 5.3);
                        outerMuteWave.closePath();
                        g2.fill(outerMuteWave);

                        // 4. Sharp, non-filled diagonal crossover stroke line (Not filled in!)
                        g2.draw(new Line2D.Double(3.0, 3.0, 21.0, 21.0));
                        break;

                    case "person win":
                    case "player_counter":
                        g2.scale(width / 25.0, height / 25.0);
                        g2.scale(24.0 / 960.0, 24.0 / 960.0);
                        g2.translate(0, 960);
                        Path2D.Double personPath = new Path2D.Double();
                        personPath.moveTo(367, -527);
                        personPath.quadTo(320, -574, 320, -640); personPath.quadTo(320, -706, 367, -753);
                        personPath.quadTo(414, -800, 480, -800); personPath.quadTo(546, -800, 593, -753);
                        personPath.quadTo(640, -706, 640, -640); personPath.quadTo(640, -574, 593, -527);
                        personPath.quadTo(546, -480, 480, -480); personPath.quadTo(414, -480, 367, -527);
                        personPath.closePath();
                        personPath.moveTo(160, -160);
                        personPath.lineTo(160, -272);
                        personPath.quadTo(160, -346, 177.5, -374.5); personPath.quadTo(195, -403, 224, -418);
                        personPath.quadTo(286, -449, 350, -464.5); personPath.quadTo(414, -480, 480, -480);
                        personPath.quadTo(546, -480, 610, -464.5); personPath.quadTo(674, -419, 736, -388);
                        personPath.quadTo(765, -373, 782.5, -344.5); personPath.quadTo(800, -316, 800, -272);
                        personPath.lineTo(800, -160);
                        personPath.lineTo(160, -160);
                        personPath.closePath();
                        personPath.moveTo(240, -240);
                        personPath.lineTo(720, -240);
                        personPath.lineTo(720, -272);
                        personPath.quadTo(720, -283, 714.5, -292); personPath.quadTo(709, -301, 700, -306);
                        personPath.quadTo(646, -333, 591, -346.5); personPath.quadTo(536, -360, 480, -360);
                        personPath.quadTo(424, -360, 369, -346.5); personPath.quadTo(314, -333, 260, -306);
                        personPath.quadTo(251, -301, 245.5, -292); personPath.quadTo(240, -283, 240, -272);
                        personPath.closePath();
                        personPath.moveTo(536.5, -583.5);
                        personPath.quadTo(560, -607, 560, -640); personPath.quadTo(560, -673, 536.5, -696.5);
                        personPath.quadTo(513, -720, 480, -720); personPath.quadTo(447, -720, 423.5, -696.5);
                        personPath.quadTo(400, -673, 400, -640); personPath.quadTo(400, -607, 423.5, -583.5);
                        personPath.quadTo(447, -560, 480, -560); personPath.quadTo(513, -560, 536.5, -583.5);
                        personPath.closePath();
                        g2.fill(personPath);
                        break;

                    case "person2":
                        g2.setColor(Color.BLACK); // Set to black as requested
                        g2.scale(width / 25.0, height / 25.0);
                        g2.scale(24.0 / 960.0, 24.0 / 960.0);
                        g2.translate(0, 960);

                        Path2D.Double person2Path = new Path2D.Double();

                        // Head - A solid circle for the bot icon
                        person2Path.moveTo(480, -800);
                        person2Path.curveTo(391, -800, 320, -729, 320, -640);
                        person2Path.curveTo(320, -551, 391, -480, 480, -480);
                        person2Path.curveTo(569, -480, 640, -551, 640, -640);
                        person2Path.curveTo(640, -729, 569, -800, 480, -800);
                        person2Path.closePath();

                        // Body - A sturdy, filled base
                        person2Path.moveTo(160, -160);
                        person2Path.lineTo(160, -272);
                        person2Path.quadTo(160, -380, 240, -420);
                        person2Path.lineTo(720, -420);
                        person2Path.quadTo(800, -380, 800, -272);
                        person2Path.lineTo(800, -160);
                        person2Path.lineTo(160, -160);
                        person2Path.closePath();

                        g2.fill(person2Path);
                        break;

                    case "play":
                    case "play_arrow":
                        g2.scale(width / 24.0, height / 24.0);
                        // Using JOIN_ROUND gives the triangle's apex points a clean, slightly rounded corner look
                        g2.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

                        Path2D.Double playArrow = new Path2D.Double();
                        playArrow.moveTo(6.5, 4.5);
                        playArrow.lineTo(19.5, 12.0); // Points out towards the right
                        playArrow.lineTo(6.5, 19.5);
                        playArrow.closePath();

                        g2.draw(playArrow);
                        break;

                    case "settings":
                        g2.scale(width / 24.0, height / 24.0);
                        g2.scale(24.0 / 960.0, 24.0 / 960.0);
                        g2.translate(0, 960);

                        // Unified, thick outline
                        g2.setStroke(new BasicStroke(65.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

                        Path2D.Double settingsPath = new Path2D.Double();

                        // Gear Perimeter
                        settingsPath.moveTo(370, -80);
                        settingsPath.lineTo(354, -208);
                        settingsPath.quadTo(341, -213, 329.5, -220); settingsPath.quadTo(318, -227, 307, -235);
                        settingsPath.lineTo(188, -185); settingsPath.lineTo(78, -375); settingsPath.lineTo(181, -453);
                        settingsPath.quadTo(180, -460, 180, -466.5); settingsPath.lineTo(180, -493.5);
                        settingsPath.quadTo(180, -500, 181, -507); settingsPath.lineTo(78, -585); settingsPath.lineTo(188, -775);
                        settingsPath.lineTo(307, -725); settingsPath.quadTo(318, -733, 330, -740);
                        settingsPath.quadTo(342, -747, 354, -752); settingsPath.lineTo(370, -880); settingsPath.lineTo(590, -880);
                        settingsPath.lineTo(606, -752); settingsPath.quadTo(619, -747, 630.5, -740);
                        settingsPath.quadTo(642, -733, 652.5, -725); settingsPath.lineTo(771.5, -775);
                        settingsPath.lineTo(881.5, -585); settingsPath.lineTo(778.5, -507);
                        settingsPath.quadTo(779.5, -460, 779.5, -493.5); settingsPath.lineTo(779.5, -466.5);
                        settingsPath.quadTo(779.5, -460, 777.5, -453); settingsPath.lineTo(880.5, -375);
                        settingsPath.lineTo(770.5, -185); settingsPath.lineTo(652.5, -235);
                        settingsPath.quadTo(641.5, -227, 630, -220); settingsPath.quadTo(618.5, -213, 606.5, -208);
                        settingsPath.lineTo(590.5, -80);
                        settingsPath.closePath(); // Closes the right side properly back to start point

                        // Inner Hub (Closed loop to keep it hollow)
                        settingsPath.moveTo(480, -340);
                        settingsPath.quadTo(540, -340, 581, -381); settingsPath.quadTo(622, -422, 622, -480);
                        settingsPath.quadTo(622, -538, 581, -579); settingsPath.quadTo(540, -620, 480, -620);
                        settingsPath.quadTo(420, -620, 379, -579); settingsPath.quadTo(338, -538, 338, -480);
                        settingsPath.quadTo(338, -422, 379, -381); settingsPath.quadTo(420, -340, 480, -340);
                        settingsPath.closePath();

                        g2.draw(settingsPath);
                        break;

                    case "timer":
                    case "match_timer":
                        // Clear out all glitchy translation matrix compounding chains
                        // This maps your 100x100 timer path precisely into the space requested by layout parameters
                        g2.scale(width / 100.0, height / 100.0);

                        Path2D.Double timerPath = new Path2D.Double();
                        timerPath.moveTo(50, 5);
                        timerPath.curveTo(25.3, 5, 5.2, 25.1, 5, 49.7);
                        timerPath.curveTo(4.9, 61.7, 9.5, 73.1, 18, 81.6);
                        timerPath.curveTo(26.4, 90.2, 37.7, 94.9, 49.7, 95);
                        timerPath.lineTo(50, 95);
                        timerPath.curveTo(74.7, 95, 94.8, 74.9, 95, 50.3);
                        timerPath.curveTo(95.2, 25.5, 75.1, 2.2, 50, 2);
                        timerPath.closePath();
                        timerPath.moveTo(50, 88.9);
                        timerPath.lineTo(49.7, 88.9);
                        timerPath.curveTo(39.3, 88.8, 29.6, 84.7, 22.2, 77.3);
                        timerPath.curveTo(15, 70, 11, 60.2, 11.1, 49.8);
                        timerPath.curveTo(11.2, 28.5, 28.7, 11.1, 50.3, 11.1);
                        timerPath.curveTo(71.8, 11.2, 89.1, 28.8, 89, 50.3);
                        timerPath.curveTo(88.8, 71.6, 71.3, 88.9, 50, 88.9);
                        timerPath.closePath();
                        timerPath.moveTo(71.2, 48.8);
                        timerPath.lineTo(52.5, 48.8);
                        timerPath.lineTo(52.5, 22.9);
                        timerPath.curveTo(52.5, 21.3, 51.2, 20, 49.6, 20);
                        timerPath.curveTo(48, 20, 46.7, 21.3, 46.7, 22.9);
                        timerPath.lineTo(46.7, 51.7);
                        timerPath.curveTo(46.7, 53.3, 48, 54.6, 49.6, 54.6);
                        timerPath.lineTo(71.2, 54.6);
                        timerPath.curveTo(72.8, 54.6, 74.1, 53.3, 74.1, 51.7);
                        timerPath.curveTo(74.1, 50.1, 72.8, 48.8, 71.2, 48.8);
                        timerPath.closePath();

                        g2.fill(timerPath);
                        break;

                    case "reset":
                        // Coordinates use a bounding box of 68.369 x 68.369
                        g2.scale(width / 68.369, height / 68.369);

                        Path2D.Float resetLeftLoop = new Path2D.Float();
                        resetLeftLoop.moveTo(22.839, 22.71);
                        resetLeftLoop.lineTo(31.19, 14.359);
                        resetLeftLoop.lineTo(23.666, 6.833);
                        resetLeftLoop.curveTo(23.211, 6.378, 22.471, 6.378, 22.015, 6.833);
                        resetLeftLoop.curveTo(21.56, 7.286, 21.56, 8.029, 22.015, 8.484);
                        resetLeftLoop.lineTo(26.621, 13.093);
                        resetLeftLoop.lineTo(15.575, 13.093);
                        resetLeftLoop.curveTo(6.987, 13.924, 0, 22.956, 0, 34.061);
                        resetLeftLoop.curveTo(0, 45.165, 6.989, 54.199, 15.575, 54.199);
                        resetLeftLoop.lineTo(29.982, 54.199);
                        resetLeftLoop.curveTo(30.627, 54.199, 31.15, 53.677, 31.15, 53.031);
                        resetLeftLoop.curveTo(31.15, 52.387, 30.627, 51.863, 29.982, 51.863);
                        resetLeftLoop.lineTo(15.575, 51.863);
                        resetLeftLoop.curveTo(8.276, 51.863, 2.336, 43.879, 2.336, 34.061);
                        resetLeftLoop.curveTo(2.336, 24.246, 8.276, 16.26, 15.575, 16.26);
                        resetLeftLoop.lineTo(27.644, 16.26);
                        resetLeftLoop.lineTo(22.841, 21.059);
                        resetLeftLoop.curveTo(22.384, 21.515, 22.384, 22.255, 22.839, 22.71);
                        resetLeftLoop.closePath();
                        g2.fill(resetLeftLoop);

                        Path2D.Float resetRightLoop = new Path2D.Float();
                        resetRightLoop.moveTo(52.789, 14.171);
                        resetRightLoop.lineTo(38.381, 14.171);
                        resetRightLoop.curveTo(37.737, 14.171, 37.213, 14.692, 37.213, 15.339);
                        resetRightLoop.curveTo(37.213, 15.981, 37.737, 16.507, 38.381, 16.507);
                        resetRightLoop.lineTo(52.789, 16.507);
                        resetRightLoop.curveTo(60.089, 16.507, 66.028, 24.49, 66.028, 34.307);
                        resetRightLoop.curveTo(66.028, 44.122, 60.089, 52.124, 52.789, 52.124);
                        resetRightLoop.lineTo(40.722, 52.124);
                        resetRightLoop.lineTo(45.521, 47.324);
                        resetRightLoop.curveTo(45.977, 46.868, 45.977, 46.129, 45.521, 45.673);
                        resetRightLoop.curveTo(45.067, 45.217, 44.326, 45.217, 43.872, 45.673);
                        resetRightLoop.lineTo(36.348, 53.194);
                        resetRightLoop.lineTo(43.872, 60.722);
                        resetRightLoop.curveTo(44.327, 61.176, 45.068, 61.176, 45.524, 60.722);
                        resetRightLoop.curveTo(45.98, 60.268, 45.98, 59.524, 45.524, 59.071);
                        resetRightLoop.lineTo(40.917, 54.461);
                        resetRightLoop.lineTo(52.789, 54.461);
                        resetRightLoop.curveTo(61.375, 54.461, 68.369, 45.43, 68.369, 34.323);
                        resetRightLoop.curveTo(68.369, 23.203, 61.379, 14.171, 52.789, 14.171);
                        resetRightLoop.closePath();
                        g2.fill(resetRightLoop);
                        break;

                    case "try again":
                        g2.scale(width / 24.0, height / 24.0);
                        g2.scale(24.0 / 960.0, 24.0 / 960.0);
                        g2.translate(0, 960);
                        Path2D.Double tryPath = new Path2D.Double();
                        tryPath.moveTo(480, -160);
                        tryPath.quadTo(346, -160, 253, -253); tryPath.quadTo(160, -346, 160, -480);
                        tryPath.quadTo(160, -614, 253, -707); tryPath.quadTo(346, -800, 480, -800);
                        tryPath.quadTo(549, -800, 612, -771.5); tryPath.quadTo(675, -743, 720, -690);
                        tryPath.lineTo(720, -800);
                        tryPath.lineTo(800, -800);
                        tryPath.lineTo(800, -520);
                        tryPath.lineTo(520, -520);
                        tryPath.lineTo(520, -600);
                        tryPath.lineTo(688, -600);
                        tryPath.quadTo(656, -656, 600.5, -688); tryPath.quadTo(545, -720, 480, -720);
                        tryPath.quadTo(380, -720, 310, -650); tryPath.quadTo(240, -580, 240, -480);
                        tryPath.quadTo(240, -380, 310, -310); tryPath.quadTo(380, -240, 480, -240);
                        tryPath.quadTo(557, -240, 619, -284); tryPath.quadTo(681, -328, 708, -434);
                        tryPath.lineTo(792, -434);
                        tryPath.quadTo(764, -328, 678, -261); tryPath.quadTo(592, -160, 480, -160);
                        tryPath.closePath();
                        g2.fill(tryPath);
                        break;

                    case "unmute":
                        // Outlined speaker body + Solid filled half-circle sound waves with sharp ends
                        g2.scale(width / 24.0, height / 24.0);

                        // 1. Draw the sharp hollow speaker body outline
                        g2.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
                        Path2D.Double unmuteBodyHollow = new Path2D.Double();
                        unmuteBodyHollow.moveTo(3.5, 9.5);
                        unmuteBodyHollow.lineTo(7.5, 9.5);
                        unmuteBodyHollow.lineTo(12.5, 4.5);
                        unmuteBodyHollow.lineTo(12.5, 19.5);
                        unmuteBodyHollow.lineTo(7.5, 14.5);
                        unmuteBodyHollow.lineTo(3.5, 14.5);
                        unmuteBodyHollow.closePath();
                        g2.draw(unmuteBodyHollow);

                        // 2. Render the first wave as a solid thick half-circle band
                        Path2D.Double innerFilledWave = new Path2D.Double();
                        innerFilledWave.moveTo(14.5, 8.5);
                        innerFilledWave.quadTo(17.0, 12.0, 14.5, 15.5); // Outer curve
                        innerFilledWave.lineTo(15.8, 16.2);             // Sharp flat end
                        innerFilledWave.quadTo(18.8, 12.0, 15.8, 7.8);  // Inner curve backwards
                        innerFilledWave.closePath();
                        g2.fill(innerFilledWave);

                        // 3. Render the second wave as a solid thick half-circle band
                        Path2D.Double outerFilledWave = new Path2D.Double();
                        outerFilledWave.moveTo(17.5, 6.0);
                        outerFilledWave.quadTo(21.5, 12.0, 17.5, 18.0); // Outer curve
                        outerFilledWave.lineTo(18.8, 18.7);             // Sharp flat end
                        outerFilledWave.quadTo(23.2, 12.0, 18.8, 5.3);  // Inner curve backwards
                        outerFilledWave.closePath();
                        g2.fill(outerFilledWave);
                        break;

                    case "back":
                    case "return-button":
                        g2.scale(width / 1024.0, height / 1024.0);
                        Path2D.Double backArrowPath = new Path2D.Double();
                        backArrowPath.moveTo(727.28171457, 968.45419457);
                        backArrowPath.lineTo(805.34237235, 898.67858173);
                        backArrowPath.lineTo(375.94402765, 513.4239921);
                        backArrowPath.lineTo(805.34237235, 129.72284839);
                        backArrowPath.lineTo(727.28171457, 59.94723555);
                        backArrowPath.lineTo(214.90346667, 510.05819259);
                        backArrowPath.lineTo(214.90346667, 515.10689185);
                        backArrowPath.lineTo(727.28171457, 968.45419457);
                        backArrowPath.closePath();
                        g2.fill(backArrowPath);
                        break;

                    case "refresh":
                        g2.scale(width / 24.0, height / 24.0);
                        g2.scale(24.0 / 960.0, 24.0 / 960.0);
                        g2.translate(0, 960);
                        Path2D.Double refreshPath = new Path2D.Double();
                        refreshPath.moveTo(480, -160);
                        refreshPath.quadTo(346, -160, 253, -253);
                        refreshPath.quadTo(160, -346, 160, -480);
                        refreshPath.quadTo(160, -614, 253, -707);
                        refreshPath.quadTo(346, -800, 480, -800);
                        refreshPath.quadTo(549, -800, 612, -771.5);
                        refreshPath.quadTo(675, -743, 720, -690);
                        refreshPath.lineTo(720, -800);
                        refreshPath.lineTo(800, -800);
                        refreshPath.lineTo(800, -520);
                        refreshPath.lineTo(520, -520);
                        refreshPath.lineTo(520, -600);
                        refreshPath.lineTo(688, -600);
                        refreshPath.quadTo(656, -656, 600.5, -688);
                        refreshPath.quadTo(545, -720, 480, -720);
                        refreshPath.quadTo(380, -720, 310, -650);
                        refreshPath.quadTo(240, -580, 240, -480);
                        refreshPath.quadTo(240, -380, 310, -310);
                        refreshPath.quadTo(380, -240, 480, -240);
                        refreshPath.quadTo(557, -240, 619, -284);
                        refreshPath.quadTo(681, -328, 708, -434);
                        refreshPath.lineTo(792, -434);
                        refreshPath.quadTo(764, -328, 678, -261);
                        refreshPath.quadTo(592, -160, 480, -160);
                        refreshPath.closePath();
                        g2.fill(refreshPath);
                        break;



                    case "board":
                        // Normalizes your 24x24 canvas coordinates to match the requested component bounds
                        g2.scale(width / 24.0, height / 24.0);

                        Path2D.Float boardGrid = new Path2D.Float();
                        // Outer border frame & inner window paths matched from your SVG source coordinates
                        boardGrid.moveTo(2, 2);
                        boardGrid.lineTo(22, 2);
                        boardGrid.lineTo(22, 22);
                        boardGrid.lineTo(2, 22);
                        boardGrid.closePath();

                        // Sub-tile segmentations
                        boardGrid.moveTo(2, 7.33);  boardGrid.lineTo(22, 7.33);
                        boardGrid.moveTo(2, 14.66); boardGrid.lineTo(22, 14.66);
                        boardGrid.moveTo(7.33, 2);  boardGrid.lineTo(7.33, 22);
                        boardGrid.moveTo(14.66, 2); boardGrid.lineTo(14.66, 22);

                        g2.setStroke(new BasicStroke(1.8f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                        g2.draw(boardGrid);
                        break;

                    default:
                        break;
                }
                g2.dispose();
            }

            @Override public int getIconWidth() { return width; }
            @Override public int getIconHeight() { return height; }
        };
    }
}