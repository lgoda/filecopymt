package org.company.interviewtest;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import main.java.org.company.interviewtest.threads.Reader;
import main.java.org.company.interviewtest.threads.Writer;
import main.java.org.company.interviewtest.util.FileMultithreadUtil;

public class FileCopierFacade {

    private static String READER_NAME = "reader";
    private static String WRITER_NAME = "writer";

    public void copyFile(final String source, final String dest) throws IOException, InterruptedException {
        final PipedOutputStream output = new PipedOutputStream();
        final PipedInputStream input = new PipedInputStream(output);

        final Runnable reader = new Reader(output, source);

        final Runnable writer = new Writer(input, dest);

        Thread readerThread = new Thread(reader);
        readerThread.setName(READER_NAME);
        Thread writerThread = new Thread(writer);
        writerThread.setName(WRITER_NAME);
        readerThread.start();
        writerThread.start();
        //Wait for the 2 threads to terminate
        readerThread.join();
        writerThread.join();
        System.out.println("Threads finished ***********");
        //Check if the copied file is the same that the original
        if (!checkFilesEqual(source, dest)) {
            throw new RuntimeException("Error on copying. SOurce file and destination are not equal");
        }
    }

    //TODO we have to check if both files source and dest are equal
    //TODO the method has not been implemented but we have different options(Compare MD5 checksum, rqw
    // comparison byte by byte or using apache common utils utility methods)
    private boolean checkFilesEqual(final String source, final String dest) {

        return true;
    }


}
