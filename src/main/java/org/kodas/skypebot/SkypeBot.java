package org.kodas.skypebot;

import com.skype.*;
import lombok.extern.java.Log;
import org.kodas.skypebot.command.SubCommand;
import org.kodas.skypebot.command.commands.*;
import org.kodas.skypebot.command.commands.search.ArianaPic;
import org.kodas.skypebot.command.commands.search.ImageSearch;
import org.kodas.skypebot.command.commands.search.VideoSearch;
import org.kodas.skypebot.command.commands.search.WebSearch;
import org.kodas.skypebot.command.commands.skypecommands.*;
import org.kodas.skypebot.utils.ChatManager;
import org.kodas.skypebot.utils.Permissions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Noy on 09/05/2014.
 */
@Log
public final class SkypeBot {

    private Map<String, SubCommand> commands = new HashMap<>();
    public static final String LAST_FILE = "lastchat";

    public static void main(String[] args) {
        log.info("Starting SkypeBot..");
        log.info("Started!");
        Skype.setDaemon(false);
        try {
            File file = new File(LAST_FILE);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                String chatId = scanner.nextLine();
                for (Chat chat : Skype.getAllChats()) {
                    if (chat.getId().equals(chatId)) {
                        chat.send("Starting Bot");
                    }
                    scanner.close();
                    file.delete();
                }
            }
            new SkypeBot().start();
        } catch (SkypeException | FileNotFoundException e) {
            System.exit(0);
        }
    }

    public void start() throws SkypeException {
        ChatManager.start();
        registerCommands();
        Skype.addChatMessageListener(new ChatMessageAdapter() {
            @Override
            public void chatMessageReceived(ChatMessage received) throws SkypeException {
                try {
                    if (received.getContent().startsWith("shutdown bot") && Permissions.hasPermission(received.getSender(), received.getChat(), "shutdown")) {
                        received.getChat().send("Shutting down bot!");
                        System.exit(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (received.getContent().startsWith("!") && received.getTime().getTime() > System.currentTimeMillis() - 300000) {
                    String[] split = received.getContent().split(" ");
                    String command = getCommand(split);
                    String[] args = getArgs(split);
                    print("Received command: " + Arrays.toString(split) + " from " + received.getSender().getId());
                    SubCommand sub = commands.get(command);
                    if (sub != null) {
                        executeAsync(sub, received.getChat(), received.getSender(), args);
                    } else {
                        received.getChat().send("Unknown command, type !help for help.");
                    }
                }
            }
        });
    }

    private void executeAsync(final SubCommand sub, final Chat chat, final User sender, final String[] args) {
        new Thread() {
            @Override
            public void run() {
                try {
                    sub.onCommand(chat, sender, args);
                } catch (Exception e) {
                    ChatManager.printThrowable(chat, e);
                }
            }
        }.start();
    }

    private String getCommand(String[] split) {
        return split[0].substring(1).toLowerCase();
    }

    private String[] getArgs(String[] split) {
        ArrayList<String> t = new ArrayList<>(Arrays.asList(split));
        t.remove(0);
        return t.toArray(new String[t.size()]);
    }

    private void registerCommands() {
        commands.put("hi", new HelloCommand());
        commands.put("hello", new HelloCommand());
        commands.put("vidsearch", new VideoSearch());
        commands.put("call", new JoinCall());
        commands.put("leave", new LeaveCommand());
        commands.put("alertsoff", new AlertsOffCommand());
        commands.put("kick", new KickCommand());
        commands.put("search", new WebSearch());
        commands.put("add", new AddCommand());
        commands.put("clearchat", new ClearChatCommand());
        commands.put("getusers", new GetUsersCommand());
        commands.put("<3", new HeartCommand());
        commands.put("(bear)", new BearCommand());
        commands.put("(hug)", new BearCommand());
        commands.put("mlg", new MLGCommand());
        commands.put("ban", new BanCommand());
        commands.put("kickban", new KickBanCommand());
        commands.put("topic", new TopicCommand());
        commands.put("imgsearch", new ImageSearch());
        commands.put("block", new BlockCommand());
        commands.put("getbirthday", new GetBirthdayCommand());
        commands.put("getinfo", new GetInfoCommand());
        commands.put("help", new HelpCommand());
        commands.put("perm", new Permission());
        commands.put("addcontact", new AddContactCommand());
        commands.put("removecontact", new RemoveContactCommand());
        commands.put("wouldyou", new WouldYou());
        commands.put("sendmessage", new SendMessageCommand());
        commands.put("arianapic", new ArianaPic());
        commands.put("setmood", new SetMoodCommand());
        commands.put("setstatus", new SetStatusCommand());
        commands.put("ddos", new DDoSCommand());
    }

    @SafeVarargs
    private static <T> void print(T... args) {
        for (T t : args) {
            System.out.println(t);
        }
    }
}
