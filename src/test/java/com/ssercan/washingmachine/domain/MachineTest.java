package com.ssercan.washingmachine.domain;

import com.ssercan.washingmachine.domain.machine.Machine;
import org.assertj.core.api.Assertions;
import org.junit.Test;

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
      Assertions.assertThat(e).hasMessageContaining("You can not set");
    }

  }

  @Test
  public void machineMustHaveName() {
    Machine testMachine = new Machine("testMachine");
    testMachine.setTime(30);
    Assertions.assertThat(testMachine.getName()).isEqualTo("testMachine");
  }

  @Test
  public void timeCanNotBeSetToMinus() {
    Machine testMachine = new Machine("testMachine");

    try{
      testMachine.setTime(-30);
      Assertions.fail("Time can not be negative");
    } catch (Exception e) {
      Assertions.assertThat(e).hasMessageContaining("negative");
    }
  }

}