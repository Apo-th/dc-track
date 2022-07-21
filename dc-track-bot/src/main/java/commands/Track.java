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

public class Track extends ListenerAdapter {

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

            try {
                long uuid = getUuidFromName(player);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            // get person who requested
            // ping the hypixel api every min to see if they're still on skyblock
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

    public long getUuidFromName(String name) throws Exception {

        long unixTIme = Instant.now().getEpochSecond();

        HttpClient client = HttpClient.newHttpClient();

        // get uuid from player name
        HttpRequest request = HttpRequest
                .newBuilder(URI.create("https://api.mojang.com/users/profiles/minecraft/" + name + "?at" + unixTIme))
                .GET()
                .build();

        HttpResponse<String> response;

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body().toString());

        return 1l;
    }
}
