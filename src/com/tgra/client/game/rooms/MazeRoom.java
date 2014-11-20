package com.tgra.client.game.rooms;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import com.tgra.client.game.column.Column;
import com.tgra.client.game.maze.Maze;
import com.tgra.client.game.maze.MazeType;
import com.tgra.client.game.shapes.Cylinder;
import com.tgra.client.game.object.Object;

/**
 * <h1>MazeRoom</h1>
 * <h2>com.tgra.client.game.rooms</h2>
 * <p></p>
 * Created on 18.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class MazeRoom extends AbstractRoom {

    private Maze maze;
    private final int roomSize = 5;

    public MazeRoom() {
        initializeRoom(roomSize, 1, roomSize);
    }

    @Override
    protected void initDoodads() {

        maze = gameFactory.createMaze(15, unitSize + 0.3f, MazeType.PRIM, this.position);

        // Create all the rooms objects.
        Column c1 = gameFactory.createColumn(TL, 1f, actualYSize, 1f);
        Column c2 = gameFactory.createColumn(TR, 1f, actualYSize, 1f);
        Column c3 = gameFactory.createColumn(BL, 1f, actualYSize, 1f);
        Column c4 = gameFactory.createColumn(BR, 1f, actualYSize, 1f);
        doodads.add(c1);
        doodads.add(c2);
        doodads.add(c3);
        doodads.add(c4);

        floor = gameFactory.createBasicFloor(position, actualXSize + thickness + 0.1f, floorThickness, actualZSize + thickness + 0.1f);

        Vector3 roofPos = new Vector3(position.x, actualYSize - 1.75f, position.z);
        roof = gameFactory.createBasicRoof(roofPos, actualXSize, roofThickness, actualZSize);

    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {

        maze.render(modelBatch, environment);

        for (Object o : doodads) {
            o.render(modelBatch, environment);
        }

        topWall.render(modelBatch, environment);
        bottomWall.render(modelBatch, environment);
        leftWall.render(modelBatch, environment);
        rightWall.render(modelBatch, environment);

        Object f = (Object) floor;
        f.render(modelBatch, environment);

        Object r = (Object) roof;
        r.render(modelBatch, environment);

    }
}
