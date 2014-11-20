package com.tgra.client.game.levels;

import com.tgra.client.game.rooms.Room;

import java.util.*;

/**
 * <h1>LevelData</h1>
 * <h2>com.tgra.client.game.levels</h2>
 * <p></p>
 * Created on 15.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class LevelGrid {


    private int gridXSize;
    private int gridYSize;

    private Map<LevelPos, Room> roomMap = new HashMap<LevelPos, Room>();

    public LevelGrid(int gridSize) {
        this.gridXSize = gridSize;
        this.gridYSize = gridSize;
    }

    public LevelGrid(int gridXSize, int gridYSize) {
        this.gridXSize = gridXSize;
        this.gridYSize = gridYSize;
    }

    public void addRoomAtPos(Room room, LevelPos levelPos) {

        // Check if the levelPosition coordinates are invalid.
        /*
        if (levelPos.x > gridXSize || levelPos.y > gridYSize ||
                levelPos.x < 0 || levelPos.y < 0) {
            //System.out.println("Invalid level position.");
            return;
        }
        */
        // Check if there is already a room at that position.
        if (roomMap.containsKey(levelPos)) {
            System.out.println("There is already a levelCell at that position.");
            return;
        }

        // If the position is empty add the cell.
        roomMap.put(levelPos, room);
    }

    public Room getRoomAtPos(LevelPos levelPos) {
        return roomMap.get(levelPos);
    }

    public Collection<LevelPos> getPositionOfRoom(Room room) {
        Collection<LevelPos> levelPoses = new ArrayList<LevelPos>();

        Set<Map.Entry<LevelPos, Room>> set = roomMap.entrySet();
        Iterator<Map.Entry<LevelPos, Room>> iterator = set.iterator();

        while (iterator.hasNext()) {
            Map.Entry<LevelPos, Room> entry = iterator.next();
            LevelPos lp = entry.getKey();
            Room r = entry.getValue();
            if (r.equals(room)) {
                levelPoses.add(lp);
            }
        }
        return levelPoses;
    }

    public boolean isPosOccupied(LevelPos levelPos) {
        return roomMap.containsKey(levelPos);
    }

    public boolean isPosOccupied(Room room, LevelPos levelPos) {
        int roomXSize = room.getRoomData().getxSize();
        int roomYSize = room.getRoomData().getySize();
        int roomZSize = room.getRoomData().getzSize();

        for (int x = levelPos.x; x < roomXSize; x++) {
            for (int y = levelPos.y; y < roomYSize; y++) {
                for (int z = levelPos.z; z < roomZSize; z++) {
                    if (roomMap.containsKey(new LevelPos(x, y, z))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * There might be multiple
     * @return
     */
    public Collection<Room> getAllRooms() {
        Collection<Room> rooms = roomMap.values();
        ArrayList<Room> result = new ArrayList<Room>();
        HashSet<Room> hashSet = new HashSet<Room>();
        hashSet.addAll(rooms);
        result.addAll(hashSet);
        return result;
    }

}
