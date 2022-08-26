package cz.krystofcejchan.link_to_externalfiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExternalFileManager {
    private final List<InputStreamHolder> INPUT_STREAM_HOLDER_LIST = new ArrayList<>();

    public ExternalFileManager() {
        AllExternalFileInputStreams allInputStreams = new AllExternalFileInputStreams();
        INPUT_STREAM_HOLDER_LIST.add(new InputStreamHolder(allInputStreams.getHeyWakeUpGif(),
                ExternalFileNamesE.POKEGIF, ".gif"));
        INPUT_STREAM_HOLDER_LIST.add(new InputStreamHolder(allInputStreams.getGetSongSearchResPY(),
                ExternalFileNamesE.GETSONGSEARCHRES, ".py"));
        INPUT_STREAM_HOLDER_LIST.add(new InputStreamHolder(allInputStreams.getSoundFileToTextPY(),
                ExternalFileNamesE.SOUNDFILETOTEXT, ".py"));
        INPUT_STREAM_HOLDER_LIST.add(new InputStreamHolder(allInputStreams.getGetTheMostSuitableSongPY(),
                ExternalFileNamesE.GETTHEMOSTSUITABLESONG, ".py"));
        INPUT_STREAM_HOLDER_LIST.add(new InputStreamHolder(allInputStreams.getTranslatorFromCustomLangPY(),
                ExternalFileNamesE.TRANSLATORCUSTOM, ".py"));
        INPUT_STREAM_HOLDER_LIST.add(new InputStreamHolder(allInputStreams.getYtSearchPY(),
                ExternalFileNamesE.YTSEARCH, ".py"));
        INPUT_STREAM_HOLDER_LIST.add(new InputStreamHolder(allInputStreams.getTranslatorPY(),
                ExternalFileNamesE.TRANSLATOR, ".py"));
        INPUT_STREAM_HOLDER_LIST.add(new InputStreamHolder(allInputStreams.getPublicPlaylistsDB(),
                ExternalFileNamesE.PUBLIC_PLAYLISTS_DB, ".db"));
        INPUT_STREAM_HOLDER_LIST.add(new InputStreamHolder(allInputStreams.getRandomFactsPY(),
                ExternalFileNamesE.RANDOMFACTS, ".py"));



    }

    public void createNewLocalFiles() {
        this.INPUT_STREAM_HOLDER_LIST.forEach(inputStreamHolder -> {
            try {
                createNewLocalFiles(inputStreamHolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void createNewLocalFiles(InputStreamHolder in) throws IOException {
        in.transformInputStreamToLocalFile(in.getName());
    }
}
