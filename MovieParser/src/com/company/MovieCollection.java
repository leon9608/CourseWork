package com.company;

import java.util.ArrayList;

/**
 * Created by Yuyang on 30/1/2017.
 */
public class MovieCollection {
    private int page;
    private int total_results;
    private int total_pages;
    private Movie[] results;


    public int getPage() {
        return page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public Movie[] getResults() {
        return results;
    }

}


