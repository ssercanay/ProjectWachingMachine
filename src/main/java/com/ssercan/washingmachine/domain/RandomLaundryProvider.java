package com.ssercan.washingmachine.domain;

import java.util.ArrayList;
import java.util.List;

public class RandomLaundryProvider implements LaundryProvider {

  public Laundry provide() {
    return new Laundry(createMachineList());
  }

  public List<Machine> createMachineList() {
    List<Machine> machines = new ArrayList<>();

    for (int i = 1; i <= 3; i++) {
      Machine machine = new Machine("Machine " + i);
      machines.add(machine);

    }
    return machines;
  }
}
