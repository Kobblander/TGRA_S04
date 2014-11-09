package com.tgra.client.game.walls;

import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import com.tgra.client.game.GameFactory;
import com.tgra.client.game.object.AbstractObject;
import com.tgra.client.game.shapes.Box;
import com.tgra.client.game.shapes.Shape;
import com.tgra.client.utility.Lights;

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
        initializeWall();
    }


    @Override
    protected void initializeWall() {
        box = GameFactory.createWoodenBox(position, thickness, height, length);
        box.build(AbstractObject.builder);
        box.setRotation(new Vector3(0, 1, 0), rotation);
    }

    @Override
    public void render() {
        box.render(new ModelBatch(), Lights.getEnvironment());
    }
}
