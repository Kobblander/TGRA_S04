package com.tgra.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.tgra.client.MyGame;
import com.tgra.client.events.InputManager;
import com.tgra.client.game.GameFactory;
import com.tgra.client.game.rooms.Room;
import com.tgra.client.game.shapes.Cylinder;
import com.tgra.client.graphics.Player;
import com.tgra.client.utility.Lights;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 9/26/2014
 * Time : 13:19
 */
public class GameScreen implements Screen {
    private MyGame game;

    // Stage
    private Stage stage;

    // Gamer camera
    public static PerspectiveCamera camera;
    private static InputManager controller;

    // ModelBatch
    private ModelBatch modelBatch;

    // Player and light
    private static Player player;
    private static Lights lights;

    private static Room room;
    private static Room room2, room3, room4;
    private static Cylinder column;


    public GameScreen(MyGame game) {
        this.game = game;
    }

    @Override
    public void show () {
        // Setup screen
        stage = new Stage();

        // Setup camera
        camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(0f, 0f, 0f);
        camera.near = 0.2f;
        camera.far = 350f;

        // Setup batch
        ModelBuilder modelBuilder = new ModelBuilder();
        modelBatch = new ModelBatch();

        // Setup Player and light
        player = new Player(modelBuilder);
        lights = new Lights(player);

        // Setup road
        /*
        sphere = new Sphere("earth.jpg", new Vector3(0, 0, 0), 1.5f);
        sphere.build(modelBuilder);
        */

        /*
        box = new Box("wood.jpg", new Vector3(0, 0, 0), 5f, 5f, 5f);
        box.build(modelBuilder);
        */

        room = GameFactory.createBasicRoom(new Vector3(2, -1.9f, 2), 2);
        room2 = GameFactory.createBasicRoom(new Vector3(2, -1.9f, 16), 2);
        room3 = GameFactory.createBasicRoom(new Vector3(16, -1.9f, 2), 2);
        room4 = GameFactory.createBasicRoom(new Vector3(-16, -1.9f, 2), 2);

        controller = new InputManager(camera, player);
        Gdx.input.setInputProcessor(controller);

        game.setupBasicScreen(stage);
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        // Update controller
        controller.update(delta);

        Environment environment = Lights.getEnvironment();

        // Render graphics instances
        modelBatch.begin(camera);
            lights.render(delta);

            //box.render(modelBatch, environment);


            player.draw(modelBatch, environment);

            room.render(modelBatch, environment);
            room2.render(modelBatch, environment);
            room3.render(modelBatch, environment);
            room4.render(modelBatch, environment);
        modelBatch.end();


        // Render stats
        game.renderBasicScreen(stage);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose () {
        modelBatch.dispose();
    }

}
