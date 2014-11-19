package com.tgra.client.game.keys;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.tgra.client.game.object.AbstractObject;

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

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {

    }

    @Override
    public void pickup() {

    }
}
