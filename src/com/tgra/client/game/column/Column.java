package com.tgra.client.game.column;

import com.badlogic.gdx.math.Vector3;
import com.tgra.client.game.object.Object;

/**
 * <h1>Column</h1>
 * <h2>com.tgra.client.game.column</h2>
 * <p></p>
 * Created on 20.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public interface Column extends Object {
    public void setRotation(float rotation, Vector3 axis);
}
