package org.kodas.skypebot.command.commands.skypecommands;

import com.skype.*;
import org.kodas.skypebot.command.SubCommand;

import java.util.Date;

/**
 * Created by Noy on 10/05/2014.
 */
public final class GetBirthdayCommand extends SubCommand {

    @Override
    public void onCommand(Chat chat, User sender, String[] args) throws Exception {
        if (args.length == 0) chat.send("Usage: !getbirthday <user>");
        getBirthday(args[0], chat);
    }

    private void getBirthday(String arg, Chat chat) throws SkypeException {
        User user = Skype.getUser(arg);
        Date birthday = user.getBirthDay();
        if (birthday == null) {
            chat.send("Could not find that user's birthday!");
            return;
        }
        chat.send(arg + "'s birthday is on " + birthday);
    }
}
