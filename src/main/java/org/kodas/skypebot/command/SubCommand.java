package org.kodas.skypebot.command;

import com.skype.Chat;
import com.skype.User;

/**
 * Created by Noy on 09/05/2014.
 */
public abstract class SubCommand {

    public abstract void onCommand(Chat chat, User sender, String[] args) throws Exception;
}