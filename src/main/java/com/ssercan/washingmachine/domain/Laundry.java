package main.java.com.ssercan.washingmachine.domain;

import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Laundry {
  private final List<Machine> machines;

  public Laundry() {
    this.machines = new ArrayList<>();

    readMachines();
  }

  //take from file
  private void readMachines() {
    try(Scanner scanner = new Scanner(Paths.get("laundry.txt"))) {
      while (scanner.hasNext()){
        String row = scanner.nextLine();
        double currentTime = System.currentTimeMillis();
        machines.add(new Machine(row, currentTime));
      }
    } catch (NoSuchFileException e) {
      System.out.println(e.getMessage() + " was not found.");

    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }

  }


  public void setMachine(int choseMachine, int setTime) {
    machines.get(choseMachine - 1).setTime(setTime);
  }

  /*public boolean isThereAvailableMachine() {
    return machines.stream().anyMatch(Machine::isAvailable);
  }

  public List<Machine> getMachines() {
    return this.machines;
  }*/

  public List<Machine> getAvailableMachines() {
    List<Machine> availableMachines = new ArrayList<>();
    for (Machine machine : machines) {
      if (machine.isAvailable()) {
        availableMachines.add(machine);
      }
    }
    return availableMachines;
  }

  public List<Machine> getOccupiedMachines() {
    List<Machine> occupiedMachines = new ArrayList<>();
    for (Machine machine : machines) {
      if (machine.isStillOccupied()) {
        occupiedMachines.add(machine);
      }
    }
    return occupiedMachines;
  }

}

