package com.company;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
/** Converting online data source to local objects
 * Created by Yuyang on 6/2/2017.
 */
public class MagicGson {

    private String sourceUrl;

    MagicGson(String source){
        sourceUrl = source;
    }

   public MovieCollection jsonToMovieCollection(int pageNumber) {
       try {
           URL url = new URL(sourceUrl+"&page="+ pageNumber);
           InputStream inStream = url.openStream();
           InputStreamReader reader = new InputStreamReader(inStream, Charset.forName("UTF-8"));
           JsonReader jsonReader = new JsonReader(reader);
           Gson makeMyLifeEasy = new Gson();
           return makeMyLifeEasy.fromJson(jsonReader, MovieCollection.class);
        } catch(MalformedURLException e) {
            e.printStackTrace();
           return null;
        } catch (IOException e) {
            e.printStackTrace();
           return null;
         }

    }
}
