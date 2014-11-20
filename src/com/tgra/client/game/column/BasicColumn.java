package com.tgra.client.game.column;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.tgra.client.game.World;
import com.tgra.client.game.object.AbstractObject;
import com.tgra.client.game.shapes.Cylinder;
import com.tgra.client.utility.Texture;

/**
 * <h1>BasicColumn</h1>
 * <h2>com.tgra.client.game.column</h2>
 * <p></p>
 * Created on 20.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class BasicColumn extends AbstractObject implements Column {

    protected Cylinder cylinder;
    protected float width;
    protected float height;
    protected float depth;

    public BasicColumn(String texture, Vector3 position, float width, float height, float depth) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.depth = depth;
        cylinder = new Cylinder(texture, position, width, height, depth);
        cylinder.build(World.getInstance().getModelBuilder());
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        cylinder.render(modelBatch, environment);
    }

    @Override
    public boolean isHit(BoundingBox player) {
        return false;
    }

    @Override
    public void setRotation(float rotation, Vector3 axis) {
        cylinder.setRotation(rotation, axis);
    }
}
