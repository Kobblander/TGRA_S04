package com.tgra.client.game.shapes;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.tgra.client.game.object.Object;

/**
 * <h1>Box</h1>
 * <h2>com.tgra.client.game</h2>
 * <p></p>
 * Created on 8.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public interface Shape extends Object{
    /**
     * Render the object in its model batch
     * @param modelBatch batch to render the model in
     * @param environment objects environment
     */
    public void render(ModelBatch modelBatch, Environment environment);

    /**
     * Builds the object with the model builder
     * @param builder the model builder ( tool )
     */
    public void build(ModelBuilder builder);

    public void setRotation(float degrees, Vector3 axis);
}
