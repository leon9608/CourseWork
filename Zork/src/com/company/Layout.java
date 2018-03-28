package com.company;

import java.util.Arrays;

/**
 * Created by Yuyang on 11/2/2017.
 */
public class Layout {
    private String initialRoom;
    private Room[] rooms;

    public Room[] getRooms() {
        return rooms;
    }

    public String getInitialRoom() {
        return initialRoom;
    }

    public boolean isInitialRoomValid() {
        for (Room room : rooms) {
            if (room.getName().equalsIgnoreCase(initialRoom)) {
                return true;
            }

        }
        return false;
    }

    public boolean containsRoom(String roomName) {
        for (Room room : rooms) {
            if (room.getName().equals(roomName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Layout layout = (Layout) other;
        return initialRoom.equals(layout.initialRoom) && Arrays.equals(rooms, layout.rooms);
    }


}
