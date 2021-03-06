package bulletin_board.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import bulletin_board.exception.IORuntimeException;

public class StreamUtil {
	public static void copy(InputStream input, OutputStream output) {

		byte[] buffer = new byte[4096];
		try {
			for (int n = 0; -1 != (n = input.read(buffer));) {
				output.write(buffer, 0, n);
			}
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}

	}

}
