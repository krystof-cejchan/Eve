package cz.krystofcejchan.objects.sound_files;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

class SoundFileTest {
    @Test
    void pathTest() {
        File f = new File("src\\main\\java\\cz\\krystofcejchan\\external_files\\sh\\py_pip.sh");
        Assertions.assertEquals(
                "C:\\Users\\kryst\\git\\repository3\\Eve\\src\\main\\java\\cz\\krystofcejchan\\external_files\\sh\\py_pip.sh",
                f.getAbsolutePath());
    }

}