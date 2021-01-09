package com.ssercan.washingmachine.domain.laundry;

import com.ssercan.washingmachine.domain.machine.Machine;

import java.util.List;

public abstract class AbstractLaundryProvider implements LaundryProvider{

  @Override
  public Laundry provide() {

    return new Laundry(createMachineList());
  }

  public abstract List<Machine> createMachineList();
}
