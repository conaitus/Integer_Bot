package net.conanprozs.events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;


public class OnMessageEvent extends ListenerAdapter {


    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        String message = event.getMessage().getContentRaw().toLowerCase();
        String authorPing = "<@" + event.getAuthor().getId() + "> ";
        String author = event.getAuthor().getName();
        String prefix = "!";
        String commandPrefix = "!int";

        if (message.equals(commandPrefix)) {

            event.getMessage().getChannel().sendMessage(authorPing + "Hello, I am a bot!").queue();

        }else if (message.startsWith(commandPrefix)){

            String[] commandArgs = message.split(" ");
            String command = commandArgs[1];
            switch (command){

                case "ping":
                    event.getChannel().sendMessage(authorPing + "Pong!").queue();
                    break;
                case "help":
                    event.getChannel().sendMessage(authorPing + "No").queue();
                    break;
                case "test":
                    event.getChannel().sendMessage(authorPing + "Testing 1 2 3!").queue();
                    System.out.println(authorPing + "Testing 1 2 3!");
                    break;

                default:
                    event.getChannel().sendMessage(authorPing + "Unknown command!").queue();
                    break;

            }


        }
    }
}
