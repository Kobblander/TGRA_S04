package com.tgra.client.game.rooms;

import com.badlogic.gdx.math.Vector3;
import com.tgra.client.game.walls.Wall;
import javafx.geometry.Side;

/**
 * <h1>Room</h1>
 * <h2>com.tgra.client</h2>
 * <p></p>
 * Created on 8.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public interface Room extends com.tgra.client.game.object.Object {

    public void setWall(Wall wall, Side side);
    public void setDoorColumns(Side side);

    public void setDoor();

    public void setPosition(Vector3 position);
    public RoomData getRoomData();
}
