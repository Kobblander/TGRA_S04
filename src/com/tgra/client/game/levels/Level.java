package com.tgra.client.game.levels;

import com.tgra.client.game.rooms.Room;
import com.tgra.client.game.object.Object;

/**
 * <h1>Level</h1>
 * <h2>com.tgra.client.game.levels</h2>
 * <p></p>
 * Created on 8.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public interface Level extends Object {

    /**
     * Adds a room
     */
    public LevelPos addRoomToLevel(Room room, LevelPos levelPos);

    /**
     * Initially adds the door in the middle.
     */
    public void addDoorway(LevelPos levelPosA, LevelPos levelPosB);

}
