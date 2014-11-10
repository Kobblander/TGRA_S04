package com.tgra.client.game.walls;

import com.tgra.client.game.object.AbstractObject;
import com.tgra.client.game.shapes.Box;

/**
 * <h1>AbstractWallt</h1>
 * <h2>com.tgra.client.game.walls</h2>
 * <p></p>
 * Created on 8.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public abstract class AbstractWall extends AbstractObject implements Wall {

    protected Box box;

    protected float xSize;
    protected float ySize;
    protected float zSize;

    // Default thickness of all walls.
    protected float thickness = 5.0f;

    // Length, height and rotation of the wall.
    protected float height;
    protected float length;
    protected float rotation;

    @Override
    public void update(float deltaTime) {

    }

    protected abstract void build();
}
