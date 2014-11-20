package com.tgra.client.game.maze;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.tgra.client.game.GameFactory;
import com.tgra.client.game.object.AbstractObject;
import com.tgra.client.game.walls.MazeWall;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <h1>Maze</h1>
 * <h2>is.ru.tgra.maze</h2>
 * <p></p>
 * Created on 4.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class Maze extends AbstractObject {

    private int size;
    private float cellsize;
    private Vector3 position;
    private float axis;
    private Vector3 bottomLeftCorner;
    private List<MazeWall> mazeWalls = new ArrayList<MazeWall>();
    private GameFactory gameFactory = GameFactory.getInstance();
    private MazeGenerator mazeGenerator;

    public Maze() {
    }

    public Maze(int size, float cellsize, MazeType mazeType, Vector3 position) {
        if (size < 0)
            size = 0;
        if (cellsize < 0)
            cellsize = 0;
        this.position = position;
        this.size = size;
        this.cellsize = cellsize;
        this.axis = size * cellsize;
        this.bottomLeftCorner = new Vector3(position.x - axis / 2, position.y, position.z - axis / 2);
        if (mazeType == MazeType.PRIM)
            mazeGenerator = new PrimMazeGenerator(size, size);
        if (mazeType == MazeType.HUNT)
            mazeGenerator = new HuntAndKillMazeGenerator(size, size);
        if (mazeType == MazeType.RECURSIVE)
            mazeGenerator = new RecursiveBacktrackerMazeGenerator(size, size);

        makeMaze();
    }

    public void makeMaze() {
        //createFloor();
//        createRoof();
        //createBorderWalls();
        //createWalls();
        mazeTester();
    }

    public void mazeTester() {
        mazeGenerator.generate();
        int height = mazeGenerator.getHeight();
        int width = mazeGenerator.getWidth();
        boolean[] horizWalls = mazeGenerator.getHorizWalls();
        boolean[] vertWalls = mazeGenerator.getVertWalls();
        MazeWall mazeWall;
        mazeGenerator.print(System.out);
        System.out.println(mazeGenerator.toString());
        for (int y = 0; y < height; y++) {

            int rowBase = y * width;
            for (int x = 0; x < width; x++) {
                if (horizWalls[rowBase + x] && (y != 0)) {
                    mazeWall = gameFactory.createWall(new Coord(x, y), new Coord(x + 1, y), this);
                    mazeWalls.add(mazeWall);
                }
            }

            rowBase = y * (width + 1);
            for (int x = 0; x < width; x++) {
                if (vertWalls[rowBase + x] && (x != 0)) {
                    mazeWall = gameFactory.createWall(new Coord(x, y), new Coord(x, y + 1), this);
                    mazeWalls.add(mazeWall);
                }
            }
        }
    }

    public Vector3 getCellPosition(int x, int y) {
        Vector3 bl = new Vector3();
        bl.x = bottomLeftCorner.x + cellsize * x + cellsize / 2;
        bl.y = bottomLeftCorner.y;
        bl.z = bottomLeftCorner.z + cellsize * y + cellsize / 2;
        return bl;
    }

    public Vector3 getCellPosition() {
        Vector3 bl = new Vector3();
        Random rand = new Random();
        int randomNum;
        randomNum = rand.nextInt(size);
        bl.x = bottomLeftCorner.x + cellsize * randomNum + cellsize / 2;
        bl.y = bottomLeftCorner.y + 0.5f;
        randomNum = rand.nextInt(size);
        bl.z = bottomLeftCorner.z + cellsize * randomNum + cellsize / 2;
        return bl;
    }

    private void createBorderWalls() {
        MazeWall l = gameFactory.createWall(new Coord(0, 0), new Coord(0, size), this);
        MazeWall r = gameFactory.createWall(new Coord(size, 0), new Coord(size, size), this);
        MazeWall b = gameFactory.createWall(new Coord(0, 0), new Coord(size, 0), this);
        MazeWall t = gameFactory.createWall(new Coord(0, size), new Coord(size, size), this);
        mazeWalls.add(l);
        mazeWalls.add(r);
        mazeWalls.add(b);
        mazeWalls.add(t);
    }

    private void createWalls() {
        MazeWall mazeWall1 = gameFactory.createWall(new Coord(2, 2), new Coord(8, 2), this);
        MazeWall mazeWall2 = gameFactory.createWall(new Coord(2, 4), new Coord(2, 8), this);
        MazeWall mazeWall3 = gameFactory.createWall(new Coord(2, 4), new Coord(8, 4), this);
        MazeWall mazeWall4 = gameFactory.createWall(new Coord(4, 6), new Coord(6, 6), this);
        MazeWall mazeWall5 = gameFactory.createWall(new Coord(8, 2), new Coord(8, 8), this);
        MazeWall mazeWall6 = gameFactory.createWall(new Coord(4, 6), new Coord(4, 8), this);
        MazeWall mazeWall7 = gameFactory.createWall(new Coord(4, 8), new Coord(8, 8), this);
        //Wall wall8 = new Wall(new Coordinate(0, size), new Coordinate(size, size), this);
        mazeWalls.add(mazeWall1);
        mazeWalls.add(mazeWall2);
        mazeWalls.add(mazeWall3);
        mazeWalls.add(mazeWall4);
        mazeWalls.add(mazeWall5);
        mazeWalls.add(mazeWall6);
        mazeWalls.add(mazeWall7);
        //walls.add(wall8);
    }

    public int getSize() {
        return size;
    }

    public float getCellsize() {
        return cellsize;
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        for (MazeWall w : mazeWalls) {
            w.render(modelBatch, environment);
        }
    }

    @Override
    public boolean isHit(BoundingBox player) {
        return false;
    }

    public Vector3 getBottomLeftCorner() {
        return bottomLeftCorner;
    }
}
