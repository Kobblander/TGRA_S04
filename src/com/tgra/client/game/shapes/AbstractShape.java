package com.tgra.client.game.shapes;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

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
    public void setRotation(float degrees) {
        shapeInstance.transform.rotate(Vector3.Y, degrees);
        shapeInstance.calculateTransforms();
    }
}
