package com.tgra.client.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Text extends Actor {
    private float x, y;
    private String text;
    private BitmapFont font;

    public Text(String initText, float posX, float posY){
        font = new BitmapFont(Gdx.files.internal("data/ui/font.fnt"), Gdx.files.internal("data/ui/font.png"), false);
        text = initText;
        x = posX;
        y = posY;

        // Fix text blur
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    /**
     * Set text value
     * @param newText new text value
     */
    public void setText(String newText) {
        text = newText;
    }

    /**
     * Set text font
     * @param fontName
     */
    public void setFont(String fontName) {
        font = new BitmapFont(Gdx.files.internal("data/ui/" + fontName + ".fnt"), Gdx.files.internal("data/ui/" + fontName + ".png"), false);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        font.draw(batch, text, x, y);
    }
}
