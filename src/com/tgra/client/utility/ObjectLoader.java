package com.tgra.client.utility;

import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 11/14/2014
 * Time : 15:52
 */
public class ObjectLoader {
    public Model model;
    public ModelInstance instance;

    public ObjectLoader(FileHandle file) {
        ModelLoader loader = new ObjLoader();

        model = loader.loadModel(file);
        instance = new ModelInstance(model);
    }
}
