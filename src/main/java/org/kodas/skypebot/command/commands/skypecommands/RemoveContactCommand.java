package org.kodas.skypebot.command.commands.skypecommands;

import com.skype.*;
import org.kodas.skypebot.command.SubCommand;
import org.kodas.skypebot.utils.Permissions;

/**
 * Created by Noy on 16/05/2014.
 */
public final class RemoveContactCommand extends SubCommand {

    private String s;

    @Override
    public void onCommand(Chat chat, User sender, String[] args) throws Exception {
        if (args.length == 0) {
            chat.send("Usage: !removecontact <user>");
            return;
        }
        if (Permissions.hasPermission(sender, chat, "block")) {
            remove(args[0]);
            chat.send("Successfully removed " + args[0]);
        } else {
            chat.send("You don't have permission! :P");
        }
        remove(s);
    }

    private void remove(String arg) throws SkypeException {
        Friend friend = (Friend) Skype.getUser(arg);
        Skype.getContactList().removeFriend(friend);
    }
}