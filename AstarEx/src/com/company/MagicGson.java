package com.company;


import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Yuyang on 19/2/2017.
 */
public class MagicGson {
    protected static Grid createGrid(String source) {
        if (source.indexOf("https://") == 0) {
            return urlToGird(source);
        } else if (source.indexOf(".json") > 0) {
            return fileToGrid(source);
        } else {
            System.out.println("Invalid Url or file path. Please re-enter with a proper format.");
            return null;
        }
    }

    protected static Grid urlToGird(String address) {
        // catch invalid url;
        try {
            URL url = new URL(address);
            InputStream inStream = url.openStream();
            InputStreamReader reader = new InputStreamReader(inStream, Charset.forName("UTF-8"));
            JsonReader jsonReader = new JsonReader(reader);
            return jsonToGrid(jsonReader);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("Something wrong with the url given.");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something wrong with the url given.");
            return null;

        }
    }

    protected static Grid fileToGrid(String path) {
        try {
            JsonReader reader = new JsonReader(new FileReader(path));
            // String stringRepresentation = new String(Files.readAllBytes(Paths.get(path)), Charset.forName("UTF-8"));
            return jsonToGrid(reader);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something wrong with the path given.");
            return null;
        }
    }

    protected static Grid jsonToGrid(JsonReader jsonReader) {
        Gson makeMyLifeEasy = new Gson();
        return makeMyLifeEasy.fromJson(jsonReader, Grid.class);
    }

    protected static Graph createGraph(String source) {
        if (source.indexOf("https://") == 0) {
            return urlToGraph(source);
        } else if (source.indexOf(".json") > 0) {
            return fileToGraph(source);
        } else {
            System.out.println("Invalid Url or file path. Please re-enter with a proper format.");
            return null;
        }
    }

    protected static Graph urlToGraph(String address) {
        // catch invalid url;
        try {
            URL url = new URL(address);
            InputStream inStream = url.openStream();
            InputStreamReader reader = new InputStreamReader(inStream, Charset.forName("UTF-8"));
            JsonReader jsonReader = new JsonReader(reader);
            return jsonToGraph(jsonReader);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("Something wrong with the url given.");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something wrong with the url given.");
            return null;

        }
    }

    protected static Graph fileToGraph(String path) {
        try {
            JsonReader reader = new JsonReader(new FileReader(path));
            return jsonToGraph(reader);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something wrong with the path given.");
            return null;
        }
    }

    protected static Graph jsonToGraph(JsonReader jsonReader) {
        Gson makeMyLifeEasy = new Gson();
        return makeMyLifeEasy.fromJson(jsonReader, Graph.class);
    }

}