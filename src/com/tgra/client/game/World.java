package com.tgra.client.game;

import com.tgra.client.game.rooms.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>World</h1>
 * <h2>com.tgra.client.game</h2>
 * <p></p>
 * Created on 8.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class World {

    private static World instance = new World();

    public static World getInstance() { return instance; }

    private World() {
    }

    private static List<Room> roomList = new ArrayList<Room>();

    private static List<Object> objectList = new ArrayList<Object>();

    public static List<Room> getRoomList() {
        return roomList;
    }

    public static List<Object> getObjectList() {
        return objectList;
    }

}
