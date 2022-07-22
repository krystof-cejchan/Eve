package cz.krystofcejchan.link_to_externalfiles;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AllExternalFileInputStreams {
    private final List<InputStream> inputStreamList = new ArrayList<>();

    private final InputStream heyWakeUpGif = this.getClass()
            .getResourceAsStream("/external_files/graphics/hey-wake-up-poke-discord.gif");
    private final InputStream getSongSearchResPY = this.getClass()
            .getResourceAsStream("/external_files/py_scripts/GetSongSearchResult_s.py");
    private final InputStream getTheMostSuitableSongPY = this.getClass()
            .getResourceAsStream("/external_files/py_scripts/GettheMostSuitableSong.py");
    private final InputStream soundFileToTextPY = this.getClass()
            .getResourceAsStream("/external_files/py_scripts/soundfiletotext.py");
    private final InputStream translatorPY = this.getClass()
            .getResourceAsStream("/external_files/py_scripts/translator.py");
    private final InputStream translatorFromCustomLangPY = this.getClass()
            .getResourceAsStream("/external_files/py_scripts/translator_fromTo_customlang.py");
    private final InputStream ytSearchPY = this.getClass()
            .getResourceAsStream("/external_files/py_scripts/ytsearch.py");


    public AllExternalFileInputStreams() {
        addToArray(heyWakeUpGif);
        addToArray(getSongSearchResPY);
        addToArray(getTheMostSuitableSongPY);
        addToArray(soundFileToTextPY);
        addToArray(translatorPY);
        addToArray(translatorFromCustomLangPY);
        addToArray(ytSearchPY);

        inputStreamList.forEach(inputStream ->
        {
            try {
                if (inputStream.available() < 1)
                    throw new IOException();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("one of the stream-inputs is null or its size is less than 1");
                System.exit(-1);
            }

        });
    }

    private void addToArray(InputStream inputStream) {
        if (!inputStreamList.contains(inputStream))
            inputStreamList.add(inputStream);
    }

    public InputStream getHeyWakeUpGif() {
        return heyWakeUpGif;
    }

    public InputStream getGetSongSearchResPY() {
        return getSongSearchResPY;
    }

    public InputStream getGetTheMostSuitableSongPY() {
        return getTheMostSuitableSongPY;
    }

    public InputStream getSoundFileToTextPY() {
        return soundFileToTextPY;
    }

    public InputStream getTranslatorPY() {
        return translatorPY;
    }

    public InputStream getTranslatorFromCustomLangPY() {
        return translatorFromCustomLangPY;
    }

    public InputStream getYtSearchPY() {
        return ytSearchPY;
    }

}