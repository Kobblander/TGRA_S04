package com.tgra.client.game.walls;

import com.badlogic.gdx.math.Vector3;
import com.tgra.client.game.rooms.RoomData;

/**
 * <h1>Wall</h1>
 * <h2>com.tgra.client</h2>
 * <p></p>
 * Created on 8.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public interface Wall {

    public void initWall(Vector3 pos, float rotation, float length, float height, float thickness);
    public void setRoomData(RoomData roomData);

}
