package com.ssercan.washingmachine.domain;

public class Machine {
  private final String name;
  private double time;
  private final double currentTime;


  public Machine(String name) {
    this.name = name;
    this.currentTime = System.currentTimeMillis();
  }

  public void setTime(double time) {

    if (isAvailable()) {
      if (time > 0) {
      this.time = time;
      } else {
        throw new RuntimeException("You can not set time to a negative number");
      }
    } else {
      throw new RuntimeException("You can not set time to occupied machines");

    }
  }
  public double getTime() {
    return this.time;
  }

  public String getName() {
    return this.name;
  }

  public boolean isAvailable() {
    return getTime() == 0;
  }

  private void remainedTime(double time) {
      this.time = time;
  }

  private double getCurrentTime() {
    return this.currentTime;
  }

  private boolean isOccupied() {
    return !isAvailable();
  }

  private void calculateRemainedTime() {
    double totalTime = getCurrentTime() + getTime() * 60000;
    double remainedTime = totalTime - System.currentTimeMillis();
    this.remainedTime((int) (remainedTime / 60000));
  }

  /**
   * This method checks whether machine is occupied or not.
   */

  public boolean isStillOccupied() {
    if (isOccupied()) {
      calculateRemainedTime();
      return true;
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return getName();
  }
}
