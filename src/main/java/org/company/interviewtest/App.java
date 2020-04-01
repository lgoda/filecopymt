package org.company.interviewtest;

import java.io.IOException;

public class App {

    public static void main(String[] args) {
        FileCopierFacade fileCopier = new FileCopierFacade();
        // "C://Users/ft20fd/development/gc.log" C://Users/ft20fd/development/gc-tmp.log
        try {
            fileCopier.copyFile(args[0], args[1]);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
