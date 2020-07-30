package org.akachi.humanTraining.service;

import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Player {

    public void playerSound(@NotNull String soundSource) {
        try {
            AudioClip ac;
//            JApplet applate = new JApplet();

            URL urlAudio;
            File f = new File(soundSource);
            urlAudio = f.toURL();
            ac = Applet.newAudioClip(urlAudio);
            ac.play();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void loopSound(@NotNull String soundSource) {
        try {
            AudioClip ac;

            URL urlAudio;
            File f = new File(soundSource);
            urlAudio = f.toURL();
            ac = Applet.newAudioClip(urlAudio);
            ac.loop();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
