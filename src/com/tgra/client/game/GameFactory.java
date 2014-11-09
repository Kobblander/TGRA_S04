package com.tgra.client.game;

import com.badlogic.gdx.math.Vector3;
import com.tgra.client.game.floors.BasicFloor;
import com.tgra.client.game.object.Object;
import com.tgra.client.game.rooms.Room;
import com.tgra.client.game.shapes.Box;
import com.tgra.client.game.shapes.Shape;
import com.tgra.client.game.walls.BasicWall;
import com.tgra.client.game.walls.Wall;
import com.tgra.client.game.floors.Floor;

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

    private GameFactory() {
    }

    public static Shape createWoodenBox(Vector3 position, float xSize, float ySize, float zSize) {
        Box box = new Box("wood.jpg", position, xSize, ySize, zSize);

        return null;
    }

    public static Wall createTiledWall() {
        // Get tile texture

        // Create the tiled wall

        // Return the wall object
        return null;
    }

    public static Wall createBasicWall(Vector3 position, float xSize, float ySize, float zSize) {
        // Get basic wall texture

        // Create the wall
        Object basicWall = new BasicWall(position, xSize, ySize, zSize);
        world.addObject(basicWall);

        // Return the wall object
        return (Wall) basicWall;
    }

    public static Floor createDirtFloor() {
        // Get the dirt floor texture

        // Create the floor with said texture

        //
        return null;
    }

    public static Room createEmptyRoom() {
        // Generic empty room

        //

        // Return said room
        return null;
    }

    public static Floor createBasicFloor(Vector3 position, float actualXSize, float actualYSize, float actualZSize) {

        Object basicFloor = new BasicFloor(position, actualXSize, actualYSize, actualZSize);
        world.addObject(basicFloor);
        return (Floor) basicFloor;
    }
}
