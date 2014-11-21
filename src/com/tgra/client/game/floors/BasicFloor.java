package com.tgra.client.game.floors;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import com.tgra.client.game.World;
import com.tgra.client.game.shapes.Box;

/**
 * Created by jakob on 9.11.2014.
 */
public class BasicFloor extends AbstractFloor {
    private Box floor;

    public BasicFloor(Vector3 position, float xSize, float ySize, float zSize) {
        this.position = position;
        this.xSize = xSize;
        this.ySize = ySize;
        this.zSize = zSize;

        build();
    }

    @Override
    protected void build() {
        floor = new Box("floor.jpg", position, xSize, ySize, zSize);
        floor.build(World.getInstance().getModelBuilder(), 0);
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        floor.render(modelBatch, environment);
    }
}
