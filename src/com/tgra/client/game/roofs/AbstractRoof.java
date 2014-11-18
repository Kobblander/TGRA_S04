package com.tgra.client.game.roofs;

import com.tgra.client.game.object.AbstractObject;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 11/10/2014
 * Time : 04:13
 */
public abstract class AbstractRoof extends AbstractObject implements Roof {
    protected float xSize;
    protected float ySize;
    protected float zSize;

    protected abstract void build();

    @Override
    public void update(float deltaTime) {

    }
}
