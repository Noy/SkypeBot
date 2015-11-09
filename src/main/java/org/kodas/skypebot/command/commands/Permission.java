package org.kodas.skypebot.command.commands;

import com.skype.Chat;
import com.skype.User;
import org.kodas.skypebot.command.SubCommand;
import org.kodas.skypebot.utils.Permissions;

/**
 * Created by Noy on 09/05/2014.
 */
public final class Permission extends SubCommand {
    @Override
    public void onCommand(Chat chat, User sender, String[] args) throws Exception {
        if (sender.getId().equalsIgnoreCase("n0yyyyyyy") || sender.getId().equalsIgnoreCase("jordg.cjail")) {
            if (args.length == 0) {
                chat.send("!perm <add> <user> <permission>");
                chat.send("!perm remove <user> <permission>");
                return;
            }
            if (args[0].equalsIgnoreCase("add")) {
                Permissions.addPerm(args[1], chat.getId(), args[2]);
                chat.send("The permission has been set.");
            } else if (args[0].equalsIgnoreCase("all")) {
                for (User user : chat.getAllActiveMembers()) {
                    Permissions.addPerm(user, chat, args[1]);
                }
                chat.send("Adding permission!");
            } else {
                Permissions.removePerms(args[1], chat.getId(), args[2]);
                chat.send("Removing permission from said user!");
            }
        } else {
            chat.send("You do not have permission!");
        }
    }
}