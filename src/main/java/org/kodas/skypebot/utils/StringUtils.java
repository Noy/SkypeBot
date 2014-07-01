package org.kodas.skypebot.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Noy on 09/05/2014.
 */
public final class StringUtils {

    public static String implode(String ... strs){
        StringBuilder sb = new StringBuilder();
        for(String str : strs){
            sb.append(str).append(" ");
        }
        return sb.toString().trim();
    }

    public static String implode(Integer start, String ... strs){
        List<String> list = new ArrayList<>(Arrays.asList(strs));
        for(Integer a = 0; a < start; a++){
            list.remove(0);
        }
        return implode(list.toArray(new String[0]));
    }


}
