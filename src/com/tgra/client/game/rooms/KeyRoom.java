package com.tgra.client.game.rooms;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.tgra.client.game.keys.Key;
import com.tgra.client.game.mechanisms.DoorLockMechanism;

/**
 * <h1>KeyRoom</h1>
 * <h2>com.tgra.client.game.rooms</h2>
 * <p></p>
 * Created on 19.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class KeyRoom extends AbstractRoom {

    protected Key key;
    protected DoorLockMechanism doorLockMechanism;

    public KeyRoom(int roomYSize) {
        initializeRoom(1, roomYSize, 1);
    }

    public void setDoorLockMechanism(DoorLockMechanism doorLockMechanism) {
        this.doorLockMechanism = doorLockMechanism;
    }

    @Override
    protected void initDoodads() {

    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {

    }
}
