package net.conanprozs;

import net.conanprozs.events.OnMessageEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Bot extends ListenerAdapter {

    public static void main(String[] args) throws LoginException {

        JDA bot = JDABuilder.createDefault(args[0])
                .addEventListeners(new Bot(), new OnMessageEvent())
                .setActivity(Activity.watching("the chat"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();


        System.out.println("Bot is running!");
    }



}
