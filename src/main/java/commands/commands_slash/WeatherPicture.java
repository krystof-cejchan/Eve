package commands.commands_slash;

import enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import enums_annotations_exceptions.enums.SlashCommandCategory;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import utility_class.UtilityClass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class WeatherPicture implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        try {
            //https://wttr.in/N%C3%A1chod.png
            URL url = new URL("http://wttr.in/" + Objects.requireNonNull(slashEvent
                    .getOption(Objects.requireNonNull(getArgName()).get(0))).getAsString().replaceAll(" ",
                    "%20") + ".png");
            String path = Objects.requireNonNull(slashEvent.getGuild()).getName() + UtilityClass.getCurrentDate(true)
                    + ".jpg";
            RenderedImage img = ImageIO.read(url);
            File file = new File(path);
            ImageIO.write(img, "jpg", file);


            //  slashEvent.reply(url).queue();

            slashEvent.replyFile(file, "Weather pic")
                    .queue();

            file.delete();

        } catch (IOException invalidWebAddress) {
            invalidWebAddress.printStackTrace();
        }
    }


    @Override
    public @NotNull String getDescription() {
        return "See the weather forecast for today and the two following days";
    }

    @Override
    public @NotNull String getName() {
        return "weatherpicture";
    }

    @Override
    public @NotNull ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.ONE;
    }

    @Nullable
    @Override
    public List<OptionData> getOptionData() {
        return new ArrayList<>(List.of(new OptionData(OptionType.STRING, Objects.requireNonNull(Objects
                .requireNonNull(getArgName()).get(0)),
                "paste name the city/town/location you want to see the forecast for",
                true)));
    }

    @Nullable
    @Override
    public List<String> getArgName() {
        return Collections.singletonList("city-town");
    }

    @Override
    public boolean isGuildOnly() {
        return false;
    }

    @Override
    public boolean isUserRequiredToBeInTheSameChannelAsBot() {
        return false;
    }

    public byte[] extractBytes(BufferedImage image) {

        WritableRaster raster = image.getRaster();
        DataBufferByte data = (DataBufferByte) raster.getDataBuffer();

        return (data.getData());
    }

    public BufferedImage toBufferedImage(final Image image, final int type) {
        if (image instanceof BufferedImage)
            return (BufferedImage) image;
        if (image instanceof VolatileImage)
            return ((VolatileImage) image).getSnapshot();
        loadImage(image);
        final BufferedImage buffImg = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        final Graphics2D g2 = buffImg.createGraphics();
        g2.drawImage(image, null, null);
        g2.dispose();
        return buffImg;
    }

    private void loadImage(final Image image) {
        class StatusObserver implements ImageObserver {
            boolean imageLoaded = false;

            public boolean imageUpdate(final Image img, final int infoflags,
                                       final int x, final int y, final int width, final int height) {
                if (infoflags == ALLBITS) {
                    synchronized (this) {
                        imageLoaded = true;
                        notify();
                    }
                    return true;
                }
                return false;
            }
        }
        final StatusObserver imageStatus = new StatusObserver();
        synchronized (imageStatus) {
            if (image.getWidth(imageStatus) == -1 || image.getHeight(imageStatus) == -1) {
                while (!imageStatus.imageLoaded) {
                    try {
                        imageStatus.wait();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public @NotNull List<SlashCommandCategory> getCategory() {
        return Collections.singletonList(SlashCommandCategory.WEATHER);
    }

}


