package main.java.org.company.interviewtest.threads;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import main.java.org.company.interviewtest.FileManagerImpl;

public class Reader implements Runnable {

    private OutputStream output;

    public Reader() {
    }

    public Reader(OutputStream outputStream) {
        this.output = outputStream;
    }

    public void run() {
        System.out.println("t1 start...");
        //opens source file ...
        try (FileInputStream fileIn = new FileInputStream("C://Users/ft20fd/development/gc.log")) { //args[0]
            // ... and copies to pipe.
            long totalBytes = copy(fileIn, output, "t1", "from src file into pipe");
            System.out.format("Reading from source file completed. Total: %d bytes.%n", totalBytes);
            fileIn.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                output.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        System.out.println("t1 done.");
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
