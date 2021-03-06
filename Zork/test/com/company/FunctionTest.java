package com.company;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Yuyang on 13/2/2017.
 */
public class FunctionTest {
    private Layout siebelString;
    private Layout invalidSiebelMap;
    private Layout siebelFile;
    private Layout siebelUrl;
    private Layout siebelDefault;
    private Room siebelEastHallway;
    private Function helper = new Function();
    private ArrayList<String> onHands = new ArrayList<>();


    @Before
    public void setUp() {
        onHands.add("pencil");
        onHands.add("bottle");
        MagicGson magicgson = new MagicGson();
        Gson gson = new Gson();
        siebelString = gson.fromJson(testcase.VALID_SIEBEL_MAP,Layout.class);
        invalidSiebelMap = gson.fromJson(testcase.INVALID_SIEBEL_MAP,Layout.class);
        siebelEastHallway = gson.fromJson(testcase.siebelEastHallway,Room.class);
        String[] source = {"C:\\Users\\Yuyang\\IdeaProjects\\Zork\\src\\com\\company\\siebel.json"};
        siebelFile = magicgson.createMap(source);
        source[0] = "https://courses.engr.illinois.edu/cs126/resources/siebel.json";
        siebelUrl = magicgson.createMap(source);
        siebelDefault = magicgson.createMap(new String[0]);

    }

    @org.junit.Test
    public void getTargetDirection() throws Exception {
        String expectedDirection = "southwest";
        String instruction = "go southwest";
        assertEquals(expectedDirection, helper.getTargetDirection(instruction));

    }

    @org.junit.Test
    public void isValidExit() throws Exception {
        String instruction = "exit";
        assertTrue(helper.isValidExit(instruction));
        instruction = "QUIT";
        assertTrue(helper.isValidExit(instruction));
        instruction = "DoNe";
        assertTrue(helper.isValidExit(instruction));

        instruction = "go out";
        assertFalse(helper.isValidExit(instruction));
        instruction = "OUT";
        assertFalse(helper.isValidExit(instruction));
    }

    @org.junit.Test
    public void isValidInput() throws Exception {
        String ValidInstruction = "exit";
        assertTrue(helper.isValidInput(ValidInstruction,siebelEastHallway,onHands));

        ValidInstruction = "QUIT";
        assertTrue(helper.isValidInput(ValidInstruction,siebelEastHallway,onHands));

        ValidInstruction = "DoNe";
        assertTrue(helper.isValidInput(ValidInstruction,siebelEastHallway,onHands));

        ValidInstruction = "go west";
        assertTrue(helper.isValidInput(ValidInstruction,siebelEastHallway,onHands));

        ValidInstruction = "go SoUth";
        assertTrue(helper.isValidInput(ValidInstruction,siebelEastHallway,onHands));

        ValidInstruction = "go DOWN";
        assertTrue(helper.isValidInput(ValidInstruction,siebelEastHallway,onHands));

        ValidInstruction = "drop pencil";
        assertTrue(helper.isValidInput(ValidInstruction,siebelEastHallway,onHands));

        ValidInstruction = "drop bottle";
        assertTrue(helper.isValidInput(ValidInstruction,siebelEastHallway,onHands));

        ValidInstruction = "pick chair";
        assertTrue(helper.isValidInput(ValidInstruction,siebelEastHallway,onHands));

        String invalidInstruction = "go out";
        assertFalse(helper.isValidInput(invalidInstruction,siebelEastHallway,onHands));
        invalidInstruction = "OUT";
        assertFalse(helper.isValidInput(invalidInstruction,siebelEastHallway,onHands));
        invalidInstruction = "";
        assertFalse(helper.isValidInput(invalidInstruction,siebelEastHallway,onHands));
        invalidInstruction = "go outer-space lar";
        assertFalse(helper.isValidInput(invalidInstruction,siebelEastHallway,onHands));
        invalidInstruction = "do you like programming";
        assertFalse(helper.isValidInput(invalidInstruction,siebelEastHallway,onHands));
        invalidInstruction = "go north";
        assertFalse(helper.isValidInput(invalidInstruction,siebelEastHallway,onHands));
        invalidInstruction = "jump down";
        assertFalse(helper.isValidInput(invalidInstruction,siebelEastHallway,onHands));
        invalidInstruction = "drop box";
        assertFalse(helper.isValidInput(invalidInstruction,siebelEastHallway,onHands));
        invalidInstruction = "pick box";
        assertFalse(helper.isValidInput(invalidInstruction,siebelEastHallway,onHands));

    }

    @org.junit.Test
    public void isValidLayout() throws Exception {
        assertTrue(helper.isValidLayout(siebelString));
        assertTrue(helper.isValidLayout(siebelFile));
        assertTrue(helper.isValidLayout(siebelUrl));
        assertTrue(helper.isValidLayout(siebelDefault));
        assertFalse(helper.isValidLayout(invalidSiebelMap));
    }

    @Test
    public void displayOnHands() throws Exception {
        String expected = "You have pencil bottle on your hands.";
        assertEquals(expected,helper.displayOnHands(onHands));

        onHands.remove("bottle");
        expected = "You have pencil on your hands.";
        assertEquals(expected,helper.displayOnHands(onHands));

        onHands.remove("pencil");
        expected = "You have nothing on your hands.";
        assertEquals(expected,helper.displayOnHands(onHands));
    }
}