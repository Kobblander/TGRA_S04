package com.tgra.client.utility;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 10/15/2014
 * Time : 00:18
 */
public class Texture {
    private FileHandle file;

    // Textures
    public com.badlogic.gdx.graphics.Texture texture;
    private TextureAttribute attribute;
    private TextureRegion region;
    public TiledDrawable tiledDrawable;
    public Material material;

    public Texture(FileHandle file) {
        this.file = file;

        // Setup the texture
        setup();
    }

    private void setup() {
        texture = new com.badlogic.gdx.graphics.Texture(file);
        texture.setWrap(com.badlogic.gdx.graphics.Texture.TextureWrap.Repeat, com.badlogic.gdx.graphics.Texture.TextureWrap.Repeat);
        region = new TextureRegion(texture);
        attribute = new TextureAttribute(TextureAttribute.Diffuse, texture);
        material = new Material(attribute);
    }

    public void setUVWrap(ModelInstance modelInstance) {
        final TextureAttribute textureAttribute = (TextureAttribute) modelInstance.materials.first().get(TextureAttribute.Diffuse);

        textureAttribute.textureDescription.magFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Nearest;
        textureAttribute.textureDescription.minFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Nearest;
        modelInstance.materials.first().set(textureAttribute);
    }
}
