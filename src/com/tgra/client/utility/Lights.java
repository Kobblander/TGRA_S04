package com.tgra.client.utility;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.math.Vector3;
import com.tgra.client.graphics.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 10/2/2014
 * Time : 15:51
 */
public class Lights {
    private static Environment environment;
    private static Color lightColor;
    private static List<PointLight> lights;
    private static int lightIndex = 0;
    private Player player;

    public Lights(Player player) {
        this.player = player;
        environment = new Environment();
        lights = new ArrayList<PointLight>();

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
        lightColor = new Color(0.4f, 0.2f, 0.3f, 0f);

        // Cast lights
        //environment.add(castLight = new PointLight().set(lightColor, new Vector3(2f, 2f, -2f), 50f));

    }

    public void render(float delta) {
        environment.clear();

        // AmbientLight
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.2f, 0.2f, 0.2f, 1f));

        int size = (lights.size() >= 5) ? 5 : lights.size();

        for(int i = 0; i < size; i++) {
            environment.add(lights.get(i));
        }
    }

    public static void flashLightOn() {
    }

    public static PointLight addLight(Vector3 pos) {
        PointLight light = new PointLight().set(lightColor, new Vector3(pos.x - 0.5f, pos.y + 0.5f, pos.z - 0.5f), 20);

        lights.add(light);

        return light;
    }

    public static void removeLight(PointLight light) {
        lights.remove(light);
    }

    public static Environment getEnvironment() {
        return environment;
    }
}
