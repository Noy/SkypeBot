package org.kodas.skypebot.utils;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Noy on 09/05/2014.
 */
public final class Googler {

    public static GoogleResults search(String type, String query) throws IOException {
        String address = "http://ajax.googleapis.com/ajax/services/search/"+type+"?v=1.0&q=";
        String charset = "UTF-8";
        URL url = new URL(address + URLEncoder.encode(query, charset));
        Reader reader = new InputStreamReader(url.openStream(), charset);
        GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);
        return results;
    }



}
