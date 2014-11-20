package com.tgra.client.game.shapes;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.tgra.client.utility.Texture;
import com.badlogic.gdx.math.collision.BoundingBox;

/**
 * <h1>AbstractShape</h1>
 * <h2>com.tgra.client.game.shapes</h2>
 * <p></p>
 * Created on 18.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public abstract class AbstractShape implements Shape {

    protected ModelInstance shapeInstance;
    protected Vector3 center;
    protected Texture texture;

    @Override
    public Vector3 getPosition() {
        return center;
    }

    @Override
    public void setRotation(float degrees, Vector3 axis) {
        shapeInstance.transform.rotate(axis, degrees);
        shapeInstance.calculateTransforms();
    }

    @Override
    public void translate(Vector3 motion) {
        shapeInstance.transform.translate(motion);
        shapeInstance.calculateTransforms();
    }

    @Override
    public void translate(float x, float y, float z) {
        shapeInstance.transform.translate(x, y ,z);
        shapeInstance.calculateTransforms();
    }

    public boolean isHit(BoundingBox box) {
        BoundingBox shape = shapeInstance.calculateBoundingBox(new BoundingBox());

        return shape.contains(box);
    }
}
