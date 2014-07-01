package org.kodas.skypebot.command.commands.skypecommands;

import com.skype.Chat;
import com.skype.User;
import org.kodas.skypebot.command.SubCommand;

/**
 * Created by Noy on 09/05/2014.
 */
public final class KickCommand extends SubCommand {
    @Override
    public void onCommand(Chat chat, User sender, String[] args) throws Exception {
        if (args.length == 0) {
            chat.send("Usage: !kick <user>");
            return;
        }
        if (sender.isAuthorized()) {
            for (String arg : args) {
                chat.send("/kick " + arg);
            }
            chat.send("User Kicked.");
        }
    }
}
