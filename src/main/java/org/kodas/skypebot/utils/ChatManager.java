package org.kodas.skypebot.utils;

import com.skype.Chat;
import com.skype.SkypeException;
import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Noy on 09/05/2014.
 */
@Data
public final class ChatManager {

    private static ConcurrentHashMap<Chat, ArrayList<String>> chats = new ConcurrentHashMap<>();

    private static Integer paste = 6;
    private static Integer seconds = 5;

    public static void printThrowable(Chat chat, Throwable t){
        ChatManager.chat(chat, t.toString());
        for (StackTraceElement element : t.getStackTrace())
             ChatManager.chat(chat, "\t" + element.toString());

        Throwable e1 = t.getCause();
        if (e1 != null){
            ChatManager.chat(chat, e1.toString());
            for (StackTraceElement el : e1.getStackTrace())
                 ChatManager.chat(chat, "\t" + el.toString());
        }
        for (Integer a  = 0; a < paste; a++){
             chat(chat, "");
        }
    }


    public static void chat(Chat chat, String msg){
        ArrayList<String> msgs = chats.get(chat);
        if(msgs == null){
            msgs = new ArrayList<>();
        }
        msgs.add(msg);
        chats.put(chat, msgs);
    }


    public static void start() {
        new Thread() {
            @Override
            public void run(){
                while(true) {
                    try{
                        HashMap<Chat, ArrayList<String>> chats_copy = new HashMap<>(chats);
                        for (Chat chat : chats_copy.keySet()){
                             try{
                                 StringBuilder sb = new StringBuilder();
                                 for(String msg : chats.get(chat)){
                                     sb.append(msg).append("\n");
                                 }
                                 sb.delete(sb.length() - 1, sb.length());
                                 if (chats.remove(chat).size() > paste){
                                     chat.send("Output: " + createPaste(sb.toString()));
                                 } else {
                                     try {
                                         chat.send(sb.toString());
                                     } catch (SkypeException e) {
                                         printThrowable(chat, e);
                                     }
                                 }
                            } catch (Exception e){
                                printThrowable(chat, e);
                            }
                        }
                        sleep(seconds * 1000);
                    }catch (Exception ignored){}
                }
            }
        }.start();
    }

    public static String createPaste(final String msg) throws Exception{
        String paste = "files/paste"+System.currentTimeMillis();
        File file1 = new File(paste);
        {
            PrintWriter pw = new PrintWriter(new FileWriter(file1));
            pw.println(msg);
            pw.flush();
            pw.close();
        }
        String script = "files/script" + System.currentTimeMillis();
        File file2 = new File(script);
        {
            PrintWriter pw1 = new PrintWriter(new FileWriter(file2));
            pw1.println("cat "+paste+" | pastebinit -a \"Bot Output\"");
            pw1.flush();
            pw1.close();
        }

        Process proc = Runtime.getRuntime().exec("bash "+script);
        String line = "";
        file2.deleteOnExit();
        file2.deleteOnExit();
        BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        while ((line = in.readLine()) != null) {
            return line;
        }
        return line;
    }
}