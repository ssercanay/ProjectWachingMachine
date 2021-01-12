package com.ssercan.washingmachine.domain.machine;

import java.util.List;


public interface MachineRepository {

  List<Machine> findAll();

  Machine findById(String id);

  Machine deleteById(String id);

  // if machine already exists, update
  Machine save(Machine machine);
}