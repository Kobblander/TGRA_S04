package com.tgra.client.game.rooms;

import com.tgra.client.game.object.AbstractObject;
import com.tgra.client.game.walls.Wall;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>AbstractRoom</h1>
 * <h2>com.tgra.client.game.rooms</h2>
 * <p></p>
 * Created on 8.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public abstract class AbstractRoom extends AbstractObject implements Room {

    protected List<Wall> walls = new ArrayList<Wall>();

    /**
     * It is optional to implement this update function.
     * Only do so if it is needed to update the rooms variables.
     * @param deltaTime Current deltaTime.
     */
    @Override
    public void update(float deltaTime) {

    }
}
