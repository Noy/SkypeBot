package org.kodas.skypebot.command.commands.skypecommands;

import com.skype.Chat;
import com.skype.User;
import org.kodas.skypebot.command.SubCommand;
import org.kodas.skypebot.utils.Permissions;

/**
 * Created by Noy on 09/05/2014.
 */
public final class JoinCall extends SubCommand {

    @Override
    public void onCommand(Chat chat, User sender, String[] args) throws Exception {
        if (Permissions.hasPermission(sender, chat, "join")) {
            chat.send("Joining/Hosting call.");
            chat.send("/golive");
        } else {
            chat.send("You don't have permission! :P");
        }
    }
}