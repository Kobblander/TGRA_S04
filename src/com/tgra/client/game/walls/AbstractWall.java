package com.tgra.client.game.walls;

import com.badlogic.gdx.math.Vector3;
import com.tgra.client.game.object.AbstractObject;
import com.tgra.client.game.rooms.RoomData;
import com.tgra.client.game.shapes.Box;

/**
 * <h1>AbstractWallt</h1>
 * <h2>com.tgra.client.game.walls</h2>
 * <p></p>
 * Created on 8.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public abstract class AbstractWall extends AbstractObject implements Wall {


    protected float xSize;
    protected float ySize;
    protected float zSize;

    protected RoomData roomData;

    // Default thickness of all walls.
    protected float thickness = 5.0f;

    // Length, height and rotation of the wall.
    protected float height;
    protected float length;
    protected float rotation;

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void setRoomData(RoomData roomData) {
        this.roomData = roomData;
    }

    @Override
    public void initWall(Vector3 pos, float rotation, float length, float height, float thickness) {
        this.position = pos;
        this.thickness = thickness;
        this.height = height;
        this.length = length;
        this.rotation = rotation;

        build();
    }

    protected abstract void build();
}
