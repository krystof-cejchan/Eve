package cz.krystofcejchan.link_to_externalfiles;

import cz.krystofcejchan.enums_annotations_exceptions.enums.ExternalFileNames;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExternalFileManager {
    private final List<InputStreamHolder> INPUT_STREAM_HOLDER_LIST = new ArrayList<>();

    public ExternalFileManager() {
        AllExternalFileInputStreams allInputStreams = new AllExternalFileInputStreams();
        INPUT_STREAM_HOLDER_LIST.add(new InputStreamHolder(allInputStreams.getHeyWakeUpGif(),
                ExternalFileNames.POKEGIF, ".gif"));
        INPUT_STREAM_HOLDER_LIST.add(new InputStreamHolder(allInputStreams.getGetSongSearchResPY(),
                ExternalFileNames.GETSONGSEARCHRES, ".py"));
        INPUT_STREAM_HOLDER_LIST.add(new InputStreamHolder(allInputStreams.getSoundFileToTextPY(),
                ExternalFileNames.SOUNDFILETOTEXT, ".py"));
        INPUT_STREAM_HOLDER_LIST.add(new InputStreamHolder(allInputStreams.getGetTheMostSuitableSongPY(),
                ExternalFileNames.GETTHEMOSTSUITABLESONG, ".py"));
        INPUT_STREAM_HOLDER_LIST.add(new InputStreamHolder(allInputStreams.getTranslatorFromCustomLangPY(),
                ExternalFileNames.TRANSLATORCUSTOM, ".py"));
        INPUT_STREAM_HOLDER_LIST.add(new InputStreamHolder(allInputStreams.getYtSearchPY(),
                ExternalFileNames.YTSEARCH, ".py"));
        INPUT_STREAM_HOLDER_LIST.add(new InputStreamHolder(allInputStreams.getTranslatorPY(),
                ExternalFileNames.TRANSLATOR, ".py"));
        INPUT_STREAM_HOLDER_LIST.add(new InputStreamHolder(allInputStreams.getPublicPlaylistsDB(),
                ExternalFileNames.PUBLIC_PLAYLISTS_DB, ".db"));



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
