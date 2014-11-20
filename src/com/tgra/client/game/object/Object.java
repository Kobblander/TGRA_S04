package com.tgra.client.game.object;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

/**
 * <h1>Entity</h1>
 * <h2>com.tgra.client.game</h2>
 * <p></p>
 * Created on 8.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public interface Object {

    public void update(float deltaTime);

    public void render(ModelBatch modelBatch, Environment environment);

    public Vector3 getPosition();

    public void setPosition(Vector3 position);

    public boolean isHit(BoundingBox player);
}
