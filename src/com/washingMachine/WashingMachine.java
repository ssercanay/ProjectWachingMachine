package com.washingMachine;

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










    /*Scanner sc = new Scanner(System.in);
    System.out.print("Input seconds => : ");
    String secs = sc.nextLine();
    int delay = 1000;
    int period = 1000;
    timer = new Timer();
    interval = Integer.parseInt(secs);
    System.out.println(secs);
    timer.scheduleAtFixedRate(new TimerTask() {

      public void run() {
        System.out.println(setInterval());

      }
    }, delay, period);
  }

  private static final int setInterval() {
    if (interval == 1)
      timer.cancel();
    return --interval;
  }*/

