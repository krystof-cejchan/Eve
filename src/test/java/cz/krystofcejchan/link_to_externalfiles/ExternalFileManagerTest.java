package cz.krystofcejchan.link_to_externalfiles;

import org.junit.jupiter.api.Test;

class ExternalFileManagerTest {
    @Test
    void testInputStreamsToLocalFiles() {
        ExternalFileManager manager = new ExternalFileManager();
        manager.createNewLocalFiles();
        assert InputStreamHolder.fileNameToPathMap != null;
        InputStreamHolder.fileNameToPathMap.forEach((v, k) -> {
                    System.out.print(v+" ");
                    System.out.println(k.toString());
                }
        );
    }

}