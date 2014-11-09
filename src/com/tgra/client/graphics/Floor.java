package com.tgra.client.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.tgra.client.utility.Texture;
import javafx.geometry.Rectangle2D;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 11/8/2014
 * Time : 04:01
 */
public class Floor implements Object {
    private Rectangle2D floor;

    // Texture and model instance
    private Texture floorTexture;
    private static ModelInstance floorInstance;

    /**
     * Create instance of the floor
     * @param texture img / object name
     * @param x floor  x start point
     * @param z floor  z start point
     * @param width floor x end point
     * @param height floor z endpoint
     */
    public Floor(String texture, float x, float z, float width, float height) {
        // Create 2d floor parameters
        this.floor = new Rectangle2D(x, z, width, height);

        // Set texture
        floorTexture = new Texture(Gdx.files.internal("data/ground/" + texture));
    }

    @Override
    public void build(ModelBuilder builder) {
        long attributes = VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates;

        builder.begin();

            MeshPartBuilder partBuilder = builder.part("rect", GL20.GL_TRIANGLES, attributes, floorTexture.material);
            floorTexture.setUVRange(partBuilder, (float) floor.getWidth(), (float) floor.getHeight());

            float x = (float) floor.getMinX(), z = (float) floor.getMaxX();
            float width = (float) floor.getWidth(), height = (float) floor.getHeight();

            partBuilder.rect(
                    -(width * 0.5f), 1f, -(height * 0.5f),      // Corner 00
                    (width * 0.5f), 1f, -(height * 0.5f),       // Corner 10
                    (width * 0.5f), 1f, (height * 0.5f),        // Corner 11
                    -(width * 0.5f), 1f, (height * 0.5f),       // Corner 01
                    0, 0, 1                                     // Normal Vector
            );

        floorInstance = new ModelInstance(builder.end());

        floorInstance.transform.setToTranslation(
                (float) floor.getMinX() - (float) floor.getWidth() / 2,         // Floor x pos
                0,                                                              // Floor y pos
                (float) floor.getMinY() - (float) floor.getHeight() / 2);       // Floor z pos

        floorInstance.calculateTransforms();

        // Floor the floor ( lols )
        floorInstance.transform.rotate(Vector3.X, 180f);
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(floorInstance, environment);
    }
}
