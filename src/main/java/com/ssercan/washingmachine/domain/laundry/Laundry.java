package com.ssercan.washingmachine.domain.laundry;

import com.ssercan.washingmachine.infrastructure.persistence.jdbc.JdbcMachineRepository;
import com.ssercan.washingmachine.domain.machine.Machine;

import java.util.ArrayList;
import java.util.List;

public class Laundry {
  private final List<Machine> machines;
  private JdbcMachineRepository jdbcRepository;

  public Laundry(List<Machine> machines) {
    this.machines = machines;

  }


  public void setMachine(int choseMachine, int setTime) {
    Machine chooseMachine = machines.get(choseMachine - 1);
    chooseMachine.setTime(setTime);
    jdbcRepository.save(chooseMachine);
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

  public void setMachineRepository(JdbcMachineRepository jdbcRepository) {
    this.jdbcRepository = jdbcRepository;
  }
}

