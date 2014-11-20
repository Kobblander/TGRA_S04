package com.tgra.client.game.keys;

import com.tgra.client.game.doors.Door;
import com.tgra.client.game.mechanisms.DoorLockMechanism;
import com.tgra.client.game.object.Object;
/**
 * <h1>Keys</h1>
 * <h2>com.tgra.client.game.keys</h2>
 * <p></p>
 * Created on 19.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public interface Key extends Object {

    public void pickup();

    public void setDoorLockMechanism(DoorLockMechanism doorLockMechanism);

    public void setDoor(Door door);

    public boolean isCollected();

}
