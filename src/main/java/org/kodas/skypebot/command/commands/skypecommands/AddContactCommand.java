package org.kodas.skypebot.command.commands.skypecommands;

import com.skype.Chat;
import com.skype.Skype;
import com.skype.SkypeException;
import com.skype.User;
import org.kodas.skypebot.command.SubCommand;
import org.kodas.skypebot.utils.Permissions;

/**
 * Created by Noy on 16/05/2014.
 */
public final class AddContactCommand extends SubCommand {

    private String s;

    @Override
    public void onCommand(Chat chat, User sender, String[] args) throws Exception {
        if (args.length == 0) {
            chat.send("Usage: !addcontact <user>");
            return;
        }
        if (Permissions.hasPermission(sender, chat, "add")) {
            add(args[0]);
            chat.send("Successfully added " + args[0]);
        } else {
            chat.send("You don't have permission! :P");
        }
        add(s);
    }

    private void add(String arg) throws SkypeException {
        Skype.getContactList().addFriend(arg, "Hello, I'd like to add you as a contact");
    }
}