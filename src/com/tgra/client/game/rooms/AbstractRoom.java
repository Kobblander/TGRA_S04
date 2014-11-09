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
    protected int roomXSize = 1;
    protected int roomYSize = 1;
    protected int roomZSize = 1;

    // Unit size of a room.
    // A doorway would take up a single unit.
    protected float unitSize = 2.0f;

    // Number of units per roomSize
    // This needs to be an odd number.
    // A room of size 5 would have 5x5 units
    // Each unit is of size 200.0f then the actualXSize
    // would be for example 5*5*200.0f
    protected int unitFactor = 3;

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

    protected abstract void initializeRoom();


    /**
     * It is optional to implement this update function.
     * Only do so if it is needed to update the rooms variables.
     * @param deltaTime Current deltaTime.
     */
    @Override
    public void update(float deltaTime) {

    }
}
