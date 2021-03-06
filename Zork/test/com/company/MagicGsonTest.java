package com.company;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Yuyang on 14/2/2017.
 */
public class MagicGsonTest {
    private Layout siebelString;
    private Layout siebelFile;
    private Layout siebelUrl;
    private Layout siebelDefault;
    @Before
    public void setUpLayout() {
        MagicGson magicgson = new MagicGson();
        Gson gson = new Gson();
        siebelString = gson.fromJson(testcase.VALID_SIEBEL_MAP,Layout.class);
        String[] source = {"C:\\Users\\Yuyang\\IdeaProjects\\Zork\\src\\com\\company\\siebel.json"};
        siebelFile = magicgson.createMap(source);
        source[0] = "https://courses.engr.illinois.edu/cs126/resources/siebel.json";
        siebelUrl = magicgson.createMap(source);
        siebelDefault = magicgson.createMap(new String[0]);

    }

    @Test
    public void invalidCreateMap() throws Exception {
        MagicGson magicgson = new MagicGson();
        String[] source = {"http://helloworld.json"};
        assertNull(magicgson.createMap(source));
        source[0] = "https://cs126Rocks.com";
        assertNull(magicgson.createMap(source));
        source[0] = "C:\\Users\\Yuyang\\IdeaProjects\\siebel.json";
        assertNull(magicgson.createMap(source));
    }

    @Test
    public void isIdentical() throws Exception{
        assertTrue(siebelFile.getRooms()[5].equals(siebelString.getRooms()[5]));
        assertTrue(siebelUrl.getRooms()[5].equals(siebelString.getRooms()[5]));
        assertTrue(siebelFile.getRooms()[5].equals(siebelUrl.getRooms()[5]));
        assertTrue(siebelDefault.getRooms()[5].equals(siebelUrl.getRooms()[5]));
        assertTrue(siebelDefault.getRooms()[5].equals(siebelString.getRooms()[5]));
        assertFalse(siebelFile.getRooms()[0].equals(siebelUrl.getRooms()[5]));

        assertTrue(siebelFile.equals(siebelString));
        assertTrue(siebelFile.equals(siebelUrl));
        assertTrue(siebelUrl.equals(siebelString));
        assertTrue(siebelFile.equals(siebelDefault));
    }
}