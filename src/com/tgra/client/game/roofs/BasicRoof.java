package com.tgra.client.game.roofs;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import com.tgra.client.game.object.AbstractObject;
import com.tgra.client.game.shapes.Box;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 11/10/2014
 * Time : 04:13
 */
public class BasicRoof extends AbstractRoof {
    private Box floor;

    public BasicRoof(Vector3 position, float xSize, float ySize, float zSize) {
        this.position = position;
        this.xSize = xSize;
        this.ySize = ySize;
        this.zSize = zSize;

        build();
    }

    @Override
    protected void build() {
        floor = new Box("roof.jpg", position, xSize, ySize, zSize);
        floor.build(AbstractObject.builder);
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        floor.render(modelBatch, environment);
    }
}
