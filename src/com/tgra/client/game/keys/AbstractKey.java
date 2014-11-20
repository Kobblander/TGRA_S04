package com.tgra.client.game.keys;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import com.tgra.client.game.doors.Door;
import com.tgra.client.game.mechanisms.DoorLockMechanism;
import com.tgra.client.game.object.AbstractObject;
import com.tgra.client.game.shapes.Box;

/**
 * <h1>AbstractKey</h1>
 * <h2>com.tgra.client.game.keys</h2>
 * <p></p>
 * Created on 19.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public abstract class AbstractKey extends AbstractObject implements Key {

    protected Box tempBox;
    protected Door door;
    protected boolean collected;
    protected DoorLockMechanism doorLockMechanism;

    @Override
    public boolean isCollected() {
        return collected;
    }

    @Override
    public void update(float deltaTime) {
        // TODO: Make it hobble, wobble and bobble.
    }

    @Override
    public void setPosition(Vector3 position) {
        this.position = position;
        build();
    }

    @Override
    public void setDoor(Door door) {
        this.door = door;
    }

    protected abstract void build();

    @Override
    public void pickup() {
        collected = true;
        doorLockMechanism.collectKey(door, this);
    }

    @Override
    public void setDoorLockMechanism(DoorLockMechanism doorLockMechanism) {
        this.doorLockMechanism = doorLockMechanism;
    }
}
