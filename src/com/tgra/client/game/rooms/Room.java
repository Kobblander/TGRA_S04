package com.tgra.client.game.rooms;

import com.badlogic.gdx.math.Vector3;
import com.tgra.client.game.walls.Wall;

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

    public void setBottomWall(Wall bottomWall);
    public void setTopWall(Wall bottomWall);
    public void setRightWall(Wall bottomWall);
    public void setLeftWall(Wall bottomWall);

    public void setDoorColumns();

    public void setDoor();

    public void setPosition(Vector3 position);
    public RoomData getRoomData();
}
