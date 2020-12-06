package com.ssercan.washingmachine.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.junit.Assert.*;

public class MachineTest {
  @Test
  public void isOccupiedWhenTimeIsNotZero() {
    Machine testMachine = new Machine("testMachine");
    testMachine.setTime(20);
    Boolean isOccupied = testMachine.isStillOccupied();
    Assertions.assertThat(isOccupied).isTrue();

  }

  @Test
  public  void isAvailableWhenMachineCreated() {
    Machine testMachine = new Machine("testMachine");
    boolean isAvailable = testMachine.isAvailable();
    Assertions.assertThat(isAvailable).isTrue();

  }

  @Test
  public void machineCannotBeSetWhenItIsOccupied() {
    Machine testMachine = new Machine("testMachine");
    testMachine.setTime(20);
    try{
      testMachine.setTime(23);
      Assertions.fail("machine can not be set two times");
    } catch (Exception e) {
      System.out.println("error");
    }

  }

}