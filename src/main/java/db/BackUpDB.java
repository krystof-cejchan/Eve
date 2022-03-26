package db;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import _library_class.LibraryClass;

public class BackUpDB {
	public static boolean wasSuccess;

	public static boolean WasSuccess() {
		return wasSuccess;
	}

	public static void setWasSuccess(boolean wasSuccess) {
		BackUpDB.wasSuccess = wasSuccess;
	}

	public static void copyFileUsingJava7Files(File source, String dir) throws IOException {
		File n_dest_time = new File(dir.concat(LibraryClass.getCurrentDate(true)));
		if (Files.exists(source.toPath())) {
			Files.copy(source.toPath(), n_dest_time.toPath());
		}
		if (isSuccessful(source, n_dest_time)) {
			setWasSuccess(true);
		} else {
			setWasSuccess(false);
			System.out.println("error whilst copying files!");
		}

	}

	public static boolean isSuccessful(File source, File dir) throws IOException {
		if (source.exists() && dir.exists()) {
			if (source.getTotalSpace() - dir.getTotalSpace() < 15) {
				return true;
			}
		}
		return false;
	}
}
