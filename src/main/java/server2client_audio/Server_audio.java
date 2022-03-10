package server2client_audio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_audio {
	public static void launch(String filePath) throws IOException {

		if (filePath == null || filePath.isEmpty())
			throw new IllegalArgumentException("expected sound file");
		File soundFile = AudioUtil.getSoundFile(filePath);

		System.out.println("server: " + soundFile);

		try (ServerSocket serverSocker = new ServerSocket(Socket_Generator.getSocket());
				FileInputStream in = new FileInputStream(soundFile)) {
			if (serverSocker.isBound()) {
				Socket client = serverSocker.accept();
				OutputStream out = client.getOutputStream();

				byte buffer[] = new byte[1024];
				int count;
				while ((count = in.read(buffer)) != -1)
					out.write(buffer, 0, count);
			}
		}

		System.out.println("server: shutdown");

	}
}
