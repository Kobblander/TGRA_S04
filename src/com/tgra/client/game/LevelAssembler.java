package com.tgra.client.game;

import com.tgra.client.game.doors.Door;
import com.tgra.client.game.keys.Key;
import com.tgra.client.game.levels.Level;
import com.tgra.client.game.levels.LevelPos;
import com.tgra.client.game.mechanisms.BasicLockMechanism;
import com.tgra.client.game.rooms.KeyRoom;
import com.tgra.client.game.rooms.Room;
import javafx.geometry.Side;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>LevelAssembler</h1>
 * <h2>com.tgra.client.game</h2>
 * <p></p>
 * Created on 20.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class LevelAssembler {

    private static GameFactory gameFactory = GameFactory.getInstance();

    private static List<BasicLockMechanism> basicLockMechanismList = new ArrayList<BasicLockMechanism>();

    private static BasicLockMechanism blm = new BasicLockMechanism();

    private LevelAssembler() {
    }

    public static void assembleKobbaLevel(Level level) {
        Key key1 = gameFactory.createKey(blm);
        Key key2 = gameFactory.createKey(blm);
        Key key3 = gameFactory.createKey(blm);
        Door door1 = gameFactory.createBasicDoor();
        blm.addKeyToDoor(door1, key1);
        blm.addKeyToDoor(door1, key2);
        blm.addKeyToDoor(door1, key3);
        basicLockMechanismList.add(blm);

        KeyRoom kr1 = (KeyRoom) level.addRoomToLevel(gameFactory.createKeyRoom(2), new LevelPos(1, 0, -1));
        KeyRoom kr2 = (KeyRoom) level.addRoomToLevel(gameFactory.createKeyRoom(1), new LevelPos(5, 0, -1));
        KeyRoom kr3 = (KeyRoom) level.addRoomToLevel(gameFactory.createKeyRoom(5), new LevelPos(6, 0, 0));
        kr1.setKey(key1);
        kr2.setKey(key2);
        kr3.setKey(key3);


        level.addRoomToLevel(gameFactory.createBasicRoom(3, 1, 1), new LevelPos(2, 0, -1));


        level.addRoomToLevel(gameFactory.createBasicRoom(1, 2, 1), new LevelPos(0, 0, 0));

        // Add door to this room
        Room room1 = level.addRoomToLevel(gameFactory.createBasicRoom(5, 2, 1), new LevelPos(1, 0, 0));
        room1.setDoor(door1, Side.BOTTOM);


        level.addRoomToLevel(gameFactory.createBasicRoom(3, 2, 1), new LevelPos(-2, 0, 3));
        level.addRoomToLevel(gameFactory.createBasicRoom(1, 1, 1), new LevelPos(-1, 0, 2));
        level.addRoomToLevel(gameFactory.createBasicRoom(1, 1, 1), new LevelPos(-1, 0, 4));
        level.addRoomToLevel(gameFactory.createBasicRoom(1, 1, 1), new LevelPos(-3, 0, 3));

        //level.addRoomToLevel(gameFactory.createMazeRoom(), new LevelPos(1, 0, 1));


        level.addRoomToLevel(gameFactory.createBasicRoom(3, 3, 3), new LevelPos(2, 0, 6));





        level.addDoorway(new LevelPos(1, 0, -1), new LevelPos(2, 0, -1));
        level.addDoorway(new LevelPos(5, 0, -1), new LevelPos(2, 0, -1));

        //level.addDoorway(new LevelPos(0, 0, 0), new LevelPos(0, 0, 1));
        level.addDoorway(new LevelPos(5, 0, 0), new LevelPos(6, 0, 0));
        level.addDoorway(new LevelPos(3, 0, 0), new LevelPos(3, 0, -1));
        level.addDoorway(new LevelPos(2, 0, 0), new LevelPos(2, 0, 1));
        level.addDoorway(new LevelPos(1, 0, 3), new LevelPos(0, 0, 3));
        level.addDoorway(new LevelPos(0, 0, 0), new LevelPos(1, 0, 0));
        level.addDoorway(new LevelPos(-1, 0, 3), new LevelPos(-1, 0, 2));
        level.addDoorway(new LevelPos(-1, 0, 3), new LevelPos(-1, 0, 4));
        level.addDoorway(new LevelPos(-2, 0, 3), new LevelPos(-3, 0, 3));
        level.addDoorway(new LevelPos(2, 0, 1), new LevelPos(2, 0, 6));
    }

    public static void assembleTestLevel(Level level) {
        level.addRoomToLevel(gameFactory.createBasicRoom(1, 2, 1), new LevelPos(0, 0, 0));
    }
}
