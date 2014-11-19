package com.tgra.client.game.doors;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.tgra.client.game.keys.Key;
import com.tgra.client.game.object.AbstractObject;
import com.tgra.client.game.shapes.Box;
import javafx.geometry.Side;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>AbstractDoor</h1>
 * <h2>com.tgra.client.game.doors</h2>
 * <p></p>
 * Created on 19.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public abstract class AbstractDoor extends AbstractObject implements Door {

    protected Box box;
    protected Side side;
    protected boolean isOpen;

    @Override
    public void open() {
        this.isOpen = true;
    }

    @Override
    public void update(float deltaTime) {
        // TODO: If open start animation or simply remove box
    }



}
