package com.ssercan.washingmachine.ui.cli;

import com.ssercan.washingmachine.domain.*;
import com.ssercan.washingmachine.ui.Operation;

import java.util.List;
import java.util.Scanner;


public class UserInterface {

  private final Laundry laundryCenter;
  private final Scanner scanner;

  /**
   * This class is ui for washing machines.
   */
  public UserInterface() {
    this.scanner = new Scanner(System.in);

    LaundryProvider laundryProvider = new FromDatabaseLaundryProvider();
    this.laundryCenter = laundryProvider.provide();
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

      if (operation == Operation.EXIT.getValue()) {
        break;
      } else if (operation == 1) {

        List<Machine> listOfAvailableMachines = laundryCenter.getAvailableMachines();
        if (listOfAvailableMachines.isEmpty()) {
          System.out.println("\nAll machines are occupied!\n");
        } else {
          for (Machine machine : listOfAvailableMachines) {
            System.out.println("\n" + machine + " is available");
          }
          setMachineProgram();
        }

      } else if (operation == 2) {
        List<Machine> listOfRemainedTimeOfOccupiedMachines = laundryCenter.getOccupiedMachines();
        for (Machine machine : listOfRemainedTimeOfOccupiedMachines) {
          System.out.println("\n" + machine.getName() + ":"
                  + " " + machine.getTime() + " minutes left");
        }
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
                    + "[2] - Check remaining times for machines\n";
    System.out.println(menu);
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
      laundryCenter.setMachine(choseMachine, setTime);
      System.out.println("\nWe set your program, please come in " + setTime + " minutes to take your laundries.");
    } else  {
      System.out.println("\nPlease enter a valid machine and time");
    }
  }


}

