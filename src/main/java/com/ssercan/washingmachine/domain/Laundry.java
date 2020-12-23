package com.ssercan.washingmachine.domain;

import java.util.ArrayList;
import java.util.List;

public class Laundry {
  private final List<Machine> machines;

  public Laundry(List<Machine> machines) {
    this.machines = machines;

  }

  public void setMachine(int choseMachine, int setTime) {
    machines.get(choseMachine - 1).setTime(setTime);
  }

  public List<Machine> getAvailableMachines() {
    List<Machine> availableMachines = new ArrayList<>();
    for (Machine machine : machines) {
      if (machine.isAvailable()) {
        availableMachines.add(machine);
      }
    }
    return availableMachines;
  }

  public List<Machine> getOccupiedMachines() {
    List<Machine> occupiedMachines = new ArrayList<>();
    for (Machine machine : machines) {
      if (machine.isStillOccupied()) {
        occupiedMachines.add(machine);
      }
    }
    return occupiedMachines;
  }

}

