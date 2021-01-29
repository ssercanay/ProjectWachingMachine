package com.ssercan.washingmachine;

import com.ssercan.washingmachine.infrastructure.cdi.ApplicationContext;

import com.ssercan.washingmachine.rest.MachineResource;
import com.ssercan.washingmachine.ui.cli.UserInterface;

import java.util.Scanner;

public class WashingMachine {

  public static void main(String[] args) throws Exception {
    //program should have following functionalities
    //is it occupied or not
    //how many minutes left
    //user should able to add time
    Scanner scanner = new Scanner(System.in);
    //JdbcMachineRepository jdbcMachineRepository = new JdbcMachineRepository();
    //MachineRepository machineRepository = new HibernateMachineRepository();
    //HibernateMachineRepository hibernateMachineRepository = new HibernateMachineRepository();
    //LaundryManager laundryManager = new LaundryManager(machineRepository);
    //MachineResource machineResource = new MachineResource(laundryManager);
    ApplicationContext componentContainer = new ApplicationContext();
    MachineResource component = componentContainer.get(MachineResource.class);
    UserInterface userInterface = new UserInterface(scanner, component);
    userInterface.start();


  }
}

