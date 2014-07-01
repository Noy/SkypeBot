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
public class SetMoodCommand extends SubCommand {

    @Override
    public void onCommand(Chat chat, User sender, String[] args) throws Exception {
        if (Permissions.hasPermission(sender, chat, "setmood")) {
            StringBuilder sb = new StringBuilder();
            for (String arg : args) {
                sb.append(arg).append(" ");
            }
            Skype.getProfile().setMoodMessage(sb.toString().trim());
            Skype.getProfile().setStatus(Profile.Status.valueOf(args[0]));
            chat.send("You have set my mood message to " + sb.toString().trim());
        }
    }
}
