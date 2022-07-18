package cz.krystofcejchan.main.after_startup;

import cz.krystofcejchan.link_to_externalfiles.ExternalFileManager;
import cz.krystofcejchan.link_to_externalfiles.InputStreamHolder;

public class CreateNewExternalFilesLocally implements IAfterStartUp {

    @Override
    public void doAfterStartUp() {
        ExternalFileManager manager = new ExternalFileManager();
        manager.createNewLocalFiles();
        assert InputStreamHolder.fileNameToPathMap != null;
        InputStreamHolder.fileNameToPathMap.forEach((v, k) -> {
                    System.out.print(v);
                    System.out.println(k);
                }
        );
    }

}
