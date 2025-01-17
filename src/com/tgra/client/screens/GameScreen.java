package com.tgra.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.tgra.client.MyGame;
import com.tgra.client.events.InputManager;
import com.tgra.client.game.GameFactory;
import com.tgra.client.game.World;
import com.tgra.client.game.levels.Level;
import com.tgra.client.game.object.Object;
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

    // Player and light
    private static Player player;
    private static Lights lights;

    private World world = World.getInstance();


    private GameFactory gameFactory = GameFactory.getInstance();
    private ModelBatch modelBatch;

    private Level level;

    public GameScreen(MyGame game) {
        this.game = game;
    }

    public void build() {
        // Setup camera
        camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(2f, 0f, 2f);
        camera.near = 0.2f;
        camera.far = 110f;

        // Setup batch
        ModelBuilder modelBuilder = World.getInstance().getModelBuilder();

        // Setup Player and light
        player = new Player(modelBuilder);
        lights = new Lights(player);

        level = gameFactory.createBasicLevel(10, 10);

        controller = new InputManager(camera, player);
    }

    @Override
    public void show () {
        // Setup screen
        stage = new Stage();

        Gdx.input.setInputProcessor(controller);
        controller.setCameraDirection();

        game.setupBasicScreen(stage);
    }

    private void update(float delta) {
        controller.update(delta);
        for (Object o : World.getInstance().getObjectList()) {
            o.update(delta);
        }
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        modelBatch = world.getModelBatch();

        // Update controller
        update(delta);

        Environment environment = Lights.getEnvironment();

        // -- Render begin -- //
        modelBatch.begin(camera);
            lights.render(delta);

            player.draw(modelBatch, environment);

            level.render(modelBatch, environment);

        modelBatch.end();
        // -- Render end -- //


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
