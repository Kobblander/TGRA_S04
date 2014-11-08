package com.tgra.client;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.lwjgl.LWJGLException;

public class DesktopLauncher {
    public static void main (String[] arg) throws LWJGLException {
        LwjglApplicationConfiguration config = configuration("Space thingy", 800, 600);
        new LwjglApplication(new MyGame(), config);
    }

    /**
     * Sets LwjglApplication to fullscreen
     * @param title Window title
    */
    public static LwjglApplicationConfiguration configuration(String title) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        Graphics.DisplayMode window = LwjglApplicationConfiguration.getDesktopDisplayMode();

        // Basic Setup
        config.title = title;
        config.width = window.width;
        config.height = window.height;
        config.fullscreen = true;
        config.resizable = false;

        return config;
    }

    /**
     * Sets LwjglApplication to a specific window size
     * @param title Window title
     * @param width Window width
     * @param height Window height
     */
    public static LwjglApplicationConfiguration configuration(String title, int width, int height) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        // Basic Setup
        config.title = title;
        config.width = width;
        config.height = height;
        config.resizable = false;

        return config;
    }

}
