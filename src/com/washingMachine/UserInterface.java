package com.washingMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
  private final List<Machine> machines;
  private final Scanner scanner;
  private double currentTime;
  
  /**
   * This class is ui for washing machines.
   */
  public UserInterface() {
    this.machines = new ArrayList<>();
    this.scanner = new Scanner(System.in);
    this.currentTime = 0;
  }

  public void start() {
    defineMachines();
    welcoming();

  }

  private void defineMachines() {
    int programTime = 0;
    for (int i = 1; i < 4; i++) {
      String machineName = "machine " + i;
      this.currentTime = System.currentTimeMillis();
      machines.add(new Machine(machineName, programTime, currentTime));
    }
  }

  private void welcoming() {
    System.out.println("Welcome to laundry room"
            + "\nWhat would you like to do?");

    String menu = "[0] - Exit the room\n"
            + "[1] - Check available machines" + "\n"
            + "[2] - Abort machine" + "\n"
            + "[3] - Price list" + "\n"
            + "[4] - Earning of the day" + "\n"
            + "[2] - List all machines";
    while (true) {
      System.out.println(menu);
      System.out.print("Please enter an operation: ");
      int operation = scanner.nextInt();
      if (operation == 0) {
        break;
      } else if (operation == 1) {
        setProgram();
      } else {
        System.out.println("Please enter a valid operation");
      }

    }
  }

  private void setProgram() {
    remainedTime();
    if (isThereAvailableMachine()) {
      System.out.println("Choose a machine please!");
      int choseMachine = scanner.nextInt();
      System.out.println("Set a time please!");
      int setTime = scanner.nextInt();
      machines.get(choseMachine - 1).setTime(setTime);
    } else {
      System.out.println("All machines are occupied!");
    }
    // methods do not do sout!!

  }

  private boolean isThereAvailableMachine() {
    boolean check = false;
    for (Machine machine : machines) {
      if (machine.isAvailable()) {
        System.out.println(machine.getName() + " is available");
        check = true;
        System.out.println();
      }
    }
    return check;
  }

  private void remainedTime() {
    for (Machine machine : machines) {
      if (machine.isStillOccupied()) {
        System.out.println(machine.getTime());
      }
    }
  }
}
/*
    public void remainedTime(){
      double remained = System.currentTimeMillis();
      for (Machine machine:machines) {
        remained = remained + machine.getTime()*60000;
        while ((remained- System.currentTimeMillis()) != 0){
          machine.setTime((int)((remained- System.currentTimeMillis())/60000));

        }
      }



    }*/



