package com.tgra.client.game.rooms;

import com.badlogic.gdx.math.Vector3;

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
    }
}
