package main.java.org.company.interviewtest.threads;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import main.java.org.company.interviewtest.FileManagerImpl;

public class Writer implements Runnable {

    private InputStream input;

    public Writer() {
    }

    public Writer(InputStream inputStream) {
        this.input = inputStream;
    }

    @Override
    public void run() {
        System.out.println("t2 start...");
        // opens dest file ...
        try (FileOutputStream fileOut = new FileOutputStream("C://Users/ft20fd/development/gc-copy.log")) {//args[1]
            // ... and copies from pipe.
            long totalBytes = copy(input, fileOut, "t2", "from pipe into dest file");
            System.out.format("Writing to dest file completed. Total: %d bytes.%n", totalBytes);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                ex.printStackTrace(System.err);
            }
        }
        System.out.println("t2 done.");
    }

    private static long copy(InputStream from, OutputStream to, String thread, String msg)
        throws IOException {
        byte[] buf = new byte[FileManagerImpl.BUFF_SIZE];
        long total = 0;
        while (true) {
            int r = from.read(buf);
            if (r == -1) {
                break;
            }
            to.write(buf, 0, r);
            total += r;
            System.out.format("I am %s, and I copied %d bytes %s.%n", thread, r, msg);
        }
        return total;
    }
}
