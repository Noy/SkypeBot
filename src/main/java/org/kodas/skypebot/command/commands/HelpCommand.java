package org.kodas.skypebot.command.commands;

import com.skype.Chat;
import com.skype.User;
import org.kodas.skypebot.command.SubCommand;

/**
 * Created by Noy on 10/05/2014.
 */
public final class HelpCommand extends SubCommand {
    @Override
    public void onCommand(Chat chat, User sender, String[] args) throws Exception {
        chat.send("---Introduction---");
        chat.send("SkypeBot Version 1.0 Made by MatorSenty");
        chat.send("Every Command starts with a '!'");
        chat.send("Some commands are: +hi, +kick, +ban, +kickban, !topic and others. ");
        chat.send("For all commands see this pastebin link: https://gist.github.com/NoyHillel/f5da9d6242fc1b21a933 ");
        chat.send("More commands/features will be added ASAP!");
        chat.send("Enjoy!");
    }
}
