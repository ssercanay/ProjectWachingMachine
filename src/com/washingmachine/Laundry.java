package com.washingmachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Laundry {
  private final List<Machine> machines;
  private final Scanner scanner;
  private double currentTime;

  public Laundry() {
    this.machines = new ArrayList<>();
    this.scanner = new Scanner(System.in);
    this.currentTime = 0;
  }

  public void defineMachines() {
    int programTime = 0;
    for (int i = 1; i < 4; i++) {
      String machineName = "machine " + i;
      this.currentTime = System.currentTimeMillis();
      machines.add(new Machine(machineName, programTime, currentTime));
    }
  }

  public void setProgram() {
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

