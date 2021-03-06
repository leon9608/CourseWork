package com.company;


import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        TextIO.putln("Hello. Welcome to the Zork. You will start your adventure.");
        runTheGame(args);
    }

    private static void runTheGame(String[] args) {
        MagicGson makeLifeEasy = new MagicGson();
        Layout siebel = makeLifeEasy.createMap(args);

        if (siebel != null) {
            HashMap<String, Room> siebelMap = new HashMap<>();
            for (Room room : siebel.getRooms()) {
                siebelMap.put(room.getName(), room);
            }

            Room currentLocation = siebelMap.get(siebel.getInitialRoom());
            Function helper = new Function();
            boolean isValidLayout = helper.isValidLayout(siebel);
            ArrayList<String> onHands = new ArrayList<>();
            while (isValidLayout) {
                System.out.println(currentLocation.getFullInstruction());
                System.out.println(helper.displayOnHands(onHands));
                TextIO.skipBlanks();
                String instruction = TextIO.getln();
                currentLocation = helper.processTheOrder(instruction, currentLocation, onHands, siebelMap);
                if (currentLocation == null) {
                    break;
                }
            }
            System.out.println("Bye bye!");
        }
    }


}

