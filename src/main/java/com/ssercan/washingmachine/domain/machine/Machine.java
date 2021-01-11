package com.ssercan.washingmachine.domain.machine;

import com.ssercan.washingmachine.infrastructure.reflection.Column;
import com.ssercan.washingmachine.infrastructure.reflection.Id;
import com.ssercan.washingmachine.infrastructure.reflection.Table;

@Table(name = "Machines")
public class Machine {
  @Column(name = "name", unique = true, nullable = false)
  private final String name;
  private double time;
  private final double currentTime;
  @Id
  @Column(name = "id", unique = true, nullable = false)
  private int id;
  @Column(name = "in_use")
  private boolean inUse;
  private  double databaseTime;

  public Machine(String name) {
    this.name = name;
    this.currentTime = System.currentTimeMillis();

  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return this.id;
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

  public void setDatabaseTime(double databaseTime) {
    this.databaseTime = databaseTime;
  }

  private double getDatabaseTime() {
    System.out.println(this.databaseTime);
    return  this.databaseTime;
  }

  public double getCurrentTime() {
    return this.currentTime + getTime() * 60000;
  }

  private boolean isOccupied() {
    return !isAvailable();
  }

  private void calculateRemainedTime() {
    double totalTime = getDatabaseTime();
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
