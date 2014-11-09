package com.tgra.client.game;

import com.tgra.client.game.object.Object;
import com.tgra.client.game.rooms.Room;
import com.tgra.client.game.shapes.Shape;

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

    private static List<Room> roomList = new ArrayList<Room>();

    private static List<Object> objectList = new ArrayList<Object>();

    private static List<Shape> shapeList = new ArrayList<Shape>();

    private World() {
    }

    public static List<Room> getRoomList() {
        return roomList;
    }

    public static List<Object> getObjectList() {
        return objectList;
    }

    public static void addObject(Object object) {
        objectList.add(object);
    }

    public static void addShape(Shape shape) {
        shapeList.add(shape);
    }

    public static List<Shape> getShapeList() {
        return shapeList;
    }

}
