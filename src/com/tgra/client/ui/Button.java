package com.tgra.client.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Button {
    private Skin skin = new Skin(Gdx.files.internal("data/ui/uiskin.json"));

    public static TextButton button;
    private static final float BUTTON_WIDTH = 300f;
    private static final float BUTTON_HEIGHT = 60f;
    private static final float BUTTON_SPACING = 10f;

    /**
     * Constructor, back button
     */
    public Button() {
        button = new TextButton("Back", skin, "default");

        backButton();
    }

    /**
     * Constructor
     * @param Text Button text
     * @param x x coordinate
     * @param y y coordinate
     */
    public Button(String Text, float x, float y) {
        button = new TextButton(Text, skin, "default");

        setButton(button, x, y);
    }

    /**
     * Sets the button size and pos
     * @param button element
     * @param x x coordinate
     * @param y y coordinate
     */
    private void setButton(final TextButton button, float x, float y) {
        button.setWidth(BUTTON_WIDTH);
        button.setHeight(BUTTON_HEIGHT);

        button.setPosition(x - BUTTON_WIDTH / 2, y - BUTTON_HEIGHT / 2);
    }

    /**
     * Create back button
     */
    private void backButton() {
        float x = Gdx.graphics.getWidth() - BUTTON_WIDTH / 2 - BUTTON_SPACING;
        float y = BUTTON_SPACING;

        button.setWidth(BUTTON_WIDTH / 2);
        button.setHeight(BUTTON_HEIGHT);

        button.setPosition(x, y);
    }
}
