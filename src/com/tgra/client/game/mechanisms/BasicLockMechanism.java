package com.tgra.client.game.mechanisms;

import com.tgra.client.game.doors.Door;
import com.tgra.client.game.keys.Key;
import com.tgra.client.utility.MapUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>BasicLockMechanism</h1>
 * <h2>com.tgra.client.game.mechanisms</h2>
 * <p></p>
 * Created on 19.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class BasicLockMechanism implements DoorLockMechanism {

    protected Map<Door, Key> keyDoorMap = new HashMap<Door, Key>();

    public BasicLockMechanism() {
    }

    @Override
    public void addKeyToDoor(Key key, Door door) {
        keyDoorMap.put(door, key);
    }

    @Override
    public void collectKey(Key key) {
        if (!keyDoorMap.containsValue(key)) {
            return;
        }

        Object d = MapUtils.getKeyFromValue(keyDoorMap, (Object) key);
        Door door = (Door) d;

    }

}
