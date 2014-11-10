package com.tgra.client.game.rooms;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import com.tgra.client.game.GameFactory;
import com.tgra.client.game.object.Object;
import com.tgra.client.game.walls.Wall;

/**
 * <h1>BasicRoom</h1>
 * <h2>com.tgra.client.game.rooms</h2>
 * <p></p>
 * Created on 8.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class BasicRoom extends AbstractRoom {


    /**
     * This constructor makes a perfect square room.
     * Constructor for a basic room.
     * @param position THIS PARAMETER WILL BE REFACTORED LATER
     *                 TO BE THE POSITION WITHIN THE LEVEL
     *                 Or maybe not... The level will decide the rooms position.
     */
    public BasicRoom(Vector3 position, int roomSize) {
        this.position = position;
        this.roomXSize = roomSize;
        this.roomYSize = roomSize;
        this.roomZSize = roomSize;

        initializeRoom();
    }

    @Override
    protected void initializeRoom() {
        // A room of size 5 would have 5x5 units
        // Each unit is of size 200.0f then the actualXSize
        // would be for example 5*5*200.0f

        // Calculate how many units the room is
        // unitFactor is how many units the room is given its size
        this.xUnits = roomXSize * unitFactor;
        this.yUnits = roomYSize * unitFactor;
        this.zUnits = roomZSize * unitFactor;

        // Calculate the actual size of the room in float
        this.actualXSize = this.xUnits * unitSize;
        this.actualYSize = (this.yUnits * unitSize) / 2;
        this.actualZSize = this.zUnits * unitSize;

        // Calculate positions of outerWalls
        float xPos = this.position.x + this.actualXSize / 2 + thickness / 2;
        float yPos = this.position.y + this.actualYSize / 2;
        float zPos = this.position.z + this.actualZSize / 2 + thickness / 2;

        // Create all the rooms objects.
        Wall wallRight = GameFactory.createBasicWall(
                new Vector3(xPos, yPos,  position.z),
                0.0f,
                actualZSize,
                actualYSize,
                thickness
        );

        Wall wallFront = GameFactory.createBasicWall(
                new Vector3(zPos - this.actualZSize - thickness, yPos, -position.x),
                -90.0f,
                actualXSize,
                actualYSize,
                thickness
        );

        Wall wallLeft = GameFactory.createBasicWall(
                new Vector3(xPos  - this.actualXSize - thickness, yPos,  position.z),
                0.0f,
                actualZSize,
                actualYSize,
                thickness
        );

        Wall wallBottom = GameFactory.createBasicWall(
                new Vector3(zPos, yPos, -position.x),
                -90.0f,
                actualXSize,
                actualYSize,
                thickness
        );

        this.outerWalls.add(wallRight);
        this.outerWalls.add(wallLeft);
        this.outerWalls.add(wallFront);
        this.outerWalls.add(wallBottom);

        this.floor = GameFactory.createBasicFloor(position, actualXSize, floorThickness, actualZSize);

        Vector3 roofPos = new Vector3(position.x, actualYSize - 1.75f, position.z);

        this.roof = GameFactory.createBasicRoof(roofPos, actualXSize, roofThickness, actualZSize);
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        for (Wall w : outerWalls) {
            Object wo = (Object) w;
            wo.render(modelBatch, environment);
        }

        Object f = (Object) floor;
        f.render(modelBatch, environment);

        Object r = (Object) roof;
        r.render(modelBatch, environment);
    }
}
