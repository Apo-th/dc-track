package commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;

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

            // track logic
            // get uuid from player name
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

            event.getHook().sendMessage(" is being tracked").queue();
        }
    }
}
