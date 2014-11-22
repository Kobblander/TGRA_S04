package com.tgra.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.tgra.client.*;
import com.tgra.client.ui.*;

public class MenuScreen implements Screen {
    MyGame game;

    private SpriteBatch batch;
    private Stage stage;

    // Button Statics
    private static final float BUTTON_HEIGHT = 60f;
    private static final float BUTTON_SPACING = 25f;

    // Constructor
    public MenuScreen(MyGame game){
        this.game = game;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        stage.draw();
        batch.end();
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void show() {
        float centerX = Gdx.graphics.getWidth() / 2;
        float centerY = Gdx.graphics.getHeight() / 2;

        // Init Stage and batch
        batch = new SpriteBatch();
        stage = new Stage();

        drawMenu(centerX, centerY);
    }

    @Override
    public void hide() {
        stage.dispose();
        batch.dispose();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
    }

    /**
     * Draw basic menu
     * @param centerX Window, x center
     * @param centerY Window, y center
     */
    public void drawMenu(float centerX, float centerY) {
        // Set Assignment title
        Text title = new Text("Cool Running", centerX - 125, centerY + BUTTON_HEIGHT * 2.5f);
        title.setFont("verdana39");
        stage.addActor(title);

        // Create button for game screen
        Button main = new Button("Start Game", centerX, centerY + BUTTON_SPACING);
        main.button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
       game.setScreen(game.main);
            }
        });

        stage.addActor(main.button);

        // Create button for options screen
        Button opt = new Button("Options", centerX, centerY - BUTTON_HEIGHT);
        opt.button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.opt);
            }
        });

        stage.addActor(opt.button);

        Gdx.input.setInputProcessor(stage);
    }
}