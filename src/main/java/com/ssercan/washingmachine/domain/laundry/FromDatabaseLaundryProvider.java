package com.ssercan.washingmachine.domain.laundry;

import com.ssercan.washingmachine.domain.machine.JdbcMachineRepository;
import com.ssercan.washingmachine.domain.machine.MachineRepository;

public class FromDatabaseLaundryProvider implements LaundryProvider{

  @Override
  public Laundry provide() {
    MachineRepository machineRepository = new JdbcMachineRepository();

    return new Laundry(machineRepository.findAll());
  }
}
