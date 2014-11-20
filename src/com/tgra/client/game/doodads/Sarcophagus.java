package com.tgra.client.game.doodads;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

/**
 * <h1>Sarcophagus</h1>
 * <h2>com.tgra.client.game.doodads</h2>
 * <p></p>
 * Created on 20.11.2014.
 *
 * @author DanJoh
 * @version 1.0
 */
public class Sarcophagus extends AbstractDoodad {
    public ModelBatch modelBatch;
    public Model model;
    public ModelInstance modelInstance;

    public Sarcophagus(Vector3 position)
    {
        ModelLoader loader = new ObjLoader();
        model = loader.loadModel(Gdx.files.internal("data/props/Prop_Sarcophagus.OBJ"));
        modelInstance = new ModelInstance(model);

        this.position = position;
    }

    public void render(ModelBatch modelBatch, Environment environment)
    {

    }

    @Override
    public boolean isHit(BoundingBox player) {
        return false;
    }
}
