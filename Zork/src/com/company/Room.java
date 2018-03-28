package com.company;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Yuyang on 11/2/2017.
 */
public class Room {

    private String name;
    private String description;
    private Direction[] directions;
    private ArrayList<String> objects;

    public Direction[] getDirections() {
        return directions;
    }

    public String getName() {
        return name;
    }

    /**
     * @param targetDirection the direction that the user want to go
     * @return the room name for the corresponding direction
     */
    public String getNextRoom(String targetDirection) {
        for (Direction directionOption : directions) {
            if (targetDirection.equalsIgnoreCase(directionOption.getDirection()))
                return directionOption.getRoom();
        }
        return null;
    }

    public boolean haveAccessTo(String targetDirection) {
        for (Direction directionOption : this.directions) {
            if (targetDirection.equalsIgnoreCase(directionOption.getDirection()))
                return true;
        }
        return false;
    }

    public String getFullInstruction() {
        String output = description + "\nFrom here, you can go: ";
        int directionOption = directions.length;
        if (directionOption == 1) {
            output += directions[0].getDirection();
        } else {
            output += getDirectionLine(directionOption);
        }
        output += "\nIn the room, there is ";
        if (objects == null || objects.isEmpty()) {
            output += "nothing.";
        } else {
            for (String goods : objects) {
                output += goods + " ";
            }
        }
        return output;
    }

    /**
     * return the formatted direction option line
     *
     * @param count the number of direction options left
     * @return a comma and space separated list of directions with the word " or " between
     * the last and second to last directions if there are two or more directions
     */
    public String getDirectionLine(int count) {
        if (count == 1) {
            return "or " + directions[directions.length - count].getDirection();
        }
        return directions[directions.length - count].getDirection() + " " + getDirectionLine(count - 1);
    }

    public boolean haveAccessRoom(Room other) {
        for (Direction direction : directions) {
            if (direction.getRoom().equals(other.getName())) {
                return true;
            }
        }
        return false;
    }

    public boolean containsObject(String goods) {
        return objects.contains(goods);
    }

    public void addObject(String goods) {
        objects.add(goods);
    }

    public void removeObject(String goods) {
        objects.remove(goods);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Room room = (Room) other;
        return name.equals(room.name) && Arrays.equals(directions, room.directions) && description.equals(room.description);
    }

}
