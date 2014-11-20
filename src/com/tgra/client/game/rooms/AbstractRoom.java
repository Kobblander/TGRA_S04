package com.tgra.client.game.rooms;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.tgra.client.game.GameFactory;
import com.tgra.client.game.World;
import com.tgra.client.game.column.Column;
import com.tgra.client.game.doors.Door;
import com.tgra.client.game.floors.Floor;
import com.tgra.client.game.object.AbstractObject;
import com.tgra.client.game.object.Object;
import com.tgra.client.game.roofs.Roof;
import com.tgra.client.game.shapes.Cylinder;
import com.tgra.client.game.walls.Wall;
import javafx.geometry.Side;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>AbstractRoom</h1>
 * <h2>com.tgra.client.game.rooms</h2>
 * <p>This class contains all common variables and functions between rooms.</p>
 * Created on 8.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public abstract class AbstractRoom extends AbstractObject implements Room {

    protected GameFactory gameFactory = GameFactory.getInstance();
    protected ModelBuilder modelBuilder = World.getInstance().getModelBuilder();
    protected RoomData roomData = new RoomData();

    // Wall thickness
    protected final float thickness = 1;

    // Roof / Floor thickness
    protected final float floorThickness = 0.5f;
    protected final float roofThickness = 0.5f;

    // Unit size of a room.
    // A doorway would take up a single unit.
    protected final float unitSize = 3f;

    // Number of units per roomSize
    // This needs to be an odd number.
    // A room of size 5 would have 5x5 units
    // Each unit is of size 200.0f then the actualXSize
    // would be for example 5*5*200.0f
    protected final int unitCount = 3;

    protected Vector3 BL = new Vector3();
    protected Vector3 BR = new Vector3();
    protected Vector3 TR = new Vector3();
    protected Vector3 TL = new Vector3();

    // Currently rooms are symmetric
    // A room of size 1 fits one cell in a level.
    protected int roomXSize;
    protected int roomYSize;
    protected int roomZSize;


    // This is will be multiplied by the unitSize.
    protected int xUnits;
    protected int yUnits;
    protected int zUnits;

    // The actual size of the room is actualXSize * unitSize.
    protected float actualXSize;
    protected float actualYSize;
    protected float actualZSize;

    protected List<Wall> outerWalls = new ArrayList<Wall>();
    protected Wall topWall;
    protected Wall bottomWall;
    protected Wall leftWall;
    protected Wall rightWall;

    protected List<Cylinder> columns = new ArrayList<Cylinder>();

    protected Floor floor;

    protected Roof roof;

    // Relative positions of the walls
    protected float xPos;
    protected float yPos;
    protected float zPos;

    protected ArrayList<Object> doodads = new ArrayList<Object>();

    protected Door door;
    protected Vector3 topWallPos;
    protected Vector3 leftWallPos;
    protected Vector3 rightWallPos;
    protected Vector3 bottomWallPos;

    protected Vector3 columnAPos = new Vector3();
    protected Vector3 columnBPos = new Vector3();
    protected Vector3 columnCPos = new Vector3();
    protected Column c1;
    protected Column c2;
    protected Column c3;

    protected abstract void initDoodads();

    protected void initializeRoom(int roomXSize, int roomYSize, int roomZSize) {
        this.roomXSize = roomXSize;
        this.roomYSize = roomYSize;
        this.roomZSize = roomZSize;

        this.xUnits = roomXSize * unitCount;
        this.yUnits = roomYSize * unitCount;
        this.zUnits = roomZSize * unitCount;

        // Calculate the actual size of the room in float
        if (roomXSize > 1) {
            this.actualXSize = this.xUnits * unitSize + thickness * roomXSize - thickness;
        } else {
            this.actualXSize = this.xUnits * unitSize;
        }
        this.actualYSize = (this.yUnits * unitSize) / 2;
        if (roomZSize > 1) {
            this.actualZSize = this.zUnits * unitSize + thickness * roomZSize - thickness;
        } else {
            this.actualZSize = this.zUnits * unitSize;
        }

        // Set up the data other objects will use (levels)
        // add thickness of walls so they wont overlap
        roomData.setActualXSize(actualXSize);
        roomData.setActualYSize(actualYSize);
        roomData.setActualZSize(actualZSize);

        roomData.setxSize(roomXSize);
        roomData.setySize(roomYSize);
        roomData.setzSize(roomZSize);
        roomData.setCellSize(unitCount * unitSize);
        roomData.setWallThickness(thickness);
        roomData.setUnitCount(unitCount);
        roomData.setUnitSize(unitSize);

    }

    @Override
    public void setWall(Wall wall, Side side) {
        if (wall == null) {
            return;
        }
        switch (side) {
            case TOP:
                wall.initWall(
                        topWallPos,
                        -90.0f,
                        actualXSize,
                        actualYSize,
                        thickness
                );
                this.topWall = wall;
                break;
            case BOTTOM:
                wall.initWall(
                        bottomWallPos,
                        -90.0f,
                        actualXSize,
                        actualYSize,
                        thickness
                );
                this.bottomWall = wall;
                break;
            case RIGHT:
                wall.initWall(
                        rightWallPos,
                        0.0f,
                        actualZSize,
                        actualYSize,
                        thickness
                );
                this.rightWall = wall;
                break;
            case LEFT:
                wall.initWall(
                        leftWallPos,
                        0.0f,
                        actualZSize,
                        actualYSize,
                        thickness
                );
                this.leftWall = wall;
                break;
        }
        outerWalls.add(wall);
    }

    @Override
    public abstract void render(ModelBatch modelBatch, Environment environment);

    @Override
    public void setDoorColumns(Side side) {
        switch (side) {
            case TOP:
                columnAPos = new Vector3(this.position.x + unitSize / 2, this.position.y + unitSize / 2, this.position.z - actualZSize / 2 - thickness / 2);
                columnBPos = new Vector3(this.position.x - unitSize / 2, this.position.y + unitSize / 2, this.position.z - actualZSize / 2 - thickness / 2);
                columnCPos = new Vector3(this.position.x, this.position.y + unitSize, this.position.z - actualZSize / 2 - thickness / 2);
                c1 = gameFactory.createColumn(columnAPos, thickness, unitSize, thickness);
                c2 = gameFactory.createColumn(columnBPos, thickness, unitSize, thickness);
                c3 = gameFactory.createColumn(columnCPos, thickness, unitSize, thickness);
                c3.setRotation(-90f, Vector3.Z);
                break;
            case BOTTOM:
                columnAPos = new Vector3(this.position.x + unitSize / 2, this.position.y + unitSize / 2, this.position.z + actualZSize / 2 + thickness / 2);
                columnBPos = new Vector3(this.position.x - unitSize / 2, this.position.y + unitSize / 2, this.position.z + actualZSize / 2 + thickness / 2);
                columnCPos = new Vector3(this.position.x, this.position.y + unitSize, this.position.z + actualZSize / 2 + thickness / 2);
                c1 = gameFactory.createColumn(columnAPos, thickness, unitSize, thickness);
                c2 = gameFactory.createColumn(columnBPos, thickness, unitSize, thickness);
                c3 = gameFactory.createColumn(columnCPos, thickness, unitSize, thickness);
                c3.setRotation(-90f, Vector3.Z);
                break;
            case RIGHT:
                columnAPos = new Vector3(this.position.x + actualXSize / 2 + thickness / 2, this.position.y + unitSize / 2, this.position.z + unitSize / 2);
                columnBPos = new Vector3(this.position.x + actualXSize / 2 + thickness / 2, this.position.y + unitSize / 2, this.position.z - unitSize / 2);
                columnCPos = new Vector3(this.position.x + actualXSize / 2 + thickness / 2, this.position.y + unitSize, this.position.z);
                c1 = gameFactory.createColumn(columnAPos, thickness, unitSize, thickness);
                c2 = gameFactory.createColumn(columnBPos, thickness, unitSize, thickness);
                c3 = gameFactory.createColumn(columnCPos, thickness, unitSize, thickness);
                c3.setRotation(-90f, Vector3.X);
                break;
            case LEFT:
                columnAPos = new Vector3(this.position.x - actualXSize / 2 - thickness / 2, this.position.y + unitSize / 2, this.position.z + unitSize / 2);
                columnBPos = new Vector3(this.position.x - actualXSize / 2 - thickness / 2, this.position.y + unitSize / 2, this.position.z - unitSize / 2);
                columnCPos = new Vector3(this.position.x - actualXSize / 2 - thickness / 2, this.position.y + unitSize, this.position.z);
                c1 = gameFactory.createColumn(columnAPos, thickness, unitSize, thickness);
                c2 = gameFactory.createColumn(columnBPos, thickness, unitSize, thickness);
                c3 = gameFactory.createColumn(columnCPos, thickness, unitSize, thickness);
                c3.setRotation(-90f, Vector3.X);
                break;
        }

        doodads.add(c1);
        doodads.add(c2);
        doodads.add(c3);
    }

    @Override
    public void setDoor(Door door, Side side) {
        if (door == null) {
            System.out.println("Room; setDoor; door is null.");
            return;
        }

        switch (side) {
            case TOP:
                door.initDoor(
                        new Vector3(this.position.x, this.position.y + unitSize / 2, this.position.z - actualZSize / 2 - thickness / 2),
                        Side.TOP,
                        unitSize,
                        unitSize,
                        0.1f);
                break;
            case BOTTOM:
                door.initDoor(
                        new Vector3(this.position.x, this.position.y + unitSize / 2, this.position.z + actualZSize / 2 + thickness / 2),
                        Side.BOTTOM,
                        unitSize,
                        unitSize,
                        0.1f);
                break;
            case LEFT:
                door.initDoor(
                        new Vector3(this.position.x - actualXSize / 2 - thickness / 2, this.position.y + unitSize / 2, this.position.z),
                        Side.LEFT,
                        unitSize,
                        unitSize,
                        0.1f);
                break;
            case RIGHT:
                door.initDoor(
                        new Vector3(this.position.x + actualXSize / 2 + thickness / 2, this.position.y + unitSize / 2, this.position.z),
                        Side.RIGHT,
                        unitSize,
                        unitSize,
                        0.1f);
                break;
        }

        this.door = door;
    }

    /**
     * It is optional to implement this update function.
     * Only do so if it is needed to update the rooms variables.
     * @param deltaTime Current deltaTime.
     */
    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void setPosition(Vector3 position) {
        this.position = position;

        // Set relative wall positions.
        xPos = this.position.x + this.actualXSize / 2 + thickness / 2;
        yPos = this.position.y + this.actualYSize / 2;
        zPos = this.position.z + this.actualZSize / 2 + thickness / 2;

        topWallPos = new Vector3(zPos - actualZSize - thickness+0.1f, yPos, -position.x);
        bottomWallPos = new Vector3(zPos-0.1f, yPos, -position.x);
        rightWallPos = new Vector3(xPos-0.1f, yPos,  position.z);
        leftWallPos = new Vector3(xPos  - actualXSize - thickness+0.1f, yPos,  position.z);

        calculateEdgePoints();
        initDoodads();
    }

    protected void calculateEdgePoints() {
        BR.x = position.x + (actualXSize / 2);
        BR.y = position.y + (actualYSize / 2);
        BR.z = position.z + (actualZSize / 2);

        BL.x = position.x - (actualXSize / 2);
        BL.y = position.y + (actualYSize / 2);
        BL.z = position.z + (actualZSize / 2);

        TR.x = position.x + (actualXSize / 2);
        TR.y = position.y + (actualYSize / 2);
        TR.z = position.z - (actualZSize / 2);

        TL.x = position.x - (actualXSize / 2);
        TL.y = position.y + (actualYSize / 2);
        TL.z = position.z - (actualZSize / 2);
    }

    @Override
    public RoomData getRoomData() {
        return roomData;
    }

    @Override
    public boolean isHit(BoundingBox player) {
        return topWall.isHit(player) || bottomWall.isHit(player) ||
               leftWall.isHit(player) || rightWall.isHit(player);
    }
}
