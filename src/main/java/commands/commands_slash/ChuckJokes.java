package commands.commands_slash;

import commands.api.chuckjokes.ExtractedJokeFromJSONResult;
import enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import utility_class.UtilityClass;

import java.awt.*;
import java.util.List;

public class ChuckJokes implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        slashEvent.replyEmbeds(new EmbedBuilder().setColor(Color.black)
                .addField("Random Chuck's joke", "```bash\n\"" +
                        ExtractedJokeFromJSONResult.getJokeFromJSON() + "\"\n```", false)
                .setImage(randomChucksPhoto()).setFooter("random photo of chuck")
                .build()).queue();
    }

    private String randomChucksPhoto() {
        String[] photos = {"https://i.blogs.es/17a983/chuck_norris/1366_2000.jpg",
                "https://thumbnails.texastribune.org/aKvtIx_jKc_eYL_-LODNiFQWl0k=/850x570/smart/filters:quality(75)/" +
                        "https://static.texastribune.org/media/images/chuck-norris1.jpg",
                "https://www.liveabout.com/thmb/c9MeRUocPTnYAB4JIn7s6zp_lME=/395x0/filters:no_upscale()" +
                        ":max_bytes(150000):strip_icc()/chuck-norris-in--hero-and-the-terror--163063331-5c08614246e0fb0001e81a8c.jpg",
                "https://i.pinimg.com/736x/7d/cd/b1/7dcdb12acc0a1fd3af7ca0f6f8101957.jpg",
                "https://c.tenor.com/oO-vl6IYj4MAAAAC/chuck-norris-muscle.gif",
                "https://www.thefactsite.com/wp-content/uploads/2021/03/chuck-norris-facts.jpg",
                "https://cdn.xsd.cz/resize/ce0fa0a7d1493b67a4cc2d5750b456e8_.jpg?hash=a604903e2497d657459c1cadd724b800",
                "https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fstatic.onecms.io%2Fwp-content%2Fuploads%2" +
                        "Fsites%2F6%2F2014%2F11%2Fchuck-norris_612x380_1.jpg",
                "https://media-cldnry.s-nbcnews.com/image/upload/t_fit-1500w,f_auto,q_auto:best/streams" +
                        "/2012/March/120305/143382-g-rnt-120305-norris-walker.jpg",
                "https://assets.deutschlandfunk.de/FILE_8be99f77dc4821bcd0ac8c6d76ed6077/1920x1080.jpg?t=1597576838367",
                "https://www1.wdr.de/stichtag/stichtagmaerzzehn-100~_v-gseapremiumxl.jpg",
                "https://www.lavanguardia.com/uploads/2020/03/10/5fa908e2329b4.jpeg",
                "https://i.insider.com/5449264a6bb3f7f539384516?width=638&format=jpeg",
                "https://www.godreports.com/wp-content/uploads/2018/03/chuck-norris-war-movie-350x183.png",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZviBpiEM2QVDntN7W7R8CrDjzv_nNi0b55SHc9L6DJAPtD9l2yrK0pgUCZp5AGlaWjnM&usqp=CAU",
                "https://imagecdn.handitv.com/bNUTO-1642798044-814-blog-snakearticle.jpg"};

        return photos[UtilityClass.generateRandomInt(0, photos.length - 1)];

    }

    @Override
    public @NotNull String getDescription() {
        return "get a Chuck Norris Joke";
    }

    @Override
    public @NotNull String getName() {
        return "chucknorris_joke";
    }

    @Override
    public @NotNull ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.NONE;
    }

    @Nullable
    @Override
    public List<OptionData> getOptionData() {
        return null;
    }

    @Nullable
    @Override
    public List<String> getArgName() {
        return null;
    }

    @Override
    public boolean isGuildOnly() {
        return false;
    }

    @Override
    public boolean isUserRequiredToBeInTheSameChannelAsBot() {
        return false;
    }
}
