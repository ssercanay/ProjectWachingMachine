package com.ssercan.washingmachine;

import com.ssercan.washingmachine.domain.TableDiscoverer;
import com.ssercan.washingmachine.ui.cli.UserInterface;

public class WashingMachine {

  public static void main(String[] args) throws Exception {
    //program should have following functionalities
    //is it occupied or not
    //how many minutes left
    //user should able to add time

    UserInterface userInterface = new UserInterface();
    userInterface.start();
  }
}

