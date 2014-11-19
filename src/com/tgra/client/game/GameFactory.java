package com.tgra.client.game;

import com.badlogic.gdx.math.Vector3;
import com.tgra.client.game.floors.BasicFloor;
import com.tgra.client.game.floors.Floor;
import com.tgra.client.game.levels.BasicLevel;
import com.tgra.client.game.levels.Level;
import com.tgra.client.game.levels.LevelPos;
import com.tgra.client.game.maze.Coord;
import com.tgra.client.game.maze.Maze;
import com.tgra.client.game.maze.MazeType;
import com.tgra.client.game.rooms.MazeRoom;
import com.tgra.client.game.walls.MazeWall;
import com.tgra.client.game.object.Object;
import com.tgra.client.game.roofs.BasicRoof;
import com.tgra.client.game.roofs.Roof;
import com.tgra.client.game.rooms.BasicRoom;
import com.tgra.client.game.rooms.Room;
import com.tgra.client.game.shapes.Box;
import com.tgra.client.game.shapes.Cylinder;
import com.tgra.client.game.shapes.Shape;
import com.tgra.client.game.walls.BasicWall;
import com.tgra.client.game.walls.DoorWall;
import com.tgra.client.game.walls.Wall;

/**
 * <h1>EntityFactory</h1>
 * <h2>com.tgra.client</h2>
 * <p>This is a singleton factory which is used to create objects.</p>
 * Created on 8.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class GameFactory {

    private static GameFactory instance = new GameFactory();

    public static GameFactory getInstance() { return instance; }

    private static World world = World.getInstance();

    private GameFactory() {}

    public static Wall createTiledWall() {
        // Get tile texture

        // Create the tiled wall

        // Return the wall object
        return null;
    }

    public Wall createBasicWall() {
        // Create the wall
        Object basicWall = new BasicWall();
        world.addObject(basicWall);

        // Return the wall object
        return (Wall) basicWall;
    }

    public Wall createDoorWall(Room room) {
        // Create the wall
        DoorWall doorWall = new DoorWall();
        doorWall.setRoomData(room.getRoomData());
        world.addObject(doorWall);

        // Return the wall object
        return doorWall;
    }

    public Level createBasicLevel(int levelXSize, int levelYSize) {
        // Create the basic level
        Level level = new BasicLevel(new Vector3(0f, -1.9f, 0f), levelXSize, levelYSize);
        world.addObject(level);


        level.addRoomToLevel(createBasicRoom(3, 1, 1), new LevelPos(2, 0, -1));

        level.addRoomToLevel(createBasicRoom(1, 5, 1), new LevelPos(6, 0, 0));

        level.addRoomToLevel(createBasicRoom(1, 2, 1), new LevelPos(0, 0, 0));
        level.addRoomToLevel(createBasicRoom(5, 1, 1), new LevelPos(1, 0, 0));


        level.addRoomToLevel(createBasicRoom(3, 2, 1), new LevelPos(-2, 0, 3));
        level.addRoomToLevel(createBasicRoom(1, 1, 1), new LevelPos(-1, 0, 2));
        level.addRoomToLevel(createBasicRoom(1, 1, 1), new LevelPos(-1, 0, 4));
        level.addRoomToLevel(createBasicRoom(1, 1, 1), new LevelPos(-3, 0, 3));

        level.addRoomToLevel(createMazeRoom(), new LevelPos(1, 0, 1));


        level.addRoomToLevel(createBasicRoom(3, 3, 3), new LevelPos(2, 0, 6));



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


        // Return the level
        return level;
    }

    public Maze createMaze(int size, float cellsize, MazeType mazeType, Vector3 position) {
        Maze maze = new Maze(size, cellsize, mazeType, position);
        world.addObject(maze);
        return maze;
    }

    public Cylinder createColumn(Vector3 position, float width, float height, float depth) {
        Shape cylinder = new Cylinder("glyphs.jpg", position, width, height, depth);
        return (Cylinder) cylinder;
    }

    public Room createBasicRoom(int roomXSize, int roomYSize, int roomZSize) {
        Room room = new BasicRoom(roomXSize, roomYSize, roomZSize);
        world.addObject(room);
        return room;
    }

    public Room createMazeRoom() {
        Room room = new MazeRoom();
        world.addObject(room);
        return room;
    }

    public Floor createBasicFloor(Vector3 position, float actualXSize, float actualYSize, float actualZSize) {
        Object basicFloor = new BasicFloor(position, actualXSize, actualYSize, actualZSize);
        world.addObject(basicFloor);

        return (Floor) basicFloor;
    }

    public Roof createBasicRoof(Vector3 position, float actualXSize, float actualYSize, float actualZSize) {
        Object basicRoof = new BasicRoof(position, actualXSize, actualYSize, actualZSize);
        world.addObject(basicRoof);

        return (Roof) basicRoof;
    }

    public MazeWall createWall(Coord coord, Coord coord1, Maze maze) {
        MazeWall mazeWall = new MazeWall(coord, coord1, maze);
        world.addObject(mazeWall);

        return mazeWall;
    }

    public Box createWallBox(Vector3 newPos, float v, float tallness, float thickness) {
        return null;
    }
}
