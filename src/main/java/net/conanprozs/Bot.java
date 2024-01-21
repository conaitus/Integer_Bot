package net.conanprozs;

import net.conanprozs.events.OnMessageEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Bot extends ListenerAdapter {

    static JDA bot;
    static String status;
    public static void main(String[] args) throws LoginException {

        bot = JDABuilder.createDefault(args[0])
                .addEventListeners(new Bot(), new OnMessageEvent())
                .setActivity(Activity.watching("the chat"))
                .setStatus(OnlineStatus.IDLE)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();

        System.out.println("Bot is running!");


        status = "idle";
        new Timer().schedule(new TimerTask(){
            public void run(){
                if (Objects.equals(status, "idle")){
                    bot.getPresence().setStatus(OnlineStatus.ONLINE);
                    status = "online";
                }else{
                    bot.getPresence().setStatus(OnlineStatus.IDLE);
                    status = "idle";
                }

            }},0,840);
    }



}
