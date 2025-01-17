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
import com.badlogic.gdx.math.collision.BoundingBox;
import com.tgra.client.game.World;
import com.tgra.client.utility.Texture;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 11/10/2014
 * Time : 04:36
 */
public class Cylinder extends AbstractShape {
    private float width, height, depth;

    public Cylinder(String texture, Vector3 center, float width, float height, float depth) {
        this.center = center;

        this.width = width;
        this.height = height;
        this.depth = depth;

        this.texture = new Texture(Gdx.files.internal("data/cylinder/" + texture));
        this.boundingBox = new BoundingBox();

        build(World.getInstance().getModelBuilder(), 0);
    }

    @Override
    public void build(ModelBuilder builder, float degrees) {
        long attributes = VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates;

        builder.begin();

        MeshPartBuilder partBuilder = builder.part("cylinder", GL20.GL_TRIANGLES, attributes, texture.material);
        texture.setUVRange(partBuilder, width, height);

        partBuilder.cylinder(
                width,
                height,
                depth,
                10
        );

        shapeInstance = new ModelInstance(builder.end());

        shapeInstance.transform.setTranslation(center);
        shapeInstance.calculateTransforms();

        setBoundingBox();
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(shapeInstance, environment);
    }

    @Override
    public void setRotation(float degrees) {
        shapeInstance.transform.rotate(Vector3.Y, degrees);
        shapeInstance.calculateTransforms();
    }

    @Override
    protected void setBoundingBox() {
        Vector3 min, max;

        min = new Vector3(center.x - width / 2, center.y - height / 2, center.z - depth / 2 );
        max = new Vector3(center.x + width / 2, center.y + height / 2, center.z + depth / 2);

        boundingBox = new BoundingBox(max, min);
    }
}
