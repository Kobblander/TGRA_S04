package com.tgra.client.game.mechanisms;

import com.tgra.client.game.doors.Door;
import com.tgra.client.game.keys.Key;
import com.tgra.client.utility.MapUtils;

import java.util.*;

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

    protected Map<Door, List<Key>> keyDoorMap = new HashMap<Door, List<Key>>();

    public BasicLockMechanism() {
    }

    @Override
    public void addKeyToDoor(Door door, Key key) {
        // If the map does not contain the door we must new the
        // array list.
        if (!keyDoorMap.containsKey(door)) {
            keyDoorMap.put(door, new ArrayList<Key>());
        }
        keyDoorMap.get(door).add(key);
    }

    @Override
    public void collectKey(Door door, Key key) {
        // If the door does not contain said key. Simply return.
        List<Key> doorKeys = keyDoorMap.get(door);
        boolean isUnlocked = true;

        if (!doorKeys.contains(key)) {
            return;
        }

        for (Key k : doorKeys) {
            if (!k.isCollected()) {
                isUnlocked = false;
            }
        }

        if (isUnlocked) {
            door.open();
        }

        //Key doorKeys = keyDoorMap.get(door);

    }

    @Override
    public List getDoors() {
        List<Door> result = new ArrayList<Door>();
        result.addAll(keyDoorMap.keySet());
        return result;
    }

    @Override
    public List getKeys() {
        List<Key> result = new ArrayList<Key>();
        for (List<Key> kl : keyDoorMap.values()) {
            result.addAll(kl);
        }
        return result;
    }

}
