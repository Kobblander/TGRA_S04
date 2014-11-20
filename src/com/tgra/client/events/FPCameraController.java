package com.tgra.client.events;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;
import org.lwjgl.opengl.GL11;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 11/15/2014
 * Time : 21:04
 */
public class FPCameraController {
    private PerspectiveCamera camera;

    //3d vector to store the camera's position in
    private Vector3 position = null;

    //the rotation around the Y axis of the camera
    private float yaw = 0.0f;

    //the rotation around the X axis of the camera
    private float pitch = 0.0f;

    public FPCameraController(PerspectiveCamera camera, float x, float y, float z)
    {
        this.camera = camera;

        //instantiate position Vector3f to the x y z params.
        position = new Vector3(x, y, z);
    }

    //increment the camera's current yaw rotation
    public void yaw(float amount)
    {
        //increment the yaw by the amount param
        yaw += amount;
    }

    //increment the camera's current yaw rotation
    public void pitch(float amount)
    {
        //increment the pitch by the amount param
        pitch += amount;
    }

    //moves the camera forward relative to its current rotation (yaw)
    public void walkForward(float distance)
    {
        position.x -= distance * (float)Math.sin(Math.toRadians(yaw));
        position.z += distance * (float)Math.cos(Math.toRadians(yaw));
    }

    //moves the camera backward relative to its current rotation (yaw)
    public void walkBackwards(float distance)
    {
        position.x += distance * (float)Math.sin(Math.toRadians(yaw));
        position.z -= distance * (float)Math.cos(Math.toRadians(yaw));
    }

    //strafes the camera left relative to its current rotation (yaw)
    public void strafeLeft(float distance)
    {
        position.x -= distance * (float)Math.sin(Math.toRadians(yaw-90));
        position.z += distance * (float)Math.cos(Math.toRadians(yaw-90));
    }

    //strafes the camera right relative to its current rotation (yaw)
    public void strafeRight(float distance)
    {
        position.x -= distance * (float)Math.sin(Math.toRadians(yaw+90));
        position.z += distance * (float)Math.cos(Math.toRadians(yaw+90));
    }

    //translates and rotate the matrix so that it looks through the camera
    //this dose basic what gluLookAt() does
    public void lookThrough()
    {
        //rotate the pitch around the X axis
        GL11.glRotatef(pitch, 1.0f, 0.0f, 0.0f);

        //rotate the yaw around the Y axis
        GL11.glRotatef(yaw, 0.0f, 1.0f, 0.0f);

        //translate to the position vector's location
        GL11.glTranslatef(position.x, position.y, position.z);
        camera.position.set(position);
        camera.update();
    }
}
