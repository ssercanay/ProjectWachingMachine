package com.ssercan.washingmachine;

import com.ssercan.washingmachine.ui.cli.UserInterface;

import java.util.Timer;

public class WashingMachine {
  static int interval;
  static Timer timer;
  public static void main(String[] args) {
    //program should have following functionalities
    //is it occupied or not
    //how many minutes left
    //user should able to add time
    UserInterface userInterface = new UserInterface();
    userInterface.start();
  }
}

