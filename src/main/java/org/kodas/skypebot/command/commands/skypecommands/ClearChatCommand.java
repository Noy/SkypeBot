package org.kodas.skypebot.command.commands.skypecommands;

import com.skype.Chat;
import com.skype.User;
import org.kodas.skypebot.command.SubCommand;
import org.kodas.skypebot.utils.Permissions;

/**
 * Created by Noy on 09/05/2014.
 */
public final class ClearChatCommand extends SubCommand {

    @Override
    public void onCommand(Chat chat, User sender, String[] args) throws Exception {
        if (Permissions.hasPermission(sender, chat, "clearchat")) {
            for (Integer i = 0; i < 30; i++) {
                chat.send("Chat Clearing... ");
            }
        }
        else {
            chat.send("You do not have permission! :P");
        }
    }
}