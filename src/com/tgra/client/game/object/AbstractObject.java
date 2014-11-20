package com.tgra.client.game.object;

import com.badlogic.gdx.math.Vector3;

/**
 * <h1>AbstractObject</h1>
 * <h2>com.tgra.client.game</h2>
 * <p></p>
 * Created on 8.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public abstract class AbstractObject implements Object {

    protected Vector3 position;

    @Override
    public abstract void update(float deltaTime);

    @Override
    public Vector3 getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Vector3 position) {
        this.position = position;
    }
}
