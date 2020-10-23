package com.washingmachine;

import java.util.ArrayList;
import java.util.List;

public class Laundry {
  private final List<Machine> machines;
  private double currentTime;

  public Laundry() {
    this.machines = new ArrayList<>();
    this.currentTime = 0;
    defineMachines();
  }

  private void defineMachines() {
    int programTime = 0;
    for (int i = 1; i < 4; i++) {
      String machineName = "Machine " + i;
      this.currentTime = System.currentTimeMillis();
      machines.add(new Machine(machineName, programTime, currentTime));
    }
  }

  public void setProgram() {
    remainedTime();
    if (isThereAvailableMachine()) {
      printAvailableMachines();
    } else {
      System.out.println("All machines are occupied!");
    }
  }

  public void setMachine(int choseMachine, int setTime){
    if (isThereAvailableMachine()) {
      machines.get(choseMachine - 1).setTime(setTime);
    }

  }

  private boolean isThereAvailableMachine() {
    return machines.stream().anyMatch(Machine::isAvailable);
  }

  private void printAvailableMachines() {
    machines.stream()
            .filter(Machine::isAvailable)
            .forEach(System.out::println);
  }

  private void remainedTime() {
    machines.stream()
            .filter(Machine::isStillOccupied)
            .map(Machine::getTime)
            .forEach(System.out::println);

  }
}

