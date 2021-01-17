package com.ssercan.washingmachine.infrastructure.persistence.inmemory;

import com.ssercan.washingmachine.domain.machine.Machine;
import com.ssercan.washingmachine.domain.machine.MachineRepository;
import com.ssercan.washingmachine.infrastructure.reflection.Component;

import java.util.*;

@Component
public class InMemoryMachineRepository implements MachineRepository {
  private final Map<Integer, Machine> machines;

  public InMemoryMachineRepository() {
    machines = new HashMap<>();

  }

  @Override
  public List<Machine> findAll() {
    return new ArrayList<>(machines.values());
  }


  @Override
  public Machine findById(Integer id) {
    if (machines.containsKey(id)){
      return machines.get(id);
    } else {
      throw new RuntimeException(id + " is not exist");
    }

  }

  @Override
  public Machine deleteById(Integer id) {
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
    Integer id = machine.getId();

    if (machines.containsValue(machine)){
      machines.replace(id, machine);
    } else {
      machines.put(id, machine);
    }

    return machines.get(id);
  }


}
