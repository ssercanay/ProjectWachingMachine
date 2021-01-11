package com.ssercan.washingmachine.domain.machine;

import java.util.*;

public class InMemoryMachineRepository implements MachineRepository{
  private final Map<String, Machine> machines;

  public InMemoryMachineRepository() {
    machines = new HashMap<>();

  }

  @Override
  public List<Machine> findAll() {
    return new ArrayList<>(machines.values());
  }

  @Override
  public Machine findById(String id) {
    if (machines.containsKey(id)){
      return machines.get(id);
    } else {
      throw new RuntimeException(id + " is not exist");
    }

  }

  @Override
  public Machine deleteById(String id) {
    Machine deletedMachine;
    if (machines.containsKey(id)){
      deletedMachine = machines.get(id);
      machines.remove(id);
    } else {
      throw new RuntimeException(id + " is not exist");
    }

    return deletedMachine;
  }

  @Override
  public Machine save(Machine machine) {
    String machineName = machine.getName();
    String id = UUID.randomUUID().toString();

    if (machines.containsValue(machine)){
      machines.replace(id, machine);
    } else {
      machines.put(id, machine);
    }

    return machines.get(machineName);
  }


}
