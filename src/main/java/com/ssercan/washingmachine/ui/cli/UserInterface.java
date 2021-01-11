package com.ssercan.washingmachine.ui.cli;

import com.ssercan.washingmachine.domain.laundry.*;
import com.ssercan.washingmachine.infrastructure.persistence.JdbcMachineRepository;
import com.ssercan.washingmachine.domain.machine.Machine;
import com.ssercan.washingmachine.rest.MachineResource;
import com.ssercan.washingmachine.rest.UseMachineRequest;

import java.util.Scanner;


public class UserInterface {

  private final Scanner scanner;
  private final MachineResource machineResource;

  /**
   * This class is ui for washing machines.
   */
  public UserInterface(Scanner scanner, MachineResource machineResource) {
    this.scanner = scanner;
    this.machineResource = machineResource;

    LaundryProvider laundryProvider = new FromDatabaseLaundryProvider();
    Laundry laundryCenter = laundryProvider.provide();
    //JdbcMachineRepository jdbcRepository = new JdbcMachineRepository();
    //laundryCenter.setMachineRepository(jdbcRepository);
  }

  public void start(String provider) {
    LaundryProvider laundryProvider;

    if (FromTextFileLaundryProvider.class.getSimpleName().contains(provider)) {
      laundryProvider = new FromTextFileLaundryProvider("emptyFIle.txt");
    } else if (RandomLaundryProvider.class.getSimpleName().contains(provider)) {
      laundryProvider = new RandomLaundryProvider();
    }
    System.out.println("\nWelcome to laundry room\n");
    setOperation();
  }

  public void start() {
    System.out.println("\nWelcome to laundry room\n");
    setOperation();

  }

  private void setOperation() {


    while (true) {
      showMenu();
      System.out.print("Please enter an operation: ");
      int operation = scanner.nextInt();

      if (operation == 0) {
        break;
      } else if (operation == 1) {

        if (machineResource.getMachines().isEmpty()) {
          System.out.println("\nAll machines are occupied!\n");
        } else {
          for (Machine machine : machineResource.getMachines()) {
            System.out.println("\n" + machine + " is available");
          }
          setMachineProgram();
        }

      } else if (operation == 2) {
        for (Machine machine : machineResource.getMachines()) {
          System.out.println("\n" + machine.getName() + ":" + " "
                  + machine.getTime() + " minutes left");
        }
      } else if (operation == 3) {
        addMachine();

      } else {
        System.out.println("\nPlease enter a valid operation\n");
      }
    }

  }

  private void showMenu() {
    String menu =
            "\n[0] - Exit the room\n"
                    + "[1] - Check available machines"
                    + "\n"
                    + "[2] - Check remaining times for machines\n"
                    + "[3] - Add new machine\n";
    System.out.println(menu);
  }


  private void addMachine() {
    scanner.nextLine();
    System.out.print("Please add name of machine: ");
    String newMachine = scanner.nextLine();
    machineResource.postCreateMachine(newMachine);
  }

  private void setMachineProgram() {
    System.out.print("\nChoose a machine please: ");
    int choseMachine = scanner.nextInt();
    System.out.print("\nSet a time please: ");
    int setTime = scanner.nextInt();
    int machineLowerIndex = 0;
    int machineUpperIndex = 4;
    int timeCantBeLessThanOne = 0;

    if (choseMachine > machineLowerIndex
        && choseMachine < machineUpperIndex && setTime > timeCantBeLessThanOne) {
      machineResource.putUseMachine(new UseMachineRequest(choseMachine, setTime));
      System.out.println("\nWe set your program, please come in " + setTime
              + " minutes to take your laundries.");
    } else  {
      System.out.println("\nPlease enter a valid machine and time");
    }
  }


}

