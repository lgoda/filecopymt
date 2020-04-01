package main.java.org.company.interviewtest.threads;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import main.java.org.company.interviewtest.util.FileMultithreadUtil;

public class Reader implements Runnable {

    private OutputStream output;
    private String inputFile;

    public Reader() {
    }

    public Reader(OutputStream outputStream, String inputFile) {
        this.output = outputStream;
        this.inputFile = inputFile;
    }

    public void run() {
        System.out.println("reader start...");
        //opens source file ...
        try (FileInputStream fileIn = new FileInputStream(inputFile)) {
            long totalBytes = FileMultithreadUtil.copy(fileIn, output, Thread.currentThread().getName(), "from src file into pipe");
            System.out.format("Reading from source file completed. Total: %d bytes.%n", totalBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                output.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        System.out.println(Thread.currentThread().getName() + " done.");
    }


}
