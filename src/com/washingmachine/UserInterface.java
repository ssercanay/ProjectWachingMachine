package com.washingmachine;


import java.util.Scanner;

public class UserInterface {

  private final Laundry laundryCenter;
  private final Scanner scanner;

  /**
   * This class is ui for washing machines.
   */
  public UserInterface() {
    this.scanner = new Scanner(System.in);
    this.laundryCenter = new Laundry();
  }

  public void start() {
    welcoming();
  }

  private void welcoming() {
    System.out.println("\nWelcome to laundry room\n");

    String menu =
        "[0] - Exit the room\n"
            + "[1] - Check available machines"
            + "\n"
            + "[2] - Check remaining times for machines"
            + "\n"
            + "[3] - Price list"
            + "\n"
            + "[4] - Earning of the day"
            + "\n"
            + "[5] - List all machines\n";

    while (true) {
      System.out.println(menu);
      System.out.print("Please enter an operation: ");
      int operation = scanner.nextInt();
      if (operation == 0) {
        break;
      } else if (operation == 1) {
        if (laundryCenter.isThereAvailableMachine()) {
          checkAvailableMachines();
        }
      } else if (operation == 2) {
        remainedTime();
      } else {
        System.out.println("Please enter a valid operation");
      }
    }
  }

  private void checkAvailableMachines() {
    if (laundryCenter.isThereAvailableMachine()) {
      printAvailableMachines();
      setMachineProgram();
    } else {
      System.out.println("All machines are occupied!\n");
    }
  }

  private void setMachineProgram() {
    System.out.println("Choose a machine please");
    int choseMachine = scanner.nextInt();
    System.out.println("Set a time please");
    int setTime = scanner.nextInt();
    if (choseMachine > 0 && choseMachine < 4 && setTime > 0) {
      laundryCenter.setMachine(choseMachine, setTime);
    } else  {
      System.out.println("Please enter a valid machine and time\n");
    }
  }

  private void printAvailableMachines() {
    laundryCenter.getMachines().stream()
            .filter(Machine::isAvailable)
            .forEach(System.out::println);
  }

  private void remainedTime() {
    laundryCenter.getMachines().stream()
        .filter(Machine::isStillOccupied)
        // .map(Machine::getTime)
        .forEach(machine -> System.out.println(machine.getName()
                + " " + machine.getTime() + " minutes left"));
  }

}

