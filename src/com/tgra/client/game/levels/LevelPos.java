package com.tgra.client.game.levels;

import com.badlogic.gdx.math.Vector3;

/**
 * <h1>LevelPosition</h1>
 * <h2>com.tgra.client.game.levels</h2>
 * <p></p>
 * Created on 16.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class LevelPos {

    public int x;
    public int y;
    public int z;

    public LevelPos(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LevelPos)) return false;

        LevelPos levelPos = (LevelPos) o;

        if (x != levelPos.x) return false;
        if (y != levelPos.y) return false;
        if (z != levelPos.z) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }
}
