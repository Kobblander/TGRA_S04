package com.tgra.client.game.walls;

import com.badlogic.gdx.math.Vector3;
import com.tgra.client.game.GameFactory;
import com.tgra.client.game.object.AbstractObject;
import com.tgra.client.game.shapes.Shape;

/**
 * Created by jakob on 9.11.2014.
 */
public class BasicWall extends AbstractWall {


    public BasicWall(Vector3 position, float angle) {
        this.position = position;
        this.xSize = xSize;
        this.ySize = ySize;
        this.zSize = zSize;
        initializeWall();
    }


    @Override
    protected void initializeWall() {
        Shape box = GameFactory.createWoodenBox(position, xSize, ySize, zSize);
        box.build(AbstractObject.builder);

    }

    @Override
    public void render() {

    }
}
