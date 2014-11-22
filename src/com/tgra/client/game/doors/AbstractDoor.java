package com.tgra.client.game.doors;

import com.badlogic.gdx.math.Vector3;
import com.tgra.client.game.GameFactory;
import com.tgra.client.game.object.AbstractObject;
import com.tgra.client.game.shapes.Box;
import com.tgra.client.managers.AudioManager;
import javafx.geometry.Side;

/**
 * <h1>AbstractDoor</h1>
 * <h2>com.tgra.client.game.doors</h2>
 * <p></p>
 * Created on 19.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public abstract class AbstractDoor extends AbstractObject implements Door {

    protected Box box;
    protected Side side;
    protected boolean opening, open;

    protected final float thickness = 0.5f;
    protected float height;
    protected float length;

    protected final float openSpeed = 0.2f;
    protected float doorPos = 0f;
    protected float doorTopStop = 2.4f;

    protected GameFactory gameFactory = GameFactory.getInstance();

    @Override
    public void open() {
        AudioManager.play("door_unlock");
        this.open = true;
        this.opening = true;
    }

    @Override
    public void update(float deltaTime) {
        // TODO: If open start animation or simply remove box
        if (open) {
            //if (position.y < 3.0f)

                    /*
            if (box != null && box.getPosition() != null && doorPos < doorTopStop) {
                box.translate(0, openSpeed * deltaTime, 0);
                doorPos += openSpeed * deltaTime;
                */

            if (box != null && box.getPosition() != null && position.y < 2.0f) {
                box.translate(0, openSpeed * deltaTime, 0);
            } else if(opening) {
                opening = false;
                AudioManager.stop("door_unlock");
            }

        }
    }

    @Override
    public void initDoor(Vector3 pos, Side side, float length, float height, float thickness) {
        this.position = pos;
        //this.thickness = thickness;
        this.height = height;
        this.length = length;
        this.side = side;

        build();
    }

    protected abstract void build();
}
