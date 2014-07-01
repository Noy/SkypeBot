package org.kodas.skypebot.command.commands.skypecommands;

import com.skype.Chat;
import com.skype.Skype;
import com.skype.User;
import org.kodas.skypebot.command.SubCommand;
import org.kodas.skypebot.utils.Permissions;

/**
 * Created by Noy on 01/06/2014.
 */
public class SendMessageCommand extends SubCommand {

    @Override
    public void onCommand(Chat chat, User sender, String[] args) throws Exception {
        if (Permissions.hasPermission(sender, chat, "sendmessage")) {
            if (args.length == 0) {
                chat.send("Usage !sendmessage <username>");
                return;
            }
            User target = Skype.getUser(args[0]);
            if (target.isBlocked()) {
                chat.send("I have blocked that user and cannot message him/her.");
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                sb.append(args[i] + " ");
            }
            target.send(sb.toString().trim());
        }
    }
}
