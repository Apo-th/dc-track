package commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Track extends ListenerAdapter {

    HttpClient client = HttpClient.newHttpClient();

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        String command = event.getName();
        OptionMapping options = event.getOption("player");

        if (options != null){
            event.reply("Please input an ign").queue();
            return;
        }

        if (command.equals("track")){

            event.deferReply().queue();

            String player = options.getAsString();

            String uuid;

            try {
                uuid = getUuidFromName(player);
            } catch (Exception e) {
                event.reply("Player not found!");
                throw new RuntimeException(e);
            }

            // get person who requested
            // ping the hypixel api every min to see if they're still on skyblock
            try {
                pingHypixelApi(uuid);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            /*
            if session.online == true {
                if !(session.game == SKYBLOCK and session.place == dynamic) {
                    let player know they are not in sb
                }
            } else {
                let person know they're offline
            }
             */

            event.getHook().sendMessage(player + " is being tracked").queue();
        }
    }

    public String getUuidFromName(String name) throws Exception {

        long unixTIme = Instant.now().getEpochSecond();

        // get uuid from player name
        HttpRequest request = HttpRequest
                .newBuilder(URI.create("https://api.mojang.com/users/profiles/minecraft/" + name + "?at" + unixTIme))
                .GET()
                .build();

        HttpResponse<String> response;

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();

        // use regex to find id from response coz I cbs turning it into a pojo
        Pattern pattern = Pattern.compile("\"id\":\".*\"");
        Matcher matcher = pattern.matcher(responseBody);
        String retVal = "";
        if(matcher.find()) {
            System.out.println(matcher.group());
            retVal = matcher.group().substring(6, matcher.group().length() - 1);
        } else {
            return null;
        }

        return retVal;
    }

    public boolean pingHypixelApi(String uuid) throws Exception {

        HttpRequest httpRequest = HttpRequest
                .newBuilder(URI.create("https://api.hypixel.net/status?uuid=" + uuid))
                .header("API-key", "key")
                .GET()
                .build();

        HttpResponse<String> response;

        response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        // get whether player is on skyblock or not and return true if they are, false if not

        return true;
    }
}
