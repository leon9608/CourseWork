package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Yuyang on 13/2/2017.
 */
public class RoomTest {
    private Room room1314;
    private Room room1102;
    private Room siebelEastHallway;


    @Before
    public void setUpRoom() {
        MagicGson gson = new MagicGson();
        room1314 = gson.jsonToRoom(testcase.room1314);
        room1102 = gson.jsonToRoom(testcase.room1102);
        siebelEastHallway = gson.jsonToRoom(testcase.siebelEastHallway);
    }

    @Test
    public void getNextRoom() throws Exception {
        String targetDirection = "nOrTh";
        String expectedRoom = "SiebelEastHallway";
        assertEquals(expectedRoom, room1314.getNextRoom(targetDirection));
        targetDirection = "NORTH";
        assertEquals(expectedRoom, room1314.getNextRoom(targetDirection));

        targetDirection = "sOurth";
        assertNull(room1314.getNextRoom(targetDirection));
        targetDirection = "heaven";
        assertNull(room1314.getNextRoom(targetDirection));
    }

    @Test
    public void haveAccess() throws Exception {
        assertEquals(true, room1314.haveAccessTo("North"));
        assertEquals(true, room1102.haveAccessTo("wEst"));
        assertEquals(true, siebelEastHallway.haveAccessTo("down"));

        assertEquals(false, room1314.haveAccessTo("south"));
        assertEquals(false, room1102.haveAccessTo("East"));
        assertEquals(false, siebelEastHallway.haveAccessTo("north"));
        assertEquals(false, siebelEastHallway.haveAccessTo("south east"));
    }

    @Test
    public void getName() throws Exception {
        String expectedName = "Siebel1314";
        assertEquals(expectedName, room1314.getName());

        expectedName = "Siebel1112";
        assertEquals(expectedName, room1102.getName());

        expectedName = "SiebelEastHallway";
        assertEquals(expectedName, siebelEastHallway.getName());
    }

    @Test
    public void getDirectionLine() throws Exception {
        String expectedDirectionLine = "West South or Down";
        assertEquals(expectedDirectionLine, siebelEastHallway.getDirectionLine(3));

        expectedDirectionLine = "or West";
        assertEquals(expectedDirectionLine, room1102.getDirectionLine(1));
    }

    @Test
    public void containsRoom() throws Exception {
        assertTrue(siebelEastHallway.haveAccessRoom(room1314));
        assertTrue(room1314.haveAccessRoom(siebelEastHallway));

        assertFalse(room1102.haveAccessRoom(siebelEastHallway));
        assertFalse(room1102.haveAccessRoom(room1314));
        assertFalse(room1314.haveAccessRoom(room1102));
    }

}