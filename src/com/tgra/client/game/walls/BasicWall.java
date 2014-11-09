package com.tgra.client.game.walls;

import com.badlogic.gdx.math.Vector3;

/**
 * Created by jakob on 9.11.2014.
 */
public class BasicWall extends AbstractWall {

    public BasicWall() {
    }

    public BasicWall(Vector3 position, float xSize, float ySize, float zSize) {
        this.position = position;
        this.xSize = xSize;
        this.ySize = ySize;
        this.zSize = zSize;

    }


    @Override
    protected void initializeWall() {

    }
}
