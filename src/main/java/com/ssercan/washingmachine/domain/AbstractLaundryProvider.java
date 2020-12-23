package com.ssercan.washingmachine.domain;

import java.util.List;

public abstract class AbstractLaundryProvider implements LaundryProvider{

  @Override
  public Laundry provide() {

    return new Laundry(createMachineList());
  }

  public abstract List<Machine> createMachineList();
}
