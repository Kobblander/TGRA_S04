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
import com.badlogic.gdx.math.collision.BoundingBox;
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
public class Box extends AbstractShape {

    // Box 3d representation
    private float width, height, depth;

    //region Constructors

    public Box(String texture, Vector3 center, float width, float height, float depth) {
        this.center = center;

        this.width = width;
        this.height = height;
        this.depth = depth;

        this.texture = new Texture(Gdx.files.internal("data/box/" + texture));
    }

    //endregion

    @Override
    public void build(ModelBuilder builder) {
        long attributes = VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates;

        builder.begin();

        MeshPartBuilder partBuilder = builder.part("box", GL20.GL_TRIANGLES, attributes, texture.material);
        texture.setUVRange(partBuilder, width, height);

        partBuilder.box(
            width,
            height,
            depth
        );

        shapeInstance = new ModelInstance(builder.end());

        shapeInstance.transform.setTranslation(center);
        shapeInstance.calculateTransforms();

        boundingBox = shapeInstance.calculateBoundingBox(new BoundingBox());
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(shapeInstance, environment);
    }

    public float getRotation() {
        Quaternion q = new Quaternion();

        shapeInstance.transform.getRotation(q);

        return q.getAngle();
    }
}
