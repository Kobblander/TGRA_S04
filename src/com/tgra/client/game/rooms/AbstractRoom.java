package com.tgra.client.game.rooms;

import com.tgra.client.game.floors.Floor;
import com.tgra.client.game.object.AbstractObject;
import com.tgra.client.game.walls.Wall;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>AbstractRoom</h1>
 * <h2>com.tgra.client.game.rooms</h2>
 * <p>This class contains all common variables and functions between rooms.</p>
 * Created on 8.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public abstract class AbstractRoom extends AbstractObject implements Room {


    // Currently rooms are symmetric
    // A room of size 1 fits one cell in a level.
    protected int roomSize = 1;

    // Unit size of a room.
    // A doorway would take up a single unit.
    protected float unitSize = 200.0f;

    // Number of units per roomSize
    // This needs to be an odd number.
    protected int unitFactor = 5;

    // This is will be multiplied by the unitSize, default 1.
    protected int xUnits = 1;
    protected int yUnits = 1;
    protected int zUnits = 1;

    // The actual size of the room is xSize * unitSize.
    protected float actualXSize = 0;
    protected float actualYSize = 0;
    protected float actualZSize = 0;

    protected List<Wall> outerWalls = new ArrayList<Wall>();

    protected Floor floor; /*= new Floor();*/

    /* protected Roof roof = new Roof(); */

    /**
     * It is optional to implement the update function.
     * @param deltaTime Current deltatime.
     */
    @Override
    public void update(float deltaTime) {

    }

    protected abstract void initializeRoom();


}
