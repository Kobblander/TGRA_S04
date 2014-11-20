package com.tgra.client.game.levels;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import com.tgra.client.game.LevelAssembler;
import com.tgra.client.game.rooms.Room;

/**
 * <h1>BasicLevel</h1>
 * <h2>com.tgra.client.game.levels</h2>
 * <p></p>
 * Created on 16.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class BasicLevel extends AbstractLevel {

    public BasicLevel(Vector3 position, int levelXSize, int levelYSize) {
        this.position = position;
        this.levelGrid = new LevelGrid(levelXSize, levelYSize);
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        for (Room r : this.levelGrid.getAllRooms()) {
            r.render(modelBatch, environment);
        }
    }

    @Override
    public void assemble() {
        LevelAssembler.assembleKobbaLevel(this);
        //LevelAssembler.assembleTestLevel(this);
    }
}
