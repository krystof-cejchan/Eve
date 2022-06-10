package weather.logic.pic;

import enums_annotations_exceptions.exceptions.InvalidWebAddress;
import weather.logic.WeatherWebPage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class PictureGrabber extends WeatherWebPage {
    public PictureGrabber(String url) throws InvalidWebAddress {
        super(url);
    }

    public ImageIcon getPictureOfWeather() throws IOException {
        return new ImageIcon(ImageIO.read(new URL(super.getUrl())));
    }

}
