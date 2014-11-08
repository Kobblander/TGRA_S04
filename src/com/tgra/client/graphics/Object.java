package com.tgra.client.graphics;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 11/8/2014
 * Time : 04:07
 */
public interface Object {

    /**
     * Builds the object with the model builder
     * @param builder the model builder ( tool )
     */
    public void build(ModelBuilder builder);

    /**
     * Render the object in its model batch
     * @param modelBatch batch to render the model in
     * @param environment objects environment
     */
    public void render(ModelBatch modelBatch, Environment environment);
}
