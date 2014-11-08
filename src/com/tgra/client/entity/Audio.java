package com.tgra.client.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.XmlReader;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 10/17/2014
 * Time : 00:39
 */
public class Audio {
    private Sound sound;
    private long soundID = -1;

    // Stats
    private String name;
    private boolean loop = false;
    private float intensity = 1f;
    private int priority = 0;

    public Audio(XmlReader.Element file)
    {
        name = file.getAttribute("name");

        validate(file);
    }

    /**
     * Validate the xml node
     * @param file xml node
     */
    private void validate(XmlReader.Element file) {
        // Get intensity
        XmlReader.Element node = file.getChildByName("intensity");

        // Intensity is optional
        if(node != null)
            intensity = Float.parseFloat(node.getText());

        // Get loop
        node = file.getChildByName("loop");

        // looping is optional
        if(node != null)
            loop = Boolean.parseBoolean(node.getText());

        // Get Path
        String path = file.getChildByName("path").getText();
        sound = Gdx.audio.newSound(Gdx.files.internal("data/sound/" + path));

        // Get priority
        node = file.getChildByName("priority");

        // priority is optional
        if(node != null)
            priority = Integer.parseInt(node.getText());
    }

    /**
     * Play the audio
     * @param volume sound volume
     */
    public void play(float volume) {
        // Sound can't loop twice
        if(soundID != -1)
            sound.setLooping(soundID, false);

        // Play sound
        if(loop)
            soundID = sound.loop(volume * intensity);
        else
            soundID = sound.play(volume * intensity);

        sound.setPriority(soundID, priority);
    }

    /**
     * Stop the audio
     */
    public void stop() {
        sound.stop();
    }

    /**
     * End the sound loop
     */
    public void endLoop() {
        if(soundID != -1)
            sound.setLooping(soundID, false);
    }

    /**
     * Check if sound corresponds with the name
     * @param name sound name
     * @return Is the sound you're looking for ?
     */
    public boolean equals(String name) {
        return name.equals(this.name);
    }
}
