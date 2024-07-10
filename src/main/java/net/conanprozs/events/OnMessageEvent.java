package net.conanprozs.events;

import net.conanprozs.Bot;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.eclipse.jetty.util.ArrayUtil;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.join;

public class OnMessageEvent extends ListenerAdapter {


    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {


        String message = event.getMessage().getContentRaw().toLowerCase();
        String authorPing = "<@" + event.getAuthor().getId() + "> ";
        String author = event.getAuthor().getName();
        String commandPrefix = "!int";

        if (message.equals(commandPrefix)) {

            event.getMessage().getChannel().sendMessage(authorPing + "Hello, I am a bot!").queue();

        } else if (message.startsWith(commandPrefix)) {

            String[] commandArgs = message.split(" ");
            String command = commandArgs[1];
            switch (command) {

                case "talk":
                    try {
                        onTalk(commandArgs, event);
                    } catch (IOException | ParseException e) {
                        event.getChannel().sendMessage(authorPing + "I could not find a response").queue();
                    }
                    break;
                case "ping":
                    event.getChannel().sendMessage(authorPing + "Poooooooooooooooooooooooooooooooooooooooooong!").queue();
                    break;
                case "help":
                    event.getChannel().sendMessage(authorPing + "No").queue();
                    break;
                case "test":
                    event.getChannel().sendMessage(authorPing + "Testing 1 2 3!").queue();
                    System.out.println(authorPing + "Testing 1 2 3!");
                    break;
                case "info":
                    event.getChannel().sendMessage(authorPing + "Bot is made by Conaitus:flag_mc:").queue();
                    break;

                default:
                    event.getChannel().sendMessage(authorPing + "Unknown command!").queue();
                    break;

            }


        }
    }

    private void onTalk(String[] args, MessageReceivedEvent event) throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader("talk.json"));
        JSONObject jo = (JSONObject) obj;

        String authorPing = "<@" + event.getAuthor().getId() + "> ";

        args = ArrayUtil.removeFromArray(ArrayUtil.removeFromArray(args, args[1]), args[0]);
        String pargs = String.join(" ", args);
        String data = (String) jo.get(pargs);

        if (data == null){
            event.getChannel().sendMessage(authorPing + "I could not find a response").queue();
            return;
        }

        event.getChannel().sendMessage(authorPing + data).queue();
    }
}