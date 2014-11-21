package com.tgra.client.game.walls;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.tgra.client.game.World;
import com.tgra.client.game.shapes.Box;
import com.tgra.client.game.shapes.Cylinder;

/**
 * <h1>DoorWall</h1>
 * <h2>com.tgra.client.game.walls</h2>
 * <p></p>
 * Created on 15.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class DoorWall extends AbstractWall {

    protected Box boxA;
    protected Box doorwayBox;
    protected Box boxB;
    protected Cylinder columnA;
    protected Cylinder columnB;

    public DoorWall() {
    }


    @Override
    protected void build() {
        Vector3 boxAPos = calculateBoxAPos();
        Vector3 boxBPos = calculateBoxBPos();
        float boxALength;
        float boxBLength;
        if (rotation == 90 || rotation == -90) {
            boxALength = (length / 2) - (roomData.getUnitSize() / 2);
            boxBLength = (length / 2) - (roomData.getUnitSize() / 2);
        } else {
            boxALength = (length / 2) - (roomData.getUnitSize() / 2);
            boxBLength = (length / 2) - (roomData.getUnitSize() / 2);
        }

        boxA = new Box("wall.jpg", boxAPos, thickness, height, boxALength);
        boxA.build(World.getInstance().getModelBuilder(), rotation);

        boxB = new Box("wall.jpg", boxBPos, thickness, height, boxBLength);
        boxB.build(World.getInstance().getModelBuilder(), rotation);

        Vector3 doorPos = new Vector3(this.position);
        doorPos.y += roomData.getUnitSize()-0.085f;
        doorwayBox = new Box("wall.jpg", doorPos, thickness, height, roomData.getUnitSize());
        doorwayBox.build(World.getInstance().getModelBuilder(), rotation);
    }

    private Vector3 calculateBoxBPos() {
        Vector3 newBPos = new Vector3(this.position);
        if (rotation == 90f || rotation == -90f) {
            newBPos.x += ((roomData.getActualXSize()/2) / 2) + (roomData.getUnitSize()/2)/2;
        } else {
            newBPos.z += ((roomData.getActualZSize()/2) / 2) + (roomData.getUnitSize()/2)/2;
        }

        return newBPos;
    }

    private Vector3 calculateBoxAPos() {
        Vector3 newAPos = new Vector3(this.position);
        if (rotation == 90f || rotation == -90f) {
            newAPos.x -= ((roomData.getActualXSize()/2) / 2) + (roomData.getUnitSize()/2)/2;
        } else {
            newAPos.z -= ((roomData.getActualZSize()/2) / 2) + (roomData.getUnitSize()/2)/2;
        }

        return newAPos;
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        boxA.render(modelBatch, environment);
        boxB.render(modelBatch, environment);
        doorwayBox.render(modelBatch, environment);
    }

    @Override
    public boolean isHit(BoundingBox player) {
        return boxA.isHit(player) || boxB.isHit(player);
    }
}
