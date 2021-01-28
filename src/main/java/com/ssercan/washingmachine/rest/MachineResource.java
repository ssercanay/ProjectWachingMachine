package com.ssercan.washingmachine.rest;

import com.ssercan.washingmachine.application.LaundryManager;
import com.ssercan.washingmachine.domain.machine.Machine;
import com.ssercan.washingmachine.infrastructure.reflection.Component;

import javax.ws.rs.*;
import java.util.List;

@Path("machines")
@Consumes("application/machines+xml")
@Produces("application/machines+xml")
@Component
public class MachineResource {
  private LaundryManager laundryManager;


  public  MachineResource(LaundryManager laundryManager) {
    this.laundryManager = laundryManager;
  }

  @GET
  public List<Machine> getMachine(@PathParam("machineId") Integer id) {
    return this.laundryManager.allMachines();
  }

  public List<Machine> getMachines() {
    return this.laundryManager.allMachines();
  }

  @PUT
  public void putUseMachine(UseMachineRequest machineRequest) {
    this.laundryManager.setMachineProgram(machineRequest);
  }

  public Machine postCreateMachine(String name) {
    return this.laundryManager.postCreateMachine(name);
  }
}
