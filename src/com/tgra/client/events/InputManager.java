package com.tgra.client.events;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.MathUtils;
import com.tgra.client.graphics.Player;
import com.tgra.client.utility.Lights;
import org.lwjgl.input.Mouse;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 10/18/2014
 * Time : 00:32
 */
public class InputManager implements InputProcessor {
    static final float MOUSE_SENSITIVITY = 0.25f;
    static float MOVE_SPEED = 10;
    static final float NORMAL_SPEED = 5;
    static final float RUN_SPEED = 15;
    static final float NINETY_DEGREE = 89.99f;
    private final Player player;

    private PerspectiveCamera camera;
    private int lastX, lastY;
    private float angleX = 90, angleY = 0;
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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        angleX -= (lastX - screenX) * MOUSE_SENSITIVITY;
        lastX = screenX;
        angleY -= (lastY - screenY) * -MOUSE_SENSITIVITY;
        lastY = screenY;

        if (angleY > NINETY_DEGREE)
            angleY = NINETY_DEGREE;
        else if (angleY < -NINETY_DEGREE)
            angleY = -NINETY_DEGREE;

        setCameraDirection();

        return true;
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
        Mouse.setGrabbed(true);
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
            delta *= MOVE_SPEED;
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

    public void setCameraDirection() {
        final float cos = MathUtils.cosDeg(angleY);

        // Don't fuck with me
        // I ain't nothin' to fuck with
        if(cos == 0f)
            return;

        player.setAngles(angleX, angleY);
        camera.direction.x = MathUtils.cosDeg(angleX) * cos;
        camera.direction.y = MathUtils.sinDeg(angleY) * 1f;
        camera.direction.z = MathUtils.sinDeg(angleX) * cos;

        // Rotate player
        Player.rotate(camera);
        camera.update();
    }

    public InputManager(final PerspectiveCamera camera, Player player) {
        this.camera = camera;
        this.player = player;

        this.lastX = Gdx.input.getX();
        this.lastY = Gdx.input.getY();

        originView = camera.fieldOfView;
        player.setCameraPosition(camera, camera.position);
        camera.update();
    }
}
