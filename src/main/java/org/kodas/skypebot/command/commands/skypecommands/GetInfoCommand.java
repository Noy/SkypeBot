package org.kodas.skypebot.command.commands.skypecommands;

import com.skype.*;
import org.kodas.skypebot.command.SubCommand;

import java.util.Date;

/**
 * Created by Noy on 10/05/2014.
 */
public final class GetInfoCommand extends SubCommand {

    @Override
    public void onCommand(Chat chat, User sender, String[] args) throws Exception {
        if (args.length == 0) chat.send("Usage: !getinfo <user>");
        getInfo(args[0], chat);
    }

    private void getInfo(String arg, Chat chat) throws SkypeException {
        User user = Skype.getUser(arg);
        Date birthday = user.getBirthDay();
        String fullName = user.getFullName();
        String id = user.getId();
        String intro = user.getIntroduction();
        String city = user.getCity();
        String language = user.getLanguage();
        String country = user.getCountry();
        Integer timezone = user.getTimeZone();
        String displayName = user.getDisplayName();
        String mobilePhone = user.getMobilePhoneNumber();
        Date lastOnlineTime = user.getLastOnlineTime();
        User.Sex gender = user.getSex();
        if (birthday == null || fullName == null || id == null || intro == null || city == null || language == null || country == null || timezone == null || displayName == null
                || mobilePhone == null || lastOnlineTime == null || gender == null) {
            chat.send("Could not find this information!");
            return;
        }
        chat.send(arg + "'s birthday is on: " + birthday);
        chat.send(arg + "'s full name is: " + user.getFullName());
        chat.send(arg + "'s ID is: " + user.getId());
        chat.send(arg + "'s 'About Me' is: " + user.getIntroduction());
        chat.send(arg + "'s City is: " + user.getCity());
        chat.send(arg + "'s Language is: " + user.getLanguage());
        chat.send(arg + "'s Country is: " + user.getCountry());
        chat.send(arg + "'s Time Zone is: " + user.getTimeZone());
        chat.send(arg + "'s Display Name is: " + user.getDisplayName());
        chat.send(arg + "'s Mobile Phone number is: " + user.getMobilePhoneNumber());
        chat.send(arg + "'s Last online way on: " + user.getLastOnlineTime());
        chat.send(arg + "'s Gender is: " + user.getSex());
        chat.send("Note - Can only get " + arg + "'s info if they have provided it themselves!");
    }
}
