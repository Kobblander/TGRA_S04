package com.tgra.client.game.shapes;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.tgra.client.game.object.AbstractObject;

/**
 * <h1>AbstractShape</h1>
 * <h2>com.tgra.client.game.shapes</h2>
 * <p></p>
 * Created on 18.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public abstract class AbstractShape extends AbstractObject implements Shape {

    protected ModelInstance shapeInstance;

    @Override
    public void setRotation(float degrees) {
        shapeInstance.transform.rotate(Vector3.Y, degrees);
        shapeInstance.calculateTransforms();
    }

    @Override
    public void update(float deltaTime) {

    }
}
