package com.ssercan.washingmachine.domain;

import com.ssercan.washingmachine.domain.laundry.Laundry;
import com.ssercan.washingmachine.domain.machine.Machine;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LaundryTest {

  @Test
  public void machinesShouldNotBeAvailableAfterAllMachinesAreSet() {
    List<Machine> machines = getMachines();

    Laundry newLaundryTest = new Laundry(machines);

    int setTime = 20;
    for (int i = 1; i <= machines.size(); i++) {
      newLaundryTest.setMachine(i, setTime);
    }
    Assertions.assertThat(newLaundryTest.getAvailableMachines()).isEmpty();

  }

  @Test
  public void machineShouldBeOccupiedAfterItIsSet() {
    List<Machine> machines = getMachines();

    Laundry newLaundryTest = new Laundry(machines);
    int chooseMachine1 = 1;
    int setTime = 20;

    newLaundryTest.setMachine(chooseMachine1, setTime);

    Assertions.assertThat(newLaundryTest.getOccupiedMachines()).isNotEmpty();

  }

  private List<Machine> getMachines() {
    List<Machine> machines = new ArrayList<>();

    for (int i = 1; i <= 3; i++) {
      Machine machine = new Machine("Machine " + i);
      machines.add(machine);

    }
    return machines;
  }

}