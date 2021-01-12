package com.ssercan.washingmachine.application;

import com.ssercan.washingmachine.domain.machine.Machine;
import com.ssercan.washingmachine.domain.machine.MachineRepository;
import com.ssercan.washingmachine.infrastructure.reflection.Component;
import com.ssercan.washingmachine.rest.UseMachineRequest;
import java.util.List;

@Component
public class LaundryManager {
  private final MachineRepository machineRepository;

  public LaundryManager(MachineRepository machineRepository) {
    this.machineRepository = machineRepository;
  }

  public List<Machine> allMachines() {
    return machineRepository.findAll();
  }

  public void setMachineProgram(UseMachineRequest machineRequest) {
    Machine chooseMachine = machineRepository.findById(machineRequest.getMachineId());
    chooseMachine.setTime(machineRequest.getMachineProgramTime());
    machineRepository.save(chooseMachine);
  }


  public Machine postCreateMachine(String name) {
    return machineRepository.save(new Machine(name));
  }
}
