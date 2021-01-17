package com.ssercan.washingmachine;

import com.ssercan.washingmachine.application.LaundryManager;
import com.ssercan.washingmachine.domain.machine.MachineRepository;
import com.ssercan.washingmachine.infrastructure.persistence.jdbc.JdbcMachineRepository;

import com.ssercan.washingmachine.rest.MachineResource;
import com.ssercan.washingmachine.ui.cli.UserInterface;

import java.util.Scanner;

public class WashingMachine {

  public static void main(String[] args) {
    //program should have following functionalities
    //is it occupied or not
    //how many minutes left
    //user should able to add time
    Scanner scanner = new Scanner(System.in);
    MachineRepository machineRepository = new JdbcMachineRepository();
    LaundryManager laundryManager = new LaundryManager(machineRepository);
    MachineResource machineResource = new MachineResource(laundryManager);

    UserInterface userInterface = new UserInterface(scanner, machineResource);
    userInterface.start();

   // TableDiscoverer tableDiscoverer = new TableDiscoverer();
   // tableDiscoverer.discover();
  }
}

