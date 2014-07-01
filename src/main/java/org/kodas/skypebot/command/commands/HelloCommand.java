package org.kodas.skypebot.command.commands;

import com.skype.Chat;
import com.skype.SkypeException;
import com.skype.User;
import org.kodas.skypebot.command.SubCommand;

import java.util.Random;

/**
 * Created by Noy on 09/05/2014.
 */
public final class HelloCommand extends SubCommand {

    private final String[] msgs = {"Hi,", "Hello,", "Hey,", "Yo,", "Sup,", "Hi there,", "Greetings,", "G'Day,"};

    @Override
    public void onCommand(Chat chat, User sender, String[] args) throws SkypeException {
        chat.send(msgs[new Random().nextInt(msgs.length)] + " " + sender.getFullName() + ".");
    }
}