package com.tgra.client.game.mechanisms;

import com.tgra.client.game.doors.Door;
import com.tgra.client.game.keys.Key;
import com.tgra.client.game.rooms.Room;

import java.util.Collection;
import java.util.List;

/**
 * <h1>DoorLockMechanism</h1>
 * <h2>com.tgra.client.game.mechanisms</h2>
 * <p>This class handles a door, room and key relationship.</p>
 * Created on 19.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public interface DoorLockMechanism {

    public void addKeyToDoor(Door door, Key key);
    public void collectKey(Door door, Key key);

    public List getDoors();
    public List getKeys();

}
