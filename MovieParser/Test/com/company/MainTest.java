package com.company;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * A comprehensive test class testing "Main" class
 * Created by Yuyang on 30/1/2017.
 */
public class MainTest {

    private Gson makeMyLifeEasy = new Gson();
    private ArrayList<Movie> testList = new ArrayList<>();
    private MovieCollection myCollection;

    @Before
    public void parseJSON() {
        myCollection = makeMyLifeEasy.fromJson(HardCodeTestcase.CollectionString, MovieCollection.class);
        testList.addAll(Arrays.asList(myCollection.getResults()));
    }

    @Test
    public void checkValidInput() throws Exception {
        String[] testArray1 = {"123","displayall"};
        assertTrue(Main.isValidInput(testArray1));

        String[] testArray2 = {"123","getspecificGenre","1" };
        assertTrue(Main.isValidInput(testArray2));

        String[] testArray3 = {"123","getvoteaverageabove","4.3" };
        assertTrue(Main.isValidInput(testArray3));

        String[] testArray4 = {"123","getpopularityabove","4.4" };
        assertTrue(Main.isValidInput(testArray4));
    }

    @Test
    public void checkInvalidInput() throws Exception {
        String[] testArray1 = null;
        assertFalse(Main.isValidInput(testArray1));

        String[] testArray2 = {"123" };
        assertFalse(Main.isValidInput(testArray2));

        String[] testArray3 = {"123","getvoteaverageabove","4.3","21r3" };
        assertFalse(Main.isValidInput(testArray3));

        String[] testArray4 = {"-10","displayall" };
        assertFalse(Main.isValidInput(testArray4));

        String[] testArray5 = {"10.5","displayall" };
        assertFalse(Main.isValidInput(testArray5));

        String[] testArray6 = {"grew","displayall" };
        assertFalse(Main.isValidInput(testArray6));

        String[] testArray7 = {"10","getsepcaf" };
        assertFalse(Main.isValidInput(testArray7));

        String[] testArray8 = {"10","getspecificgenre","-10" };
        assertFalse(Main.isValidInput(testArray8));

        String[] testArray9 = {"10","getspecificGenre","fqwe[" };
        assertFalse(Main.isValidInput(testArray9));

        String[] testArray10 = {"10","getvoteaverageabove","-10" };
        assertFalse(Main.isValidInput(testArray10));

        String[] testArray11 = {"10","getvoteaverageabove","10.1" };
        assertFalse(Main.isValidInput(testArray11));

        String[] testArray12 = {"10","getvoteaverageabove","rgsr" };
        assertFalse(Main.isValidInput(testArray12));

        String[] testArray13 = {"10","getpopularityabove","-10" };
        assertFalse(Main.isValidInput(testArray13));

        String[] testArray14 = {"10","feifjew","10" };
        assertFalse(Main.isValidInput(testArray14));
    }

    @Test
    public void checkGetAllMovies() throws Exception {
        String expectedAll = "1.The Secret Life of Pets\n" +
                "2.Moana\n" +
                "3.Suicide Squad\n" +
                "4.La La Land\n" +
                "5.Assassin's Creed\n" +
                "6.Finding Dory\n" +
                "7.Jurassic World\n" +
                "8.Miss Peregrine's Home for Peculiar Children\n" +
                "9.Fantastic Beasts and Where to Find Them\n" +
                "10.Interstellar\n" +
                "11.Captain America: Civil War\n" +
                "12.xXx: Return of Xander Cage\n" +
                "13.Passengers\n" +
                "14.Arrival\n" +
                "15.Mad Max: Fury Road\n" +
                "16.Rogue One: A Star Wars Story\n" +
                "17.Underworld: Blood Wars\n" +
                "18.The Girl on the Train\n";

        int numberRequired = 18;
        assertEquals(expectedAll, Main.getAllMovies(testList, numberRequired));

        expectedAll+= "19.Inferno\n" +
                "20.The Magnificent Seven\n";
        numberRequired = 20;
        assertEquals(expectedAll,Main.getAllMovies(testList, numberRequired));

    }

    @Test
    /*  1. There are more than enough satisfied movies in the current page.
        2. The number of satisfied movie in the current page just match the required number.
        3. There are not enough satisfied movies but no more page remained.
        4. There are not enough satisfied movies but there is still page remained.
        5. There is no satisfied movie found and no more page remained.
        6. There is no satisfied movie found but there is still page remained.
     */
    public void checkFindSpecificGenre() throws Exception {
        String expectedGenre = "1.Suicide Squad\n" +
                "2.Assassin's Creed\n" +
                "3.Jurassic World\n" +
                "4.Captain America: Civil War\n" +
                "5.xXx: Return of Xander Cage\n" +
                "6.Mad Max: Fury Road\n" +
                "7.Rogue One: A Star Wars Story\n" +
                "8.Underworld: Blood Wars\n" +
                "9.Inferno\n";
        int requiredID = 28;
        int numberRequired = 9;
        boolean pageRemaining = true;
        assertEquals(expectedGenre, Main.findSpecificGenre(testList, requiredID, numberRequired, pageRemaining));
        expectedGenre += "10.The Magnificent Seven\n";
        numberRequired = 10;
        assertEquals(expectedGenre, Main.findSpecificGenre(testList, requiredID, numberRequired, pageRemaining));
        numberRequired = 15;
        pageRemaining = false;
        assertEquals(expectedGenre, Main.findSpecificGenre(testList, requiredID, numberRequired, pageRemaining));
        pageRemaining = true;
        String expected = "";
        assertEquals(expected, Main.findSpecificGenre(testList, requiredID, numberRequired, pageRemaining));

        String expectedOutput = "No result found.\n";
        requiredID = 1;
        numberRequired = 10;
        pageRemaining = false;
        assertEquals(expectedOutput, Main.findSpecificGenre(testList, requiredID, numberRequired, pageRemaining));

        expectedOutput = "";
        requiredID = 1;
        numberRequired = 10;
        pageRemaining = true;
        assertEquals(expectedOutput, Main.findSpecificGenre(testList, requiredID, numberRequired, pageRemaining));
    }

    @Test
    /*  1. There are more than enough satisfied movies in the current page.
        2. The number of satisfied movie in the current page just match the required number.
        3. There are not enough satisfied movies but no more page remained.
        4. There are not enough satisfied movies but there is still page remained.
        5. There is no satisfied movie found and no more page remained.
     */
    public void checkFindVoteAve() throws Exception {
        String expectedAve = "1.La La Land\n" + "2.Interstellar\n";
        double requiredVote = 7.1;
        int numberRequired = 2;
        boolean pageRemaining = true;
        assertEquals(expectedAve, Main.findVoteAve(testList, requiredVote, numberRequired, pageRemaining));
        expectedAve += "3.Rogue One: A Star Wars Story\n";
        numberRequired = 3;
        assertEquals(expectedAve, Main.findVoteAve(testList, requiredVote, numberRequired, pageRemaining));
        pageRemaining = false;
        numberRequired = 5;
        assertEquals(expectedAve, Main.findVoteAve(testList, requiredVote, numberRequired, pageRemaining));
        String expected = "";
        pageRemaining = true;
        assertEquals(expected, Main.findVoteAve(testList, requiredVote, numberRequired, pageRemaining));

        expected = "No result found.\n";
        pageRemaining = false;
        requiredVote = 10.0;
        numberRequired = 5;
        assertEquals(expected, Main.findVoteAve(testList, requiredVote, numberRequired, pageRemaining));
    }

    @Test
    /*  1. There are more than enough satisfied movies in the current page.
        2. The number of satisfied movie in the current page just match the required number.
        3. There are not enough satisfied movies but no more page remained.
        4. There are not enough satisfied movies but there is still page remained.
        5. There is no satisfied movie found and no more page remained.
     */
    public void checkFindPopularity() throws Exception {
        String expectedPop = "1.The Secret Life of Pets\n" + "2.Moana\n" + "3.Suicide Squad\n";
        double requiredPop = 122.0;
        int numberRequired = 3;
        boolean pageRemaining = true;
        assertEquals(expectedPop, Main.findPopularity(testList, requiredPop, numberRequired, pageRemaining));
        numberRequired = 4;
        expectedPop += "4.La La Land\n";
        assertEquals(expectedPop, Main.findPopularity(testList, requiredPop, numberRequired, pageRemaining));
        pageRemaining = false;
        numberRequired = 10;
        assertEquals(expectedPop, Main.findPopularity(testList, requiredPop, numberRequired, pageRemaining));
        String expected = "";
        pageRemaining = true;
        assertEquals(expected, Main.findPopularity(testList, requiredPop, numberRequired, pageRemaining));

        expected = "No result found.\n";
        pageRemaining = false;
        numberRequired = 10;
        requiredPop = 400;
        assertEquals(expected, Main.findPopularity(testList, requiredPop, numberRequired, pageRemaining));
    }


    @Test
    public void checkGetTaskDone() throws Exception {
       int numberRequired = 10;
       String queryType = "displayall";
       double parameter = 0.0;
        String output = Main.getTaskDone(numberRequired,queryType,parameter);
        String[] outputArray = output.split("\n");
        int expectedNumber = 10;
        assertEquals(expectedNumber,outputArray.length);
    }

    @Test
    public void invalidPageNumber() throws Exception{
        MagicGson helper = new MagicGson("https://api.themoviedb.org/3/movie/popular?api_key=" + APIKEY.key);
        MovieCollection badCollection = helper.jsonToMovieCollection(-100);
        assertNull(badCollection);
    }
}