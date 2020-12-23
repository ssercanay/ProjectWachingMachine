package com.ssercan.washingmachine.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;


public class FromTextFileLaundryProviderTest {


  @Test
  public void shouldStopWhenFileDoesNotExist() {
    FromTextFileLaundryProvider newProvider = new FromTextFileLaundryProvider("nonExistingFile.txt");
    try{
      newProvider.provide();
      Assertions.fail("Program should not work with random files");
    } catch (Exception e) {
      Assertions.assertThat(e).hasMessageContaining("found");
    }

  }

  @Test
  public void shouldStopWhenIsEmpty() {
    FromTextFileLaundryProvider newProvider = new FromTextFileLaundryProvider("emptyFIle.txt");
    try{
      //call provide method to test
      // take file parameter from constructor
      newProvider.provide();
      Assertions.fail("Program should not work with empty files");
    } catch (Exception e) {
      Assertions.assertThat(e).hasMessageContaining("Error");
    }
    }
}