package com.tgra.client.game.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.tgra.client.utility.Texture;

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
    protected BoundingBox boundingBox = new BoundingBox();
    protected Vector3 center;
    protected Texture texture;

    @Override
    public Vector3 getPosition() {
        return center;
    }

    @Override
    public void setRotation(float degrees, Vector3 axis) {
        //shapeInstance.transform.rotate(axis, degrees);
        //shapeInstance.calculateTransforms();
    }

    @Override
    public void translate(Vector3 motion) {
        //shapeInstance.transform.translate(motion);
        //shapeInstance.calculateTransforms();
    }

    @Override
    public void translate(float x, float y, float z) {
        //shapeInstance.transform.translate(x, y, z);
        //shapeInstance.calculateTransforms();
    }

    @Override
    public boolean isHit(BoundingBox player) {
        boolean hit =  boundingBox.intersects(player);

        BoundingBox b = new BoundingBox().mul(shapeInstance.transform);
        boolean bHit = b.intersects(player);

        if(!hit) {
            System.out.println("------------------------------------------------------");
            System.out.println("Shape ID : " + boundingBox.hashCode());
            System.out.println("Shape Center : " + boundingBox.getCenter());
            System.out.println("Shape Size : " + boundingBox.getWidth() + ", " + boundingBox.getHeight() + ", " + boundingBox.getDepth());
            System.out.println("SC : " + b.getCenter());
            System.out.println("SS : " + b.getWidth() + ", " + b.getHeight() + ", " + b.getDepth());
            System.out.println("------------------------------------------------------");
        }

        if(hit) {
            shapeInstance.materials.get(0).set(ColorAttribute.createDiffuse(Color.RED));
        }

        //if(bHit)
           // shapeInstance.materials.get(0).set(ColorAttribute.createDiffuse(Color.GREEN));


        return hit;
    }
}
