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
      this.currentTime = System.currentTimeMillis();
      machines.add(new Machine(("Machine " + i), programTime, currentTime));
    }
  }

  public void setMachine(int choseMachine, int setTime) {
    machines.get(choseMachine - 1).setTime(setTime);
  }

  public boolean isThereAvailableMachine() {
    return machines.stream().anyMatch(Machine::isAvailable);
  }

  public List<Machine> getMachines() {
    return this.machines;
  }


}

