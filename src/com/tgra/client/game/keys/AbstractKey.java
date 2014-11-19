package com.tgra.client.game.keys;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
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
    protected boolean collected;
    protected DoorLockMechanism doorLockMechanism;

    @Override
    public void update(float deltaTime) {
        // TODO: Make it hobble, wobble and bobble.
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        if (!collected) {
            tempBox.render(modelBatch, environment);
        }
    }

    @Override
    public void pickup() {
        collected = true;
        doorLockMechanism.collectKey(this);
    }

    @Override
    public void setDoorLockMechanism(DoorLockMechanism doorLockMechanism) {
        this.doorLockMechanism = doorLockMechanism;
    }
}
