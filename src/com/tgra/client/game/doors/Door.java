package com.tgra.client.game.doors;

import com.badlogic.gdx.math.Vector3;
import com.tgra.client.game.object.Object;
import javafx.geometry.Side;

/**
 * <h1>Doors</h1>
 * <h2>com.tgra.client.game.doors</h2>
 * <p></p>
 * Created on 19.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public interface Door extends Object {
    public void open();
    public void initDoor(Vector3 pos, Side side, float length, float height, float thickness);
}
