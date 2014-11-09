package com.tgra.client.game.rooms;

import com.badlogic.gdx.math.Vector3;
import com.tgra.client.game.GameFactory;
import com.tgra.client.game.floors.Floor;
import com.tgra.client.game.object.*;
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
     * Constructor for a basic room.
     * @param position THIS PARAMETER WILL BE REFACTORED LATER
     *                 TO BE THE POSITION WITHIN THE LEVEL
     */
    public BasicRoom(Vector3 position, int roomSize) {
        this.position = position;
        this.roomSize = roomSize;

        initializeRoom();
    }

    @Override
    protected void initializeRoom() {
        this.xUnits = roomSize * unitFactor;
        this.yUnits = roomSize * unitFactor;
        this.zUnits = roomSize * unitFactor;

        this.actualXSize = this.xUnits * unitSize;
        this.actualYSize = this.yUnits * unitSize;
        this.actualZSize = this.zUnits * unitSize;

        float xPos = this.position.x + this.actualXSize / 2;
        float yPos = this.position.y + this.actualYSize / 2;
        float zPos = this.position.z + this.actualZSize / 2;

        Wall wall1 = GameFactory.createBasicWall(new Vector3(xPos, yPos, zPos), actualXSize, actualYSize, actualZSize);
        Wall wall2 = GameFactory.createBasicWall(new Vector3(xPos, yPos, -zPos), actualXSize, actualYSize, actualZSize);
        Wall wall3 = GameFactory.createBasicWall(new Vector3(-xPos, yPos, zPos), actualXSize, actualYSize, actualZSize);
        Wall wall4 = GameFactory.createBasicWall(new Vector3(-xPos, yPos, -zPos), actualXSize, actualYSize, actualZSize);
        this.outerWalls.add(wall1);
        this.outerWalls.add(wall2);
        this.outerWalls.add(wall3);
        this.outerWalls.add(wall4);

        Floor floor = GameFactory.createBasicFloor(position, actualXSize, actualYSize, actualZSize);
        this.floor = floor;
    }

}
