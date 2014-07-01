package org.kodas.skypebot.command.commands.search;

import com.skype.Chat;
import com.skype.User;
import org.kodas.skypebot.command.SubCommand;
import org.kodas.skypebot.internet.Google;
import org.kodas.skypebot.utils.ChatManager;
import org.kodas.skypebot.utils.Permissions;
import org.kodas.skypebot.utils.StringUtils;

import java.util.Random;

/**
 * Created by Noy on 01/06/2014.
 */
public class ArianaPic extends SubCommand {

    @Override
    public void onCommand(Chat chat, User sender, String[] args) throws Exception {
        if (!Permissions.hasPermission(sender, chat, "ariana")) return;
        chat.send("Looking...");
        String search = "";
        Integer start = 0;
        search = StringUtils.implode(1, "Ariana Grande");
        start = new Random().nextInt(51);
        Google.GoogleResult result = Google.search("images", "Ariana Grande", start);
        Google.Results[] results = result.responseData.results;
        if (results != null && results.length > 0) {
            ChatManager.chat(chat, start + 1 + ". " + results[0].unescapedUrl);
            ChatManager.chat(chat, results[0].titleNoFormatting);
        }
    }
}