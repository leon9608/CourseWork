package com.company;

import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Yuyang on 30/1/2017.
 */
public class MovieTest {

    Movie newMovie = HardCodeTestcase.newMovie;

    @Test
    public void getPoster_path() throws Exception {
        String expected = "/WLQN5aiQG8wc9SeKwixW7pAR8K.jpg";
        assertEquals(expected,newMovie.getPoster_path());
    }

    @Test
    public void getAdult() throws Exception {
        boolean expected = false;
        assertEquals(expected,newMovie.getAdult());
    }

    @Test
    public void getOverview() throws Exception {
        String expected = "The quiet life of a terrier named Max is upended when his owner takes in Duke, a stray whom Max instantly dislikes.";
        assertEquals(expected, newMovie.getOverview());
    }

    @Test
    public void getRelease_date() throws Exception {
        assertEquals("2016-06-18", newMovie.getRelease_date());
    }

    @Test
    public void getGenre_ids() throws Exception {
     int[] expected = {12,16,35,10751};
        assertTrue(expected[0] == newMovie.getGenre_ids()[0]);
        assertTrue(expected[1] == newMovie.getGenre_ids()[1]);
        assertTrue(expected[2] == newMovie.getGenre_ids()[2]);
        assertTrue(expected[3] == newMovie.getGenre_ids()[3]);
    }

    @Test
    public void getId() throws Exception {
        int expected = 328111;
        assertEquals(expected, newMovie.getId());
    }

    @Test
    public void getOriginal_title() throws Exception {
        String expected = "The Secret Life of Pets";
        assertEquals(expected,newMovie.getOriginal_title());
    }

    @Test
    public void getOriginal_language() throws Exception {
        String expected = "en";
        assertEquals(expected,newMovie.getOriginal_language());
    }

    @Test
    public void getTitle() throws Exception {
        String expected = "The Secret Life of Pets";
        assertEquals(expected,newMovie.getTitle());
    }

    @Test
    public void getBackdrop_path() throws Exception {
        String expected = "/lubzBMQLLmG88CLQ4F3TxZr2Q7N.jpg";
        assertEquals(expected,newMovie.getBackdrop_path());
    }

    @Test
    public void getPopularity() throws Exception {
        double expected = 289.434746;
        assertTrue( expected == newMovie.getPopularity());
    }

    @Test
    public void getVote_count() throws Exception {
        int expected = 1922;
        assertEquals(expected,newMovie.getVote_count());
    }

    @Test
    public void getVideo() throws Exception {
        boolean expected = false;
        assertEquals(expected,newMovie.getVideo());
    }

    @Test
    public void getVote_average() throws Exception {
        double expected = 5.8;
        assertTrue(expected == newMovie.getVote_average());
    }

    @Test
    public void equals() throws Exception {
        assertTrue(newMovie.equals(newMovie));
    }
}