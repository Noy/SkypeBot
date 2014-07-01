package org.kodas.skypebot.command.commands.skypecommands;

import com.skype.Chat;
import com.skype.Profile;
import com.skype.Skype;
import com.skype.User;
import org.kodas.skypebot.command.SubCommand;
import org.kodas.skypebot.utils.Permissions;

/**
 * Created by Noy on 07/06/2014.
 */
public class SetStatusCommand extends SubCommand {

    @Override
    public void onCommand(Chat chat, User sender, String[] args) throws Exception {
        if (args.length == 0) {
            chat.send("!setstatus <status>");
            return;
        }
        if (Permissions.hasPermission(sender, chat, "setstatus")) {
            try {
                Skype.getProfile().setStatus(Profile.Status.valueOf(args[0].toUpperCase()));
                chat.send("You have set my status as " + args[0]);
            }catch (IllegalArgumentException e) {
                chat.send("That is not a valid status!");
            }
        }
    }
}
