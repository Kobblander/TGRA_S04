package com.tgra.client.game.keys;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.tgra.client.game.World;
import com.tgra.client.game.object.Object;
import com.tgra.client.game.shapes.Box;

/**
 * <h1>Key</h1>
 * <h2>com.tgra.client.game.doors</h2>
 * <p></p>
 * Created on 19.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class BasicKey extends AbstractKey {

    public BasicKey() {
    }

    @Override
    protected void build() {
        tempBox = new Box("wood.jpg", position, 0.5f, 0.5f, 0.5f);
        tempBox.build(World.getInstance().getModelBuilder());
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        if (!collected) {
            tempBox.render(modelBatch, environment);
        }
    }

}
