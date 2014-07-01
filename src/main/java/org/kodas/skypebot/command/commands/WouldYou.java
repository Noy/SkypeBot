package org.kodas.skypebot.command.commands;

import com.skype.Chat;
import com.skype.User;
import org.kodas.skypebot.command.SubCommand;

import java.util.Random;

/**
 * Created by Noy on 28/05/2014.
 */
public final class WouldYou extends SubCommand {

    private String[] msg = {"Yes", "No", "Of course", "Ew, no", "(puke)", "YES", "Oh my god yes", "I wish", "Uh, no", "/me cringes"};

    @Override
    public void onCommand(Chat chat, User sender, String[] args) throws Exception {
        chat.send(msg[new Random().nextInt(msg.length)] + ".");
    }
}