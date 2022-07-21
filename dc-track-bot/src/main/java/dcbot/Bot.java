package dcbot;

import commands.Track;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.util.Arrays;

public class Bot {

    public static final GatewayIntent[] INTENTS = {GatewayIntent.GUILD_MESSAGES};


    public static void main(String[] args) throws Exception {
//        JDA jda = JDABuilder.create("", Arrays.asList(INTENTS))
//                .setStatus(OnlineStatus.ONLINE)
//                .addEventListeners(new Track())
//                .build().awaitReady();
//
//        jda.getGuildById("id")
//                .upsertCommand("track", "Start tracking a player's active status in hypixel skyblock")
//                .addOption(OptionType.STRING, "player", "ign of player you want to track", true)
//                .queue();

        // global command
//        jda.upsertCommand("track", "Start tracking a player's active status in hypixel skyblock")
//                .addOption(OptionType.STRING, "player", "ign of player you want to track", true)
//                .queue();
    }
}
