package org.kodas.skypebot.command.commands.skypecommands;

import com.skype.Chat;
import com.skype.User;
import org.kodas.skypebot.command.SubCommand;

/**
 * Created by Noy on 10/05/2014.
 */
public final class TopicCommand extends SubCommand {

    @Override
    public void onCommand(Chat chat, User sender, String[] args) throws Exception {
        if (args.length == 0) {
            chat.send("Usage: !topic <topic>");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(arg).append(" ");
        }
        chat.setTopic(sb.toString().trim());
    }
}
