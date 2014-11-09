package com.tgra.client.game.walls;

import com.tgra.client.game.object.AbstractObject;

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

    protected float xSize;
    protected float ySize;
    protected float zSize;

    @Override
    public void update(float deltaTime) {

    }

    protected abstract void initializeWall();
}
