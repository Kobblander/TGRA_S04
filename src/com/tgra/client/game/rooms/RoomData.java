package com.tgra.client.game.rooms;

/**
 * <h1>RoomData</h1>
 * <h2>com.tgra.client.game.rooms</h2>
 * <p></p>
 * Created on 16.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class RoomData {

    protected int xSize;
    protected int ySize;
    protected int zSize;
    protected float actualXSize;
    protected float actualYSize;
    protected float actualZSize;
    protected float cellSize;
    protected float wallThickness;
    protected int unitCount;
    protected float unitSize;

    public RoomData() {
    }

    public int getUnitCount() {
        return unitCount;
    }

    public void setUnitCount(int unitCount) {
        this.unitCount = unitCount;
    }

    public float getUnitSize() {
        return unitSize;
    }

    public void setUnitSize(float unitSize) {
        this.unitSize = unitSize;
    }

    public float getActualXSize() {
        return actualXSize;
    }

    public void setActualXSize(float actualXSize) {
        this.actualXSize = actualXSize;
    }

    public float getActualYSize() {
        return actualYSize;
    }

    public void setActualYSize(float actualYSize) {
        this.actualYSize = actualYSize;
    }

    public float getActualZSize() {
        return actualZSize;
    }

    public void setActualZSize(float actualZSize) {
        this.actualZSize = actualZSize;
    }

    public int getxSize() {
        return xSize;
    }

    public void setxSize(int xSize) {
        this.xSize = xSize;
    }

    public int getySize() {
        return ySize;
    }

    public void setySize(int ySize) {
        this.ySize = ySize;
    }

    public int getzSize() {
        return zSize;
    }

    public void setzSize(int zSize) {
        this.zSize = zSize;
    }

    public float getCellSize() {
        return cellSize;
    }

    public void setCellSize(float cellSize) {
        this.cellSize = cellSize;
    }

    public float getWallThickness() {
        return wallThickness;
    }

    public void setWallThickness(float wallThickness) {
        this.wallThickness = wallThickness;
    }
}
