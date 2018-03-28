package com.company;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Help to proceed the instructions given by the user and also check the validity of map
 * Created by Yuyang on 12/2/2017.
 */
public class Function {

    /**
     * Convert the instruction to the direction
     *
     * @param instruction the instruction given by the user
     * @return the direction in the instruction
     */
    protected String getTargetDirection(String instruction) {
        String[] instructionArray = instruction.split(" ");
        return instructionArray[1];
    }

    protected String displayOnHands(ArrayList<String> onHands) {
        String output = "You have ";
        if (onHands.isEmpty()) {
            output += "nothing ";
        } else {
            for (String goods : onHands) {
                output += goods + " ";
            }
        }
        return output + "on your hands.";
    }


    /**
     * To check whether the instruction for exit is valid
     *
     * @param instruction the instruction given by the user
     * @return true if the instruction given match the valid exit parameters;
     * false if the given instruction is invalid to quit
     */
    protected boolean isValidExit(String instruction) {

        return instruction.equalsIgnoreCase("exit")
                || instruction.equalsIgnoreCase("quit")
                || instruction.equalsIgnoreCase("done");
    }

    /**
     * To check whether the instruction given by the user is valid and hence the adventure can be processed to next move
     *
     * @param instruction the instruction given by the user
     * @param currentRoom the room the person is in now and provide the valid direction options
     * @param onHands     the String array stores the objects carried by player
     * @return true if the instruction given by the user can be identified to proceed the adventure or terminate
     */
    protected boolean isValidInput(String instruction, Room currentRoom, ArrayList<String> onHands) {
        String[] instructionArray = instruction.split(" ");

        // find invalid input and print appropriate response
        try {
            if (instructionArray.length == 0 || instructionArray.length > 2) {
                throw new Exception("Please enter a valid instruction.");
            }
            if (instructionArray.length == 1) {
                if (isValidExit(instructionArray[0])) {
                    return true;
                } else {
                    throw new Exception("Please enter a valid instruction.");
                }
            }
            switch (instructionArray[0].toLowerCase()) {
                case "go":
                    if (currentRoom.haveAccessTo(instructionArray[1])) {
                        return true;
                    } else {
                        throw new Exception("I can't " + instruction);
                    }

                case "pick":
                    if (currentRoom.containsObject(instructionArray[1])) {
                        return true;
                    } else {
                        throw new Exception("The room does not contain " + instructionArray[1]);
                    }

                case "drop":
                    if (onHands.contains(instructionArray[1])) {
                        return true;
                    } else {
                        throw new Exception("You do not have " + instructionArray[1] + " on your hand");
                    }
                default:
                    throw new Exception("I don't understand " + instruction);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Process the instruction given by the user and make modifications accordingly
     *
     * @param instruction the String input by the user
     * @param currentRoom the Room that the user is in now
     * @param onHands     the String array stores the objects carried by player
     * @param map         the hashmap connecting the String of room name and the Room object
     * @return the current room which may have been modified or the room that user is going to enter
     */
    protected Room processTheOrder(String instruction, Room currentRoom, ArrayList<String> onHands, HashMap<String, Room> map) {
        if (isValidInput(instruction, currentRoom, onHands)) {
            if (isValidExit(instruction)) {
                return null;
            } else {
                String[] instructionArray = instruction.split(" ");
                switch (instructionArray[0].toLowerCase()) {
                    case "go":
                        String targetDirection = getTargetDirection(instruction);
                        return map.get(currentRoom.getNextRoom(targetDirection));

                    case "pick":
                        currentRoom.removeObject(instructionArray[1]);
                        onHands.add(instructionArray[1]);
                        break;

                    case "drop":
                        onHands.remove(instructionArray[1]);
                        currentRoom.addObject(instructionArray[1]);
                        break;
                }
            }
        }
        return currentRoom;
    }

    /**
     * To check whether the layout is valid. For every room,If room A have the access to room B ,B must exist in the layout.
     * if room A have the direct access to room B, then room B must have the direct access to room A.
     *
     * @param testLayout the layout given by the user
     * @return ture if it is a valid layout ;
     * false if it is an invalid layout.
     */
    protected boolean isValidLayout(Layout testLayout) {
        Room[] testrooms = testLayout.getRooms();
        if (!testLayout.isInitialRoomValid()) {
            return false;
        }

        // check the room that can be accessed by A exist in the layout
        for (Room initialRoom : testrooms) {
            for (Direction direction : initialRoom.getDirections()) {
                if (!testLayout.containsRoom(direction.getRoom())) {
                    return false;
                }
            }
        }
        // check if A have access to B ,then B have access to A
        for (Room initialRoom : testrooms) {
            for (Room targetRoom : testrooms) {
                if (initialRoom.haveAccessRoom(targetRoom) &&
                        !targetRoom.haveAccessRoom(initialRoom)) {
                    return false;
                }
            }
        }
        return true;
    }
}
