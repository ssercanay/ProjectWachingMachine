package com.ssercan.washingmachine.domain;

import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FromTextFileLaundryProvider implements LaundryProvider {
  String fileName;
  public FromTextFileLaundryProvider(String file) {
    this.fileName = file;

  }

  public Laundry provide() {

    return new Laundry(createMachineList());
  }

  public List<Machine> createMachineList() {
    List<Machine> machines = new ArrayList<>();
    try (Scanner scanner = new Scanner(Paths.get(fileName))) {
      while (scanner.hasNext()) {
        String row = scanner.nextLine();
        machines.add(new Machine(row));
      }
    } catch (NoSuchFileException e) {
      System.out.println(e.getMessage() + " was not found.");
      System.exit(1);

    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
      System.exit(2);
    }
    return machines;
  }
  
}
