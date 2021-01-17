package com.ssercan.washingmachine.rest;

public class UseMachineRequest {
  private int machineId;
  private int machineProgramTime;

  public UseMachineRequest(int machineId, int machineProgramTime) {
    this.machineId = machineId;
    this.machineProgramTime = machineProgramTime;
  }

  public void setMachineId(int machineId) {
    this.machineId = machineId;
  }

  public void setMachineProgramTime(int machineProgramTime) {
    this.machineProgramTime = machineProgramTime;
  }

  public int getMachineId() {
    return machineId;
  }

  public int getMachineProgramTime() {
    return machineProgramTime;
  }
}
