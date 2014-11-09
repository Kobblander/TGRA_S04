package com.tgra.client.utility;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.tgra.client.screens.GameScreen;

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
    public TextureRegion region;
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

    public void setUVRange(MeshPartBuilder partBuilder, float width, float height) {
        Vector3 size = new Vector3(1, 1, 1);
        GameScreen.camera.unproject(size);

        float u = texture.getWidth() / (Math.abs(width * size.x));
        float v = texture.getHeight() / (Math.abs( height * size.y));

        partBuilder.setUVRange(0, 0, u, v);
    }
}
