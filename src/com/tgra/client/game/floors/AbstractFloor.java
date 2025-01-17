package com.tgra.client.game.floors;

import com.badlogic.gdx.math.collision.BoundingBox;
import com.tgra.client.game.object.AbstractObject;

/**
 * <h1>AbstractFloor</h1>
 * <h2>com.tgra.client.game.floors</h2>
 * <p></p>
 * Created on 8.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public abstract class AbstractFloor extends AbstractObject implements Floor {

    protected float xSize;
    protected float ySize;
    protected float zSize;

    protected abstract void build();

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public boolean isHit(BoundingBox player) {
        return false;
    }
}
