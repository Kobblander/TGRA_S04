package com.tgra.client.game.doors;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import com.tgra.client.game.keys.Key;
import javafx.geometry.Side;

/**
 * <h1>BasicDoor</h1>
 * <h2>com.tgra.client.game.doors</h2>
 * <p></p>
 * Created on 19.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class BasicDoor extends AbstractDoor {

    public BasicDoor(Vector3 position, Side side) {
        this.position = position;
        this.side = side;
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        box.render(modelBatch, environment);
    }
}
