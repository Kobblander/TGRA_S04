package com.tgra.client.game.rooms;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import com.tgra.client.game.object.Object;
import com.tgra.client.game.shapes.Cylinder;
import com.tgra.client.game.walls.Wall;

/**
 * <h1>BasicRoom</h1>
 * <h2>com.tgra.client.game.rooms</h2>
 * <p></p>
 * Created on 8.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class BasicRoom extends AbstractRoom {

    public BasicRoom(int roomXSize, int roomYSize, int roomZSize) {
        if (roomXSize % 2 == 0) {
            System.out.println("Room sizes must be odd numbers.");
            roomXSize++;
        } else if (roomYSize % 2 == 0) {
            System.out.println("Room sizes must be odd numbers.");
            roomYSize++;
        }
        initializeRoom(roomXSize, roomYSize, roomZSize);
    }


    @Override
    protected void initDoodads() {

        // Create all the rooms objects.
        Cylinder c1 = gameFactory.createColumn(TL, 1f, actualYSize, 1f);
        c1.build(modelBuilder);
        Cylinder c2 = gameFactory.createColumn(TR, 1f, actualYSize, 1f);
        c2.build(modelBuilder);
        Cylinder c3 = gameFactory.createColumn(BL, 1f, actualYSize, 1f);
        c3.build(modelBuilder);
        Cylinder c4 = gameFactory.createColumn(BR, 1f, actualYSize, 1f);
        c4.build(modelBuilder);
        columns.add(c1);
        columns.add(c2);
        columns.add(c3);
        columns.add(c4);

        floor = gameFactory.createBasicFloor(position, actualXSize + thickness + 0.1f, floorThickness, actualZSize + thickness + 0.1f);

        Vector3 roofPos = new Vector3(position.x, actualYSize - 1.75f, position.z);
        roof = gameFactory.createBasicRoof(roofPos, actualXSize, roofThickness, actualZSize);
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        /*
        for (Wall w : outerWalls) {
            Object wo = (Object) w;
            wo.render(modelBatch, environment);
        }
        */

        if (topWall != null) {
            ((Object) topWall).render(modelBatch, environment);
        }
        if (bottomWall != null) {
            ((Object) bottomWall).render(modelBatch, environment);
        }
        if (rightWall != null) {
            ((Object) rightWall).render(modelBatch, environment);
        }
        if (leftWall != null) {
            ((Object) leftWall).render(modelBatch, environment);
        }

        for (Cylinder c : columns) {
            c.render(modelBatch, environment);
        }

        Object f = (Object) floor;
        f.render(modelBatch, environment);

        Object r = (Object) roof;
        r.render(modelBatch, environment);

    }


}
