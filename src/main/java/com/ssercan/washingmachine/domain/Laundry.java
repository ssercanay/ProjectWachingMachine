package com.ssercan.washingmachine.domain;

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


  private void readMachines() {
    try (Scanner scanner = new Scanner(Paths.get("laundry.txt"))) {
      while (scanner.hasNext()) {
        String row = scanner.nextLine();
        machines.add(new Machine(row));
      }
    } catch (NoSuchFileException e) {
      System.out.println(e.getMessage() + " was not found.");
      System.exit(1);

    } catch (Exception e) {
      System.exit(1);
      System.out.println("Error: " + e.getMessage());
    }

  }

  public void setMachine(int choseMachine, int setTime) {
    machines.get(choseMachine - 1).setTime(setTime);
  }


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

