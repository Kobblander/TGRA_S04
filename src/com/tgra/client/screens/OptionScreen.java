package com.tgra.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tgra.client.*;
import com.tgra.client.managers.AudioManager;
import com.tgra.client.ui.*;

public class OptionScreen implements Screen {
    private MyGame game;

    private SpriteBatch batch;
    private Stage stage;
    private Skin skin = new Skin(Gdx.files.internal("data/ui/uiskin.json"));

    // Slider
    public Slider volumeSlider;

    // Constructor
    public OptionScreen(MyGame inGame){
        game = inGame;
    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
    }

    @Override
    public void pause() {}

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());

        stage.draw();
    }

    @Override
    public void resize(int arg0, int arg1) {}

    @Override
    public void resume() {}

    @Override
    public void show() {
        stage = new Stage();
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(stage);

        float volume = AudioManager.getVolume();

        Table rootTable = new Table();
        rootTable.setFillParent(true);

        Text title = new Text("Settings", 50, 550);
        title.setFont("verdana39");
        stage.addActor(title);

        Button back = new Button();
        back.button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.toMenu();
            }
        });
        stage.addActor(back.button);

        Label soundText = new Label("Sound: ", skin);

        volumeSlider = new Slider(0, 100, 1, false, skin);
        volumeSlider.setValue(50);

        final Label soundValue = new Label("50", skin);

        Table table = new Table(skin);
        table.add(soundText).width(100).height(60).padLeft(50);
        table.add(volumeSlider).width(500).height(60).align(Align.center);
        table.add(soundValue).pad(0, 25, 0, 10).width(100).height(60);

        rootTable.add(table);
        stage.addActor(rootTable);

        volumeSlider.addListener(new ChangeListener() {
            public void changed (ChangeListener.ChangeEvent event, Actor actor) {
                float value = Math.round(volumeSlider.getValue());
                AudioManager.setVolume(value / 100f);

                soundValue.setText(Integer.toString((int) value));
            }
        });
    }

    @Override
    public void hide() {
        stage.dispose();
        batch.dispose();
    }
}
