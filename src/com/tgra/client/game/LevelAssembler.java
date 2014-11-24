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
        Door firstMainDoor = gameFactory.createBasicDoor();
        Key firstMainKey = gameFactory.createKey(blm, firstMainDoor);
        Key secondMainKey = gameFactory.createKey(blm, firstMainDoor);

        // All rooms leading up to the maze
        roomsBeforeMazeAndMaze(level);

        // When going left from the maze
        roomsLeftOfMaze(level);

        // When going right from the maze
        roomsRightOfMaze(level);


        // Main right maze keyroom
        ((KeyRoom) level.addRoomToLevel(gameFactory.createKeyRoom(3), new LevelPos(-3, 0, 3))).setKey(firstMainKey);

        // Main left maze keyroom
        ((KeyRoom) level.addRoomToLevel(gameFactory.createKeyRoom(2), new LevelPos(17, 0, 3))).setKey(secondMainKey);

        level.addRoomToLevel(gameFactory.createMazeRoom(), new LevelPos(1, 0, 1));

        level.addRoomToLevel(gameFactory.createBasicRoom(1, 1, 1), new LevelPos(3, 0, 6)).setDoor(firstMainDoor, Side.BOTTOM);
        // Big room
        level.addRoomToLevel(gameFactory.createBigRoom(5, 5, 7), new LevelPos(1, 0, 7));
        level.addRoomToLevel(gameFactory.createBasicRoom(1, 2, 1), new LevelPos(3, 0, 14));//.setDoor(firstMainDoor, Side.TOP);




        level.addDoorway(new LevelPos(3, 0, 6), new LevelPos(3, 0, 7));
        level.addDoorway(new LevelPos(3, 0, 6), new LevelPos(3, 0, 5));

        level.addDoorway(new LevelPos(3, 0, 13), new LevelPos(3, 0, 14));
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
        level.addDoorway(new LevelPos(6, 0, 3), new LevelPos(5, 0, 3));
        level.addDoorway(new LevelPos(13, 0, 3), new LevelPos(12, 0, 3));

        level.addDoorway(new LevelPos(13, 0, 0), new LevelPos(13, 0, 1));
        level.addDoorway(new LevelPos(13, 0, 5), new LevelPos(13, 0, 6));

        level.addDoorway(new LevelPos(14, 0, 3), new LevelPos(13, 0, 3));
        level.addDoorway(new LevelPos(15, 0, 4), new LevelPos(15, 0, 5));

        level.addDoorway(new LevelPos(15, 0, 1), new LevelPos(15, 0, 2));

        level.addDoorway(new LevelPos(16, 0, 3), new LevelPos(17, 0, 3));
    }

    private static void roomsLeftOfMaze(Level level) {

        Door door1 = gameFactory.createBasicDoor();
        Key key1 = gameFactory.createKey(blm, door1);
        Key key2 = gameFactory.createKey(blm, door1);

        Door door2 = gameFactory.createBasicDoor();
        Key key3 = gameFactory.createKey(blm, door2);
        Key key4 = gameFactory.createKey(blm, door2);

        // Hallways
        level.addRoomToLevel(gameFactory.createBasicRoom(7, 1, 1), new LevelPos(6, 0, 3));
        level.addRoomToLevel(gameFactory.createBasicRoom(1, 1, 5), new LevelPos(13, 0, 1)).setDoor(door1, Side.RIGHT);

        // Big mid room
        level.addRoomToLevel(gameFactory.createBasicRoom(3, 2, 3), new LevelPos(14, 0, 2)).setDoor(door2, Side.RIGHT);

        // Bottom key room
        ((KeyRoom) level.addRoomToLevel(gameFactory.createKeyRoom(2), new LevelPos(13, 0, 0))).setKey(key1);

        // Top key room
        ((KeyRoom) level.addRoomToLevel(gameFactory.createKeyRoom(2), new LevelPos(13, 0, 6))).setKey(key2);

        ((KeyRoom) level.addRoomToLevel(gameFactory.createKeyRoom(2), new LevelPos(15, 0, 5))).setKey(key3);
        ((KeyRoom) level.addRoomToLevel(gameFactory.createKeyRoom(2), new LevelPos(15, 0, 1))).setKey(key4);


        }

    private static void roomsRightOfMaze(Level level) {

        // Door and keys for stuff right of maze
        Door door2 = gameFactory.createBasicDoor();
        Key key1 = gameFactory.createKey(blm, door2);
        Key key2 = gameFactory.createKey(blm, door2);

        // Hallway from maze to keyrooms
        level.addRoomToLevel(gameFactory.createBasicRoom(3, 2, 1), new LevelPos(-2, 0, 3)).setDoor(door2, Side.LEFT);

        // Keyrooms to open main right maze keyroom
        ((KeyRoom) level.addRoomToLevel(gameFactory.createKeyRoom(1), new LevelPos(-1, 0, 2))).setKey(key1);
        ((KeyRoom) level.addRoomToLevel(gameFactory.createKeyRoom(1), new LevelPos(-1, 0, 4))).setKey(key2);

    }


    private static void roomsBeforeMazeAndMaze(Level level) {
        // First key rooms and its door
        Door door1 = gameFactory.createBasicDoor();
        Key key1 = gameFactory.createKey(blm, door1);
        Key key2 = gameFactory.createKey(blm, door1);
        Key key3 = gameFactory.createKey(blm, door1);

        ((KeyRoom) level.addRoomToLevel(gameFactory.createKeyRoom(2), new LevelPos(1, 0, -1))).setKey(key1);
        ((KeyRoom) level.addRoomToLevel(gameFactory.createKeyRoom(1), new LevelPos(5, 0, -1))).setKey(key2);
        ((KeyRoom) level.addRoomToLevel(gameFactory.createKeyRoom(5), new LevelPos(6, 0, 0))).setKey(key3);

        // Hallway to key rooms
        level.addRoomToLevel(gameFactory.createBasicRoom(3, 1, 1), new LevelPos(2, 0, -1));

        // Start room
        level.addRoomToLevel(gameFactory.createBasicRoom(1, 2, 1), new LevelPos(0, 0, 0));

        // Add door to first hallway
        level.addRoomToLevel(gameFactory.createBasicRoom(5, 2, 1), new LevelPos(1, 0, 0)).setDoor(door1, Side.BOTTOM);
    }

    public static void assembleTestLevel(Level level) {
        level.addRoomToLevel(gameFactory.createBasicRoom(1, 2, 1), new LevelPos(0, 0, 0));
    }
}
