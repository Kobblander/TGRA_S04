package com.tgra.client.game.keys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.tgra.client.managers.AudioManager;
import com.tgra.client.utility.Lights;

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

    private ModelInstance instance;
    private BoundingBox box;
    private Vector3 size = new Vector3(3f, 3f, 3f);
    private boolean up = true;

    public BasicKey() {
    }

    @Override
    protected void build() {
        //tempBox = new Box("wood.jpg", position, 0.5f, 0.5f, 0.5f);
        //tempBox.build(World.getInstance().getModelBuilder(), 0);

        ModelLoader<?> loader = new ObjLoader();
        Model model = loader.loadModel(Gdx.files.internal("data/props/Key_B_02.obj"));
        instance = new ModelInstance(model);
        instance.transform.setTranslation(position);
        instance.transform.scale(0.2f, 0.2f, 0.2f);
        instance.calculateTransforms();

        Lights.addLight(position);
        buildBox();
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        if (!collected) {
            instance.transform.rotate(Vector3.Y, 60f * Gdx.graphics.getDeltaTime());
            instance.transform.rotate(Vector3.Z, 60f * Gdx.graphics.getDeltaTime());
            bounceKey();

            modelBatch.render(instance, environment);
        }
    }

    @Override
    public boolean isHit(BoundingBox player) {
        boolean hit = box.intersects(player);

        if(hit && !collected) {
            pickup();
            AudioManager.play("key");
        }

        return false;
    }

    private void buildBox() {
        Vector3 min = new Vector3(position.x - size.x / 2, position.y - size.y / 2, position.z - size.z / 2);
        Vector3 max = new Vector3(position.x + size.x / 2, position.y + size.y / 2, position.z + size.z / 2);

        box = new BoundingBox(max, min);
        System.out.println(box);
    }

    private void bounceKey() {

        float delta = 20 * Gdx.graphics.getDeltaTime() / 100;

        if(up) // Go up
            delta *= 1;
        else // Go down
            delta *= -1;

        if(position.y > -0.4)
            up = false;

        if(position.y < -0.8f)
            up = true;

        position.y = position.y + delta;

        instance.transform.setTranslation(position);
    }
}
