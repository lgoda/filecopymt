package main.java.org.company.interviewtest.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class FileMultithreadUtil {

    private FileMultithreadUtil() {
    }

    private static int BUFFER_SIZE = 4096;

    public static long copy(InputStream from, OutputStream to, String thread, String msg)
        throws IOException {
        byte[] buf = new byte[BUFFER_SIZE];
        long total = 0;
        while (true) {
            int bytesRead = from.read(buf);
            if (bytesRead == -1) {
                break;
            }
            to.write(buf, 0, bytesRead);
            total += bytesRead;
            System.out.format("I am %s, and I copied %d bytes %s.%n", thread, bytesRead, msg);
        }
        return total;
    }


}
