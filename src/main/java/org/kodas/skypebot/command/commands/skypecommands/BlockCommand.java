package org.kodas.skypebot.command.commands.skypecommands;

import com.skype.Chat;
import com.skype.Skype;
import com.skype.SkypeException;
import com.skype.User;
import org.kodas.skypebot.command.SubCommand;
import org.kodas.skypebot.utils.Permissions;

/**
 * Created by Noy on 10/05/2014.
 */
public final class BlockCommand extends SubCommand {

    @Override
    public void onCommand(Chat chat, User sender, String[] args) throws Exception {
        if (args.length == 0) {
            chat.send("Usage: !block <user>");
            return;
        }
        if (Permissions.hasPermission(sender, chat, "block")) {
            block(args[0]);
            chat.send("Successfully blocked " + args[0]);
        } else {
            chat.send("You don't have permission! :P");
        }
    }

    private void block(String arg) throws SkypeException {
        User user = Skype.getUser(arg);
        user.send("You have been blocked by the SkypeBot, talk to the owner about getting unblocked.");
        user.setBlocked(true);
    }
}
