package com.tgra.client.game.walls;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.tgra.client.game.World;
import com.tgra.client.game.maze.Coord;
import com.tgra.client.game.maze.Maze;
import com.tgra.client.game.shapes.Box;

/**
 * <h1>Wall</h1>
 * <h2>is.ru.tgra.maze</h2>
 * <p></p>
 * Created on 4.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class MazeWall extends AbstractWall {

    private Coord sCoord;
    private Coord eCoord;
    private Vector3 startPoint;
    private Vector3 endPoint;
    private Box box;
    private Maze maze;
    private final float thickness = 0.5f;


    public MazeWall(Coord sCoord, Coord eCoord, Maze maze) {
        this.sCoord = sCoord;
        this.eCoord = eCoord;
        this.maze = maze;
        build();
    }

    public void setBox(Box box) {
        this.box = box;
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    protected void build() {
        Vector3 blc = maze.getBottomLeftCorner();
        float cellsize = maze.getCellsize();

        this.startPoint = new Vector3(blc.x + cellsize * sCoord.x, 0f, blc.z + cellsize * sCoord.y);
        this.endPoint = new Vector3(blc.x + cellsize * eCoord.x, 0f, blc.z + cellsize * eCoord.y);
        this.length = startPoint.dst(endPoint);
        Vector3 newPos;
        if (sCoord.x == eCoord.x) {
            newPos = new Vector3(startPoint.x, startPoint.y, startPoint.z + length / 2);
            rotation = -0f;
            box = new Box("wall.jpg", newPos, thickness, 4, length);
        }
        if (sCoord.y == eCoord.y) {
            newPos = new Vector3(startPoint.x + length / 2, startPoint.y, startPoint.z);
            rotation = 0f;
            box = new Box("wall.jpg", newPos, length, 4, thickness);
        }
        box.build(World.getInstance().getModelBuilder(), rotation);
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        this.box.render(modelBatch, environment);
    }

    @Override
    public Vector3 getPosition() {
        return position;
    }

    @Override
    public boolean isHit(BoundingBox player) {
        return box.isHit(player);
    }

    /*
    public boolean checkIfOnLine(Vector3 a, Vector3 b, Vector3 pHit) {
        return (a.lengthTo(pHit) + b.lengthTo(pHit)) == a.lengthTo(b);
    }
    */

    /*
    @Override
    public void collision(ObjectReference or, float deltaTime) {
        this.box.collision(or, deltaTime);
    }
    */
}
