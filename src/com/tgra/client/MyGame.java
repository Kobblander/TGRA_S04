package com.tgra.client;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.tgra.client.managers.AudioManager;
import com.tgra.client.managers.KeyManager;
import com.tgra.client.screens.GameScreen;
import com.tgra.client.screens.MenuScreen;
import com.tgra.client.screens.OptionScreen;
import com.tgra.client.ui.Text;

public class MyGame extends Game {
    private MenuScreen mainMenuScreen;

    private Text fps, camX, camY, camZ;

    // Screens
    public GameScreen main;
    public OptionScreen opt;
    public GameScreen test;

    // Files
    private AudioManager audioFiles;

    @Override
    public void create() {
        mainMenuScreen = new MenuScreen(this);

        // Set Main Screens
        main = new GameScreen(this);

        // Set Option Screen
        opt = new OptionScreen(this);

        // Set up test Screen
        test = new GameScreen(this);

        // Load Audio / Key bindings
        loadResources();

        setScreen(mainMenuScreen);
    }

    public void toMenu() {
        setScreen(mainMenuScreen);
    }

    /**
     * Setups back button and fps display
     * @param stage elements are added to the stage
     */
    public void setupBasicScreen(Stage stage) {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        // Set FPS display
        fps = new Text("fps: 0", width - 55, height - 10);
        stage.addActor(fps);

        // Set Cam pos
        camX = new Text("X : ", 10, height - 10);
        stage.addActor(camX);

        camY = new Text("Y : ", 10, height - 35);
        stage.addActor(camY);

        camZ = new Text("Z : ", 10, height - 60);
        stage.addActor(camZ);
    }

    /**
     * Draw stage and update fps display
     * @param stage viewport
     */
    public void renderBasicScreen(Stage stage){
        fps.setText("fps:" + Gdx.graphics.getFramesPerSecond());

        camX.setText("X : " + GameScreen.camera.position.x);
        camY.setText("Y : " + GameScreen.camera.position.y);
        camZ.setText("Z : " + GameScreen.camera.position.z);

        stage.draw();
    }

    public void loadResources() {
        // Load Audio files
        AudioManager.load();

        // Load Key configuration
        KeyManager.load();
    }
}
