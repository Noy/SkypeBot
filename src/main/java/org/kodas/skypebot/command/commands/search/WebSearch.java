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
 * Created by Noy on 09/05/2014.
 */
public final class WebSearch extends SubCommand {

    @Override
    public void onCommand(Chat chat, User sender, String[] args) throws Exception {
        if (!Permissions.hasPermission(sender, chat, "search")) return;
        chat.send("Looking...");
        String search = "";
        Integer start = 0;
        switch (args[0]) {
            case "-rand":
                search = StringUtils.implode(1, args);
                start = new Random().nextInt(50);
                break;
            default:
                search = StringUtils.implode(args);
                break;
        }
        Google.GoogleResult result = Google.search("web", search, start);
        Google.Results[] results = result.responseData.results;
        if (results != null && results.length > 0) {
            ChatManager.chat(chat, start + 1 + ". " + results[0].unescapedUrl);
            ChatManager.chat(chat, results[0].titleNoFormatting);
        } else {
            ChatManager.chat(chat, "No results for " + StringUtils.implode(args));
        }
    }
}