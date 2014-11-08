package com.tgra.client.utility;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.math.Vector3;
import com.tgra.client.graphics.*;
import com.tgra.client.managers.AudioManager;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 10/2/2014
 * Time : 15:51
 */
public class Lights {
    private static Environment environment;
    private static PointLight castLight;
    private static Color lightColor;
    private static PointLight flashLight;
    private static boolean isFlashLight = false;
    private Player player;

    public Lights(Player player) {
        this.player = player;
        environment = new Environment();

        init();
    }

    /**
     * Initialise default lighting
     */
    private static void init() {
        environment.clear();

        // AmbientLight
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.2f, 0.2f, 0.2f, 1f));

        // Light color
        lightColor = new Color(0.2f, 0.2f, 0.2f, 0f);

        // Cast lights
        environment.add(castLight = new PointLight().set(lightColor, new Vector3(2f, 2f, -2f), 50f));

        // Flash light
        environment.add(flashLight = new PointLight());
        flashLight.color.set(new Color(0.4f, 0.4f, 0.4f, 0f));
        flashLight.intensity = 0f;
    }

    public void render(float delta) {
        if(isFlashLight)
            player.flashLight(flashLight);
    }

    public static void flashLightOn() {
        isFlashLight = !isFlashLight;

        if(isFlashLight) {
            flashLight.intensity = 10f;
            AudioManager.play("flashlightOn");
        } else {
            flashLight.intensity = 0f;
            AudioManager.play("flashlightOff");
        }
    }

    public static Environment getEnvironment() {
        return environment;
    }
}
