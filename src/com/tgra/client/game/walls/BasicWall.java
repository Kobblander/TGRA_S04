package com.tgra.client.game.walls;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.tgra.client.game.World;
import com.tgra.client.game.shapes.Box;

/**
 * Created by jakob on 9.11.2014.
 */
public class BasicWall extends AbstractWall {

    protected Box box;

    public BasicWall() {}

    @Override
    protected void build() {
        box = new Box("wall.jpg", position, thickness, height, length);
        box.build(World.getInstance().getModelBuilder(), rotation);
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        box.render(modelBatch, environment);
    }

    @Override
    public boolean isHit(BoundingBox player) {
        return box.isHit(player);
    }
}
