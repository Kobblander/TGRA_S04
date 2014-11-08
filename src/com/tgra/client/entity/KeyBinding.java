package com.tgra.client.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.XmlReader;
import com.tgra.client.managers.AudioManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 10/18/2014
 * Time : 00:45
 */
public class KeyBinding {
    private String name;
    private int keyCode, soundIndex;
    private List<Audio> sounds;
    private boolean buffered, pressed;

    public KeyBinding(XmlReader.Element element) {
        name = element.getAttribute("name");
        soundIndex = 0;
        pressed = false;

        sounds = new ArrayList<Audio>();

        validate(element);
    }

    /**
     * Validate the xml node
     * @param element xml node
     */
    private void validate(XmlReader.Element element) {
        // Get key
        XmlReader.Element node = element.getChildByName("value");

        String key = node.getText();
        keyCode = Input.Keys.valueOf(key);

        // Get sounds list
        XmlReader.Element soundsList = element.getChildByName("sounds");

        if(soundsList != null)
            for(XmlReader.Element child : soundsList.getChildrenByName("sound"))
                sounds.add(AudioManager.getAudioFile(child.getText()));

        // Get buffered
        node = element.getChildByName("buffered");

        // buffered is optional
        buffered = node != null && Boolean.parseBoolean(node.getText());
    }

    /**
     * Check if key corresponds with the name
     * @param name key name
     * @return Is this the key you're looking for ?
     */
    public boolean equals(String name) {
        return name.equals(this.name);
    }

    /**
     * Check if key is pressed
     * @return is the key pressed
     */
    public boolean isPressed() {
        if(buffered)
            pressed =  Gdx.input.isKeyJustPressed(keyCode);
        else
            pressed = Gdx.input.isKeyPressed(keyCode);

        if(pressed && !sounds.isEmpty())
            playKeySound();

        return pressed;
    }

    /**
     * Is the active
     * @return is the key active
     */
    public boolean isActive() {
        return pressed;
    }

    /**
     * Play key bound to key
     */
    private void playKeySound() {
        // Play sound
        sounds.get(soundIndex).play(AudioManager.getVolume());

        // Update sound index
        soundIndex = (soundIndex >= sounds.size() - 1) ? 0 : soundIndex + 1;
    }
}