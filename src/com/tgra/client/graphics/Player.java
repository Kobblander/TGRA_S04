package com.tgra.client.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.tgra.client.game.World;
import com.tgra.client.game.object.Object;
import com.tgra.client.screens.GameScreen;
import com.tgra.client.utility.Texture;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 9/28/2014
 * Time : 00:38
 */
public class Player  {
    private static float HEIGHT = 0.8f;
    public static float WIDTH = 1f;
    private static float groundY = -0.4f;
    private static float angleX = -90, angleY = 0;

    // Texture
    private Texture playerTexture;
    private static ModelInstance playerInstance;

    public Player(ModelBuilder builder)
    {
        // Get Texture
        playerTexture = new Texture(Gdx.files.internal("data/player/error.jpg"));

        playerInstance = buildPlayer(builder);
    }

    /**
     * Renders the player
     * @param modelBatch Render to batch
     * @param environment Players environment
     */
    public void draw(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(playerInstance, environment);
    }

    /**
     * Build player model as a box
     * @param builder Model builder
     * @return Player Instance model
     */
    private ModelInstance buildPlayer(ModelBuilder builder) {
        long attributes = VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates;

        ModelInstance player = new ModelInstance(builder.createBox(WIDTH, HEIGHT, WIDTH, playerTexture.material, attributes));

        return player;
    }

    /**
     * Move player in 3d space
     * @param camera PerspectiveCamera
     * @param delta Delta time
     * @param key Key pressed
     */
    public void movePlayer(PerspectiveCamera camera, float delta, char key) {
        Vector3 nextMove = getNextMove(camera, delta, key);
        BoundingBox player = buildPlayer(nextMove);

        if(isHit(player))
            slideAlong(camera, nextMove);
        else
            setCameraPosition(camera, nextMove);
    }

    /**
     * Get Players next move
     * @param camera  PerspectiveCamera which represents the players pos
     * @param delta Delta time
     * @param key Key pressed
     * @return Players next pos
     */
    public Vector3 getNextMove(PerspectiveCamera camera, float delta, char key) {
        Vector3 nextMove = new Vector3();

        switch(key) {
            case 'A' :
                nextMove.set(camera.position.x + delta * MathUtils.sinDeg(angleX),
                        camera.position.y,
                        camera.position.z - delta * MathUtils.cosDeg(angleX));
                break;
            case 'D' :
                nextMove.set(camera.position.x - delta * MathUtils.sinDeg(angleX),
                        camera.position.y,
                        camera.position.z + delta * MathUtils.cosDeg(angleX));
                break;
            case 'W' :
                nextMove.set(camera.position.x + delta * camera.direction.x,
                        camera.position.y,
                        camera.position.z + delta * camera.direction.z);
                break;
            case 'S' :
                nextMove.set(camera.position.x - delta * camera.direction.x,
                        camera.position.y,
                        camera.position.z - delta * camera.direction.z);
                break;
            default:
                System.out.println("Invalid input key.");
        }

        return nextMove;
    }

    /**
     * Get vector in front of player
     * @param camera  PerspectiveCamera which represents the players pos
     * @param delta Delta time
     * @param scale Scale the x and z pos
     * @return Players next pos
     */
    public Vector3 getFrontMove(PerspectiveCamera camera, float delta, float scale) {
        Vector3 nextMove = new Vector3();

        nextMove.set(camera.position.x + (delta * camera.direction.x) * scale,
                camera.position.y,
                camera.position.z + (delta * camera.direction.z) * scale);

        return nextMove;
    }

    /**
     * Slides the player along the colliding object
     * @param camera PerspectiveCamera which represents the players pos
     * @param nextMove Players next move
     */
    private void slideAlong(PerspectiveCamera camera, Vector3 nextMove) {
        Vector3 pos = camera.position;

        // Slide along X
        updateCamera(camera, new Vector3(nextMove.x, pos.y, pos.z));

        // Slide along Z
        updateCamera(camera, new Vector3(pos.x, pos.y, nextMove.z));
    }

    /**
     * Update player model with colliding detection
     * @param camera PerspectiveCamera which represents the players pos
     * @param nextMove Players next move
     */
    private void updateCamera(PerspectiveCamera camera, Vector3 nextMove) {
        BoundingBox player = buildPlayer(nextMove);

        if(!isHit(player))
            setCameraPosition(camera, nextMove);
    }

    /**
     * Update player model pos
     * @param camera PerspectiveCamera which represents the players pos
     * @param nextMove Players next move
     */
    public static void setCameraPosition(PerspectiveCamera camera, Vector3 nextMove) {
        camera.position.set(nextMove);

        // Update player
        playerInstance.transform.setToTranslation(nextMove.x, nextMove.y + groundY, nextMove.z);
        playerInstance.transform.rotate(Vector3.Y, angleX);
        playerInstance.calculateTransforms();
    }

    /**
     * Update player rotation
     * @param camera PerspectiveCamera which represents the players pos
     */
    public static void rotate(PerspectiveCamera camera) {
        // Update player rotation and position
        playerInstance.transform.setToTranslation(camera.position.x, camera.position.y + groundY, camera.position.z);
        playerInstance.transform.rotate(Vector3.Y, angleX);
        playerInstance.calculateTransforms();
    }

    /**
     * Get Players Bounding box
     * @param nextMove Players next move
     * @return Player bounding box
     */
    private static BoundingBox buildPlayer(Vector3 nextMove) {
        BoundingBox box = playerInstance.calculateBoundingBox(new BoundingBox());
        box.set(box.min.add(nextMove), box.max.add(nextMove));

        return box;
    }

    public void setAngles(float angleX, float angleY) {
        this.angleX = angleX;
        this.angleY = angleY;
    }

    private boolean isHit(BoundingBox player) {
        List<Object> objs = World.getInstance().getObjectList();

        /*
        for(Object obj: objs)
            if(obj.isHit(player))
                return true;
                */

        return false;
    }

    public void flashLight(PointLight flashLight) {
        float delta = Gdx.graphics.getDeltaTime();

        Vector3 light = getFrontMove(GameScreen.camera, delta, 150f);
        flashLight.position.set(light);
    }
}