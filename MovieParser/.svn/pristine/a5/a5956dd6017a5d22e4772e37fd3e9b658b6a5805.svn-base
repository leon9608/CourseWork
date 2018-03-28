package com.company;

import org.junit.Test;
import com.google.gson.Gson;

import static org.junit.Assert.*;

/**
 * Created by Yuyang on 31/1/2017.
 */
public class MovieCollectionTest {

    private MovieCollection myCollection = HardCodeTestcase.myCollection;

    @Test
    public void getPage() throws Exception {
        int expectedPage = 1;
        assertEquals(expectedPage, myCollection.getPage());
    }

    @Test
    public void getTotal_result() throws Exception {
        int expectedResult = 19652;
        assertEquals(expectedResult, myCollection.getTotal_results());
    }

    @Test
    public void getTotal_pages() throws Exception {
        int expectedTotalPage = 983;
        assertEquals(expectedTotalPage, myCollection.getTotal_pages());
    }

    @Test
    public void getResults() throws Exception {
        int expectedNumber = 20;
        assertEquals(expectedNumber, myCollection.getResults().length);
        assertTrue( HardCodeTestcase.newMovie.equals( myCollection.getResults()[0] ) );

    }

}