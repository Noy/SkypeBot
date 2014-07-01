package org.kodas.skypebot.command.commands.skypecommands;

import com.skype.Chat;
import com.skype.User;
import org.kodas.skypebot.command.SubCommand;

/**
 * Created by Noy on 10/05/2014.
 */
public final class MLGCommand extends SubCommand {
    @Override
    public void onCommand(Chat chat, User sender, String[] args) throws Exception {
        sender.send("https://www.youtube.com/watch?v=7-MDuM8HvJ8");
        sender.send(";)");
    }
}
