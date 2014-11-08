package com.tgra.client.managers;

import com.tgra.client.entity.*;
import com.tgra.client.utility.*;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.utils.XmlReader.Element;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 10/17/2014
 * Time : 00:36
 */
public class AudioManager {
    private static final String path = "data/config/AudioConfig.xml";
    private static List<Audio> files;
    private static float volume = 0.5f;

    /**
     * Loads in and reads the xml config file
     */
    public static void load() {
        // New list
        files = new ArrayList<Audio>();

        Array<Element> items = XMLReader.read(path, "file");

        for (Element child : items)
        {
            files.add(new Audio(child));
        }
    }

    /**
     * Play the sound byte
     * @param name name of the sound
     */
    public static void play(String name) {
        Audio file = getAudioFile(name);
        file.play(volume);
    }

    /**
     * Stop the sound byte
     * @param name name of the sound
     */
    public static void stop(String name) {
        Audio file = getAudioFile(name);
        file.stop();
    }

    /**
     * End the sound loop
     * @param name name of the sound
     */
    public static void endLoop(String name) {
        Audio file = getAudioFile(name);
        file.endLoop();
    }

    /**
     * Gets the audio file by name in
     * @param name name of the sound
     * @return audio file instance
     */
    public static Audio getAudioFile(String name) {
        for(Audio audio : files) {
            if(audio.equals(name))
                return audio;
        }

        return getAudioFile("error");
    }

    /**
     * Set the volume
     * @param v new volume
     */
    public static void setVolume(float v) {
        volume = v;
    }

    /**
     * Get the volume
     */
    public static float getVolume() {
        return volume;
    }
}
