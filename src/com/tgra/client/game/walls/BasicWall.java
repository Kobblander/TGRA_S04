package com.tgra.client.game.walls;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import com.tgra.client.game.object.AbstractObject;
import com.tgra.client.game.shapes.Box;

/**
 * Created by jakob on 9.11.2014.
 */
public class BasicWall extends AbstractWall {


    public BasicWall(Vector3 position, float rotation, float length, float height, float thickness) {
        this.position = position;
        this.thickness = thickness;
        this.height = height;
        this.length = length;
        this.rotation = rotation;

        build();
    }


    @Override
    protected void build() {
        box = new Box("wall.jpg", position, thickness, height, length);
        box.build(AbstractObject.builder);
        box.setRotation(rotation);
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        box.render(modelBatch, environment);
    }
}
