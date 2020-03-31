package main.java.org.company.interviewtest;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import main.java.org.company.interviewtest.threads.Reader;
import main.java.org.company.interviewtest.threads.Writer;

public class FileCopierFacade {

    public void copyFile(final String source, final String dest) throws IOException {
        final PipedOutputStream output = new PipedOutputStream();
        final PipedInputStream input = new PipedInputStream(output);

        final Runnable reader = new Reader(output);

        final Runnable writer = new Writer(input);

        new Thread(reader).start();
        new Thread(writer).start();
    }


}
