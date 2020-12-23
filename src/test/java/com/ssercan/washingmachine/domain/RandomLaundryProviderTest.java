package com.ssercan.washingmachine.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;


public class RandomLaundryProviderTest {

  @Test
  public void machineListCanNotBeEmpty() {
    RandomLaundryProvider randomLaundryProvider = new RandomLaundryProvider();
    boolean isEmpty = randomLaundryProvider.createMachineList().isEmpty();
    Assertions.assertThat(isEmpty).isFalse();

  }


}