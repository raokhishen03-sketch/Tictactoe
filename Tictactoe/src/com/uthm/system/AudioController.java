package com.uthm.system;

import javax.sound.sampled.*;
import java.net.URL;

public class AudioController {
    private static final String BACKGROUND_MUSIC = "audio/background_music.wav";
    private static final String WIN_SOUND = "audio/win_sound.wav";
    private static final String LOSE_SOUND = "audio/lose_sound.wav";

    private static Clip backgroundClip;
    private static boolean isMuted = false;
    private static long clipTimePosition = 0;

    public static void startMusic() {
        try {
            if (isMuted || backgroundClip != null) return;
            backgroundClip = loadClip(BACKGROUND_MUSIC);
            if (backgroundClip != null) backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println("Audio Error: " + e.getMessage());
        }
    }

    public static void toggleMute() {
        isMuted = !isMuted;

        if (isMuted) {
            if (backgroundClip != null) {
                clipTimePosition = backgroundClip.getMicrosecondPosition();
                backgroundClip.stop();
            }
        } else {
            if (backgroundClip == null) {
                startMusic();
            } else {
                backgroundClip.setMicrosecondPosition(clipTimePosition);
                backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }
    }

    public static boolean isMuted() {
        return isMuted;
    }

    public static void stopMusic() {
        if (backgroundClip == null) return;
        clipTimePosition = backgroundClip.getMicrosecondPosition();
        backgroundClip.stop();
        backgroundClip.close();
        backgroundClip = null;
    }

    public static void playWinSound() {
        playEffect(WIN_SOUND);
    }

    public static void playLoseSound() {
        playEffect(LOSE_SOUND);
    }

    private static void playEffect(String resourcePath) {
        if (isMuted) return;

        try {
            Clip effectClip = loadClip(resourcePath);
            if (effectClip == null) return;
            effectClip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    effectClip.close();
                }
            });
            effectClip.start();
        } catch (Exception e) {
            System.out.println("Sound Effect Error: " + e.getMessage());
        }
    }

    private static Clip loadClip(String resourcePath) throws Exception {
        URL soundURL = findResource(resourcePath);
        if (soundURL == null) {
            System.out.println("Audio file not found: " + resourcePath);
            return null;
        }

        AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        audioStream.close();
        return clip;
    }

    private static URL findResource(String resourcePath) {
        String[] paths = {
                resourcePath,
                "resources/" + resourcePath
        };

        for (String path : paths) {
            URL soundURL = AudioController.class.getResource("/" + path);
            if (soundURL == null) {
                soundURL = AudioController.class.getClassLoader().getResource(path);
            }
            if (soundURL != null) return soundURL;
        }
        return null;
    }
}
