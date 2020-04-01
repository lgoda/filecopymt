package main.java.org.company.interviewtest.threads;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import main.java.org.company.interviewtest.util.FileMultithreadUtil;

public class Writer implements Runnable {

    private InputStream input;
    private String destFile;

    public Writer() {
    }

    public Writer(InputStream inputStream, String destFile) {
        this.input = inputStream;
        this.destFile = destFile;
    }

    @Override
    public void run() {
        System.out.println("t2 start...");
        // opens dest file ...
        try (FileOutputStream fileOut = new FileOutputStream(destFile)) {
            // ... and copies from pipe.
            long totalBytes = FileMultithreadUtil.copy(input, fileOut, Thread.currentThread().getName(), "from pipe into dest file");
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
        System.out.println(Thread.currentThread().getName() + " done.");
    }

}
