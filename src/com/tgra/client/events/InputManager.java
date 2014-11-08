package com.tgra.client.events;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.MathUtils;
import com.tgra.client.graphics.*;
import com.tgra.client.utility.Lights;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 10/18/2014
 * Time : 00:32
 */
public class InputManager implements InputProcessor {
    static final float MOUSE_SENSITIVITY = 0.25f;
    static float MOVE_SPEED = 2;
    static final float NORMAL_SPEED = 2;
    static final float RUN_SPEED = 4;
    static final float NINETY_DEGREE = 89.99f;
    private final Player player;

    private PerspectiveCamera camera;
    private int lastX, lastY;
    private float angleX = -90, angleY = 0;
    private float originView;

    private boolean W, A, S, D, SHIFT;

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.A)
            A = true;
        else if (keycode == Input.Keys.D)
            D = true;
        else if (keycode == Input.Keys.S)
            S = true;
        else if (keycode == Input.Keys.W)
            W = true;
        else if(keycode == Input.Keys.SHIFT_LEFT)
            SHIFT = true;
        else if(keycode == Input.Keys.F)
            Lights.flashLightOn();

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.A)
            A = false;
        else if (keycode == Input.Keys.D)
            D = false;
        else if (keycode == Input.Keys.S)
            S = false;
        else if (keycode == Input.Keys.W)
            W = false;
        else if(keycode == Input.Keys.SHIFT_LEFT)
            SHIFT = false;

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        lastX = x;
        lastY = y;

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        angleX += (lastX - x) * MOUSE_SENSITIVITY;
        lastX = x;
        angleY += (lastY - y) * -MOUSE_SENSITIVITY;
        lastY = y;

        if (angleY > NINETY_DEGREE)
            angleY = NINETY_DEGREE;
        else if (angleY < -NINETY_DEGREE)
            angleY = -NINETY_DEGREE;

        final float cos = MathUtils.cosDeg(angleY);

        // Don't fuck with me
        if(cos == 0f)
            return true;

        player.setAngles(angleX, angleY);
        camera.direction.x = MathUtils.cosDeg(angleX) * cos;
        camera.direction.y = MathUtils.sinDeg(angleY) * 1f;
        camera.direction.z = MathUtils.sinDeg(angleX) * cos;

        // Rotate player
        Player.rotate(camera);
        camera.update();

        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        float newView = camera.fieldOfView + amount * 1f;

        if(newView > originView || newView <= 0)
            return true;

        camera.fieldOfView = newView;
        camera.update(false);

        return true;
    }

    public void update(float delta) {
        // No key is down
        if (!(A | D | W | S))
            return;

        // Player run
        if(SHIFT)
            MOVE_SPEED = RUN_SPEED;
        else
            MOVE_SPEED = NORMAL_SPEED;

        // Move speed full sqrt of normal
        if ((A ^ D) & (W ^ S))
            delta *= (float) Math.sqrt(MOVE_SPEED);
        else {
            // Move speed full
            delta *= MOVE_SPEED;
        }

        if (A & !D)
            player.movePlayer(camera, delta, 'A');

        if (D & !A)
            player.movePlayer(camera, delta, 'D');

        if (W & !S)
            player.movePlayer(camera, delta, 'W');

        if (S & !W)
            player.movePlayer(camera, delta, 'S');

        camera.update();
    }

    public InputManager(final PerspectiveCamera camera, Player player) {
        this.camera = camera;
        this.player = player;

        originView = camera.fieldOfView;
        player.setCameraPosition(camera, camera.position);
        camera.update();
    }
}

