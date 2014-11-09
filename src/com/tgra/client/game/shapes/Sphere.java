package com.tgra.client.game.shapes;

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

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 11/9/2014
 * Time : 01:04
 */
public class Sphere implements Shape {
    // Side textures
    private static Texture sphereTexture;

    // Box instance
    private static ModelInstance sphereInstance;

    // Box 3d representation
    private Vector3 center;
    private float radius, width, height, depth;

    public Sphere(String texture, Vector3 center, float radius) {
        this.center = center;

        this.radius = radius;

        this.sphereTexture = new Texture(Gdx.files.internal("data/box/" + texture));
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(sphereInstance, environment);
    }

    @Override
    public void build(ModelBuilder builder) {
        long attributes = VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates;

        builder.begin();

            MeshPartBuilder partBuilder = builder.part("sphere", GL20.GL_TRIANGLES, attributes, sphereTexture.material);

            partBuilder.sphere(
                radius,
                radius,
                radius,
                10,
                10
            );

        sphereInstance = new ModelInstance(builder.end());

        sphereInstance.transform.setTranslation(center);
    }
}
