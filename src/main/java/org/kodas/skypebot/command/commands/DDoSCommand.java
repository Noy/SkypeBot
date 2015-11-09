package org.kodas.skypebot.command.commands;

import com.skype.Chat;
import com.skype.SkypeException;
import com.skype.User;
import org.kodas.skypebot.command.SubCommand;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Noy on 04/06/2014.
 */
public final class DDoSCommand extends SubCommand { // this isn't real...
    @Override
    public void onCommand(final Chat chat, User sender, final String[] args) throws Exception {
        if (args.length == 0) {
            chat.send("Use !ddos <target>");
            return;
        }
        chat.send("Hitting.");
        Timer timer = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                String s = args[0];
                if (!s.contains(".")) {
                    try {
                        chat.send("Could not hit! Not a real target!");
                        return;
                    } catch (SkypeException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                if (s.equals(args[0])) {
                    System.out.println("doing the scheduled task");
                    try {
                        chat.send("Hitting " + s + " with (venet0:0 111.111.32.3): NO FLAGS are set, 40 headers + 0 data bytes");
                    } catch (SkypeException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        timer.schedule(tt, 1000, 1000 * 2); // delay the task 1 second, and then run task every two seconds
    }
}