package cz.krystofcejchan.objects;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

class ExternalFilesTest {
    @Test
    void localFile() throws IOException {
        InputStream is = this.getClass().getResourceAsStream("/external_files/graphics/hey-wake-up-poke-discord.gif");
        Path p = Path.of("src\\main\\resources\\external_files\\temp.gif");
        assert is != null;
        Files.copy(is, p, new StandardCopyOption[]{StandardCopyOption.REPLACE_EXISTING});
      //  new AllExternalFileInputStreams();
    }

    private static void printInputStream(InputStream is) {

        try (InputStreamReader streamReader =
                     new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

    public static class ExternalFilesData {
        /**
         * input-stream from resources folder
         */
        private final InputStream inputStream;
        /**
         * file name with its suffix
         * <br>example:<b> script.py</b>
         */
        private final String fileNameWithSuffix;
        /**
         * {@link Path} to the file location<br>
         * example: <b>/home/user/folder1/</b>
         */
        private final Path filePath;

        private final Path completedFilePath;

        public ExternalFilesData(InputStream inputStream, String fileNameWithSuffix, Path filePath) {
            this.inputStream = inputStream;
            this.fileNameWithSuffix = fileNameWithSuffix;
            this.filePath = filePath;
            this.completedFilePath = (filePath.endsWith("/") || filePath.endsWith("\\"))
                    ? Path.of(filePath + fileNameWithSuffix)
                    : Path.of(filePath + "/" + fileNameWithSuffix);
        }

        public InputStream getInputStream() {
            return inputStream;
        }

        public String getFileNameWithSuffix() {
            return fileNameWithSuffix;
        }

        public Path getFilePath() {
            return filePath;
        }


        public Path getCompletedFilePath() {
            return completedFilePath;
        }


        /**
         * transforms input stream to local file
         *
         * @throws IOException file or path does not exist
         */
        public void transformInputStreamToLocalFile() throws IOException {
            Files.copy(getInputStream(), getCompletedFilePath(), StandardCopyOption.REPLACE_EXISTING);
        }

    }
}