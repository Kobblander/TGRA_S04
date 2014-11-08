package com.tgra.client.managers;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.tgra.client.entity.KeyBinding;
import com.tgra.client.utility.XMLReader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 10/17/2014
 * Time : 03:03
 */
public class KeyManager {
    private static final String path = "data/config/KeyConfig.xml";
    private static List<KeyBinding> keys;

    /**
     * Loads in and reads the xml config file
     */
    public static void load() {
        // New list
        keys = new ArrayList<KeyBinding>();

        Array<XmlReader.Element> items = XMLReader.read(path, "key");

        for (XmlReader.Element child : items)
        {
            keys.add(new KeyBinding(child));
        }
    }

    /**
     * Gets the audio file by name in
     * @param name name of the sound
     * @return audio file instance
     */
    public static boolean isPressed(String name) {
        for(KeyBinding key : keys) {
            if(key.equals(name))
                return key.isPressed();
        }

        return false;
    }



}
