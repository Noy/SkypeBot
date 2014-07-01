package org.kodas.skypebot.command.commands.skypecommands;

import com.skype.Chat;
import com.skype.User;
import org.kodas.skypebot.command.SubCommand;

/**
 * Created by Noy on 10/05/2014.
 */
public final class BanCommand extends SubCommand {
    @Override
    public void onCommand(Chat chat, User sender, String[] args) throws Exception {
        if (sender.isAuthorized()) {
            for (String arg : args) {
                chat.send("/set banlist + " + arg);
            }
            sender.send("That user is now banned from the group.");
        }
    }
}
