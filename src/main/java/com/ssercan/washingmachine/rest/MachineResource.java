package com.ssercan.washingmachine.rest;

import com.ssercan.washingmachine.application.LaundryManager;
import com.ssercan.washingmachine.domain.machine.Machine;
import com.ssercan.washingmachine.infrastructure.reflection.Component;

import java.util.List;

@Component
public class MachineResource {
  private LaundryManager laundryManager;

  public  MachineResource(LaundryManager laundryManager) {
    this.laundryManager = laundryManager;
  }

  public List<Machine> getMachines() {
    return this.laundryManager.allMachines();
  }

  public void putUseMachine(UseMachineRequest machineRequest) {
    this.laundryManager.setMachineProgram(machineRequest);
  }

  public Machine postCreateMachine(String name) {
    return this.laundryManager.postCreateMachine(name);
  }
}
