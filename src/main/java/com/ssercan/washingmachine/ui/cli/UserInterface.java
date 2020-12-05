package main.java.com.ssercan.washingmachine.ui.cli;

import main.java.com.ssercan.washingmachine.domain.Laundry;
import main.java.com.ssercan.washingmachine.domain.Machine;
import main.java.com.ssercan.washingmachine.ui.Operation;

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
    this.laundryCenter = new Laundry();
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
      // convert operation to enum
      // use enum with switch

      //handleOperation(Operation.EXIT);

      if (operation == Operation.EXIT.getValue()) {
        break;
      } else if (operation == 1) {

        List<Machine> listOfAvailableMachines = laundryCenter.getAvailableMachines();
        if (listOfAvailableMachines.isEmpty()) {
          System.out.println("All machines are occupied!\n");
        } else {
          for (Machine machine : listOfAvailableMachines) {
            System.out.println(machine);
          }
          setMachineProgram();
        }

      } else if (operation == 2) {
        List<Machine> listOfRemainedTimeOfOccupiedMachines = laundryCenter.getOccupiedMachines();
        for (Machine machine : listOfRemainedTimeOfOccupiedMachines) {
          System.out.println(machine.getName()
                  + " " + machine.getTime() + " minutes left");
        }
      } else {
        System.out.println("Please enter a valid operation");
      }
    }

  }

  private void showMenu() {
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
    System.out.println(menu);
  }


  private void setMachineProgram() {
    System.out.println("Choose a machine please");
    int choseMachine = scanner.nextInt();
    System.out.println("Set a time please");
    int setTime = scanner.nextInt();
    //get rid of magic numbers
    int machineLowerIndex = 0;
    int machineUpperIndex = 4;
    int timeCantBeLessThanOne = 0;

    if (choseMachine > machineLowerIndex
        && choseMachine < machineUpperIndex && setTime > timeCantBeLessThanOne) {
      laundryCenter.setMachine(choseMachine, setTime);
    } else  {
      System.out.println("Please enter a valid machine and time\n");
    }
  }


}

