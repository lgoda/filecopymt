package com.ing.mwchapter;

import java.io.IOException;
import main.java.org.company.interviewtest.FileCopierFacade;

public class App {

  public static void main(String[] args) {
    System.out.println("\"Talent wins games, but teamwork and intelligence win championships.\" – Michael Jordan");

    FileCopierFacade fileCopier = new FileCopierFacade();
    try {
      fileCopier.copyFile("", "");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
