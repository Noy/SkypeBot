package org.kodas.skypebot.command.commands.skypecommands;

import com.skype.Chat;
import com.skype.User;
import org.kodas.skypebot.command.SubCommand;

/**
 * Created by Noy on 09/05/2014.
 */
public final class AddCommand extends SubCommand {
    @Override
    public void onCommand(Chat chat, User sender, String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(arg).append(" ");
        }
        chat.send("/add " + sb.toString().trim());
    }
}
