package dcbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.util.Arrays;

public class Bot {

    public static final GatewayIntent[] INTENTS = {GatewayIntent.GUILD_MESSAGES};

    public static void main(String[] args) throws LoginException {
        JDA jda = JDABuilder.create("", Arrays.asList(INTENTS))
                .setStatus(OnlineStatus.ONLINE)
                .build();
    }
}
