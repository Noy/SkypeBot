package org.kodas.skypebot.command.commands.skypecommands;

import com.skype.Chat;
import com.skype.SkypeException;
import com.skype.User;
import org.kodas.skypebot.command.SubCommand;
import org.kodas.skypebot.utils.ChatManager;

/**
 * Created by Noy on 09/05/2014.
 */
public final class GetUsersCommand extends SubCommand {
    @Override
    public void onCommand(Chat chat, User sender, String[] args) throws SkypeException {
        StringBuilder sb = new StringBuilder();
        for (User user : chat.getAllMembers()) {
            if (args.length == 0) {
                sb.append(user.getId() + ": " + user.getFullName() + ", ");
            } else {
                sb.append(user.getId() + " ");

            }
        }
        ChatManager.chat(chat, sb.toString());
    }
}
