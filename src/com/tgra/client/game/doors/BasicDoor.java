package com.tgra.client.game.doors;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.tgra.client.game.World;
import com.tgra.client.game.shapes.Box;

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

    public BasicDoor() {
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        box.render(modelBatch, environment);
    }

    @Override
    public boolean isHit(BoundingBox player) {
        return box.isHit(player);
    }

    @Override
    protected void build() {
        if (side.isHorizontal()) {
            box = new Box("wood.jpg", position, length, height, thickness);
        } else {
            box = new Box("wood.jpg", position, thickness, height, length);
        }
        box.build(World.getInstance().getModelBuilder(), 0);

    }
}
