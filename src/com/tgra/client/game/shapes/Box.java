package com.tgra.client.game.shapes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
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
<<<<<<< HEAD
    private float width, height, depth;
    private Vector3 min, max;
=======
    private float width, height, depth, angle;
>>>>>>> 7ae4e7c22883beb52f12f4b81174d971d4124296

    //region Constructors

    public Box(String texture, Vector3 center, float width, float height, float depth) {
        this.center = center;

        this.width = width;
        this.height = height;
        this.depth = depth;

        this.boundingBox = new BoundingBox();

        this.texture = new Texture(Gdx.files.internal("data/box/" + texture));
    }

    //endregion

    @Override
    public void build(ModelBuilder builder, float degrees) {
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

        shapeInstance.transform.translate(center);
        //shapeInstance.calculateTransforms();

        shapeInstance.transform.rotate(Vector3.Y, degrees);
        shapeInstance.calculateTransforms();
        this.boundingBox = new BoundingBox();
        shapeInstance.calculateBoundingBox(boundingBox);

<<<<<<< HEAD
        //setBoundingBox(degrees);
=======
        angle = degrees;

        setBoundingBox();
>>>>>>> 7ae4e7c22883beb52f12f4b81174d971d4124296
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

    private void setBoundingBox() {
<<<<<<< HEAD
        Vector3 min = new Vector3(center.x - depth / 2, center.y - height / 2, center.z - width / 2);
        Vector3 max = new Vector3(center.x + depth / 2, center.y + height / 2, center.z + width / 2);

        boundingBox = new BoundingBox(min, max);
    }

    private void setBoundingBox(float degrees) {


        Vector3 min = new Vector3(center.x - width / 2, center.y - height / 2, center.z - depth / 2);
        Vector3 max = new Vector3(center.x + width / 2, center.y + height / 2, center.z + depth / 2);
=======
        Vector3 min, max;

        if (angle == -90) {
            min = new Vector3(center.x - depth / 2, center.y - height / 2, center.z - width / 2);
            max = new Vector3(center.x + depth / 2, center.y + height / 2, center.z + width / 2);
        } else {
            min = new Vector3(center.x - width / 2, center.y - height / 2, center.z - depth / 2);
            max = new Vector3(center.x + width / 2, center.y + height / 2, center.z + depth / 2);
        }
>>>>>>> 7ae4e7c22883beb52f12f4b81174d971d4124296

        boundingBox = new BoundingBox(max, min);
        //boundingBox.mul(new Matrix4(new Quaternion(Vector3.Y, degrees)));
        //boundingBox = shapeInstance.calculateBoundingBox(boundingBox);
    }

    @Override
    public void setRotation(float degrees) {
        shapeInstance.transform.rotate(Vector3.Y, degrees);
        shapeInstance.calculateTransforms();
    }
}
