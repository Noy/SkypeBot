package org.kodas.skypebot.utils;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by Noy on 09/05/2014.
 */
@Data
@ToString
public final class GoogleResults {
    private ResponseData responseData;

    @Data
    @ToString
    public static class ResponseData {
        private List<Result> results;
    }

    @Data
    @ToString
    public static class Result {
        private String url;
        private String title;
    }
}
