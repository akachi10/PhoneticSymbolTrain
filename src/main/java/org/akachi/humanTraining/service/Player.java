package org.akachi.humanTraining.service;

import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Player extends Thread {

    private boolean isLoop = false;
    private String filePath;

    @Override
    public void run() {
        try {
            JApplet applate = new JApplet();
            URL urlAudio;
            File f = new File(filePath);
            urlAudio = f.toURL();
            AudioClip ac = Applet.newAudioClip(urlAudio);
            if (isLoop) {
                ac.loop();
            } else {
                ac.play();
                Thread.sleep(2000);
                ac.stop();
                this.interrupt();
            }
        } catch (MalformedURLException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void playerSound(@NotNull String soundSource) {
        Player p = new Player();
        p.filePath = soundSource;
        p.start();
    }

    public static void loopSound(@NotNull String soundSource) {
        Player p = new Player();
        p.filePath = soundSource;
        p.isLoop = true;
        p.start();
    }
}
