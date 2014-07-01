package org.kodas.skypebot.utils;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
/**
 * Created by Noy on 09/05/2014.
 */
@Data
public final class Arguments {

    @Getter(AccessLevel.PUBLIC) private HashMap<String, String> values = new HashMap<>();
    @Getter(AccessLevel.PUBLIC) private String[] args;

    public Arguments(String input[], String ... args){
        ArrayList<String> list = new ArrayList<>(Arrays.asList(input));

        for (String arg : args){
             String split[] = arg.split(" ");
             List<String> swi = Arrays.asList(split[0].split("/"));
             int index = -1;
             Integer a = 0;
             for (String str : list){
                 if (swi.contains(str.replace("-", ""))){
                     index = a;
                     break;
                 }
                 a++;
             }
             if (index != -1){
                 if (split.length == 1){
                     values.put(swi.get(0), null);
                     list.remove(index);
                 } else {
                     list.remove(index);
                     values.put(swi.get(0), list.remove(index));
                }
            }
        }
        this.args = list.toArray(new String[0]);
    }
}
