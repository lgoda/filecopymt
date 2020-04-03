package org.company.interviewtest;

import java.io.IOException;

public class App {

    public static void main(String[] args) {
        FileCopierFacade fileCopier = new FileCopierFacade();
        
        try {
            fileCopier.copyFile(args[0], args[1]);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
