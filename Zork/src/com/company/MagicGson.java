package com.company;


import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Yuyang on 11/2/2017.
 */
public class MagicGson {
    private static final String SIEBEL_MAP = "https://courses.engr.illinois.edu/cs126/resources/siebel.json";

    protected Layout createMap(String[] source) {
        if (source == null || source.length == 0) {
            return defaultToLayout();
        } else if (source[0].indexOf("https://") == 0) {
            return urlToLayout(source[0]);
        } else if (source[0].indexOf(".json") > 0) {
            return fileToLayout(source[0]);
        } else {
            System.out.println("Invalid Url or file path. Please re-enter with a proper format.");
            return null;
        }
    }

    protected Layout defaultToLayout() {
        // catch invalid url;
        try {
            URL url = new URL(SIEBEL_MAP);
            InputStream inStream = url.openStream();
            InputStreamReader reader = new InputStreamReader(inStream, Charset.forName("UTF-8"));
            JsonReader jsonReader = new JsonReader(reader);
            return jsonToLayout(jsonReader);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
    }

    protected Layout urlToLayout(String address) {
        // catch invalid url;
        try {

            URL url = new URL(address);
            InputStream inStream = url.openStream();
            InputStreamReader reader = new InputStreamReader(inStream, Charset.forName("UTF-8"));
            JsonReader jsonReader = new JsonReader(reader);
            return jsonToLayout(jsonReader);

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

    protected Layout fileToLayout(String path) {
        try {
            String stringRepresentation = new String(Files.readAllBytes(Paths.get(path)), Charset.forName("UTF-8"));
            return jsonToLayout(stringRepresentation);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something wrong with the path given.");
            return null;
        }
    }

    protected Layout jsonToLayout(JsonReader jsonReader) {
        Gson makeMyLifeEasy = new Gson();
        return makeMyLifeEasy.fromJson(jsonReader, Layout.class);
    }

    protected Layout jsonToLayout(String jasonString) {
        Gson makeMyLifeEasy = new Gson();
        return makeMyLifeEasy.fromJson(jasonString, Layout.class);
    }

    protected Room jsonToRoom(String jasonString) {
        Gson makeMyLifeEasy = new Gson();
        return makeMyLifeEasy.fromJson(jasonString, Room.class);
    }

}