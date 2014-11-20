package com.tgra.client.game.levels;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.tgra.client.game.GameFactory;
import com.tgra.client.game.mechanisms.DoorLockMechanism;
import com.tgra.client.game.object.AbstractObject;
import com.tgra.client.game.rooms.Room;
import javafx.geometry.Side;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>AbstractLevel</h1>
 * <h2>com.tgra.client.game.levels</h2>
 * <p></p>
 * Created on 8.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public abstract class AbstractLevel extends AbstractObject implements Level {

    protected LevelGrid levelGrid;

    protected GameFactory gameFactory = GameFactory.getInstance();

    protected List<DoorLockMechanism> doorLockMechanisms = new ArrayList<DoorLockMechanism>();

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public Room addRoomToLevel(Room room, LevelPos levelPos) {
        int roomXSize = room.getRoomData().getxSize();
        int roomYSize = room.getRoomData().getySize();
        int roomZSize = room.getRoomData().getzSize();


        if (levelGrid.isPosOccupied(room, levelPos)) {
            return null;
        }

        calculateRealPosition(room, levelPos);
        for (int x = levelPos.x; x < levelPos.x + roomXSize; x++) {
            for (int y = levelPos.y; y < levelPos.y + roomYSize; y++) {
                for (int z = levelPos.z; z < levelPos.z + roomZSize; z++) {
                    levelGrid.addRoomAtPos(room, new LevelPos(x, y, z));
                }
            }
        }
        return room;
    }

    /**
     * Creates a doorway in the middle of the wall in the direction of the other position.
     * Make sure that either the x or y coords are the same. (They must be next to each other.)
     * @param levelPosA Position roomA resides in.
     * @param levelPosB Position roomB resides in.
     */
    @Override
    public void addDoorway(LevelPos levelPosA, LevelPos levelPosB) {
        if (!levelGrid.isPosOccupied(levelPosA) || !levelGrid.isPosOccupied(levelPosB)) {
            System.out.println("There are no valid rooms to put the doorway between.");
            return;
        }
        Room roomA = levelGrid.getRoomAtPos(levelPosA);
        Room roomB = levelGrid.getRoomAtPos(levelPosB);

        // LevelPoses must be next to eachother.
        if (levelPosA.y == levelPosB.y) {
            // Check if either left, top, right or bottom of each other
            if (levelPosA.x < levelPosB.x) {
                // Check which room is taller
                // TODO: Here I can put columns and doors :)
                roomA.setWall(gameFactory.createDoorWall(roomA), Side.RIGHT);
                roomB.setWall(gameFactory.createDoorWall(roomB), Side.LEFT);
                roomB.setDoorColumns(Side.LEFT);
            }
            if (levelPosA.x > levelPosB.x) {
                roomA.setWall(gameFactory.createDoorWall(roomA), Side.LEFT);
                roomB.setWall(gameFactory.createDoorWall(roomB), Side.RIGHT);
                roomB.setDoorColumns(Side.RIGHT);
            }
        }
        if (levelPosA.x == levelPosB.x) {
            if (levelPosA.z < levelPosB.z) {
                roomA.setWall(gameFactory.createDoorWall(roomA), Side.BOTTOM);
                roomB.setWall(gameFactory.createDoorWall(roomB), Side.TOP);
                roomB.setDoorColumns(Side.TOP);
            }
            if (levelPosA.z > levelPosB.z) {
                roomA.setWall(gameFactory.createDoorWall(roomA), Side.TOP);
                roomB.setWall(gameFactory.createDoorWall(roomB), Side.BOTTOM);
                roomB.setDoorColumns(Side.BOTTOM);
            }
        }
    }

    @Override
    public abstract void render(ModelBatch modelBatch, Environment environment);

    /**
     * Calculates the real position of the room relative to its
     * position in the levelGrid
     */
    protected void calculateRealPosition(Room room, LevelPos levelPos) {
        Vector3 roomPosition = new Vector3();
        float actualXSize = room.getRoomData().getActualXSize();
        float actualYSize = room.getRoomData().getActualYSize();
        float actualZSize = room.getRoomData().getActualZSize();
        float posX = levelPos.x;
        float posY = levelPos.y;
        float posZ = levelPos.z;
        float cellSize = room.getRoomData().getCellSize() + room.getRoomData().getWallThickness();

        roomPosition.x = this.position.x + posX * cellSize + actualXSize/2;
        roomPosition.y = this.position.y + posY * actualYSize;
        roomPosition.z = this.position.z + posZ * cellSize + actualZSize/2;

        room.setPosition(roomPosition);
        room.setWall(gameFactory.createBasicWall(), Side.BOTTOM);
        room.setWall(gameFactory.createBasicWall(), Side.LEFT);
        room.setWall(gameFactory.createBasicWall(), Side.TOP);
        room.setWall(gameFactory.createBasicWall(), Side.RIGHT);
    }

    @Override
    public boolean isHit(BoundingBox player) { return false; }
}
