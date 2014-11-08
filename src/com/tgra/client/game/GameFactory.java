package com.tgra.client.game;

import com.tgra.client.game.rooms.Room;
import com.tgra.client.game.shapes.Shape;
import com.tgra.client.game.walls.Wall;
import com.tgra.client.graphics.Floor;

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

    private GameFactory() {
    }

    public static Shape createBox(/* Texture */) {

        return null;
    }

    public static Wall createTiledWall() {
        // Get tile texture

        // Create the tiled wall

        // Return the wall object
        return null;
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

}
