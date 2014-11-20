package com.tgra.client.game.shapes;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
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


    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void setRotation(float degrees, Vector3 axis) {
        shapeInstance.transform.rotate(axis, degrees);
        shapeInstance.calculateTransforms();
    }

    @Override
    public boolean isHit(BoundingBox box) {
        BoundingBox shape = shapeInstance.calculateBoundingBox(new BoundingBox());

        return shape.contains(box);
    }

    @Override
    public Vector3 getPosition() {
        return shapeInstance.transform.getTranslation(new Vector3());
    }

    @Override
    public void setPosition(Vector3 position) {
        shapeInstance.transform.translate(position);
        shapeInstance.calculateTransforms();
    }

}
