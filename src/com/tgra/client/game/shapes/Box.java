package com.tgra.client.game.shapes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.tgra.client.utility.Texture;

/**
 * <h1>Box</h1>
 * <h2>com.tgra.client.game.shapes</h2>
 * <p></p>
 * Created on 8.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class Box implements Shape {
    // Side textures
    private static Texture boxTexture;

    // Box instance
    private ModelInstance boxInstance;

    // Box 3d representation
    private Vector3 center;
    private float width, height, depth;

    //region Constructors

    public Box(String texture, Vector3 center, float width, float height, float depth) {
        this.center = center;

        this.width = width;
        this.height = height;
        this.depth = depth;

        this.boxTexture = new Texture(Gdx.files.internal("data/box/" + texture));
    }

    //endregion

    @Override
    public void build(ModelBuilder builder) {
        long attributes = VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates;

        builder.begin();

            MeshPartBuilder partBuilder = builder.part("box", GL20.GL_TRIANGLES, attributes, boxTexture.material);
            boxTexture.setUVRange(partBuilder, width, height);

            partBuilder.box(
                center.x,
                center.y,
                center.z,
                width,
                height,
                depth
            );

        boxInstance = new ModelInstance(builder.end());
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(boxInstance, environment);
    }

    public void setRotation(Vector3 axis, float degrees) {
        boxInstance.transform.rotate(axis, degrees);

    }

    public float getRotation() {
        Quaternion q = new Quaternion();

        boxInstance.transform.getRotation(q);

        return q.getAngle();
    }
}
