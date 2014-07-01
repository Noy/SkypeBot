package org.kodas.skypebot.internet;

import com.google.gson.Gson;
import org.kodas.skypebot.utils.WebClient;

/**
 * Created by Noy on 09/05/2014.
 */
public final class Google {

    public static GoogleResult search(String type, String search, Integer start) throws Exception{
        String url = "http://ajax.googleapis.com/ajax/services/search/"+type+"?v=2.0&safe=off&q="+search+"&start="+start;
        String json = WebClient.request(url);
        return new Gson().fromJson(json, GoogleResult.class);
    }

    public final class GoogleResult{
        public ResponseData responseData;
    }
    public final class ResponseData{
        public Results[] results;
    }
    public final class Results{
        public String cacheUrl;
        public String content;
        public String title;
        public String titleNoFormatting;
        public String unescapedUrl;
        public String url;
        public String visibleUrl;
    }
}
