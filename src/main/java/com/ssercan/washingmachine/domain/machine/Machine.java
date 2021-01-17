package com.ssercan.washingmachine.domain.machine;

import com.ssercan.washingmachine.infrastructure.reflection.Column;
import com.ssercan.washingmachine.infrastructure.reflection.Id;
import com.ssercan.washingmachine.infrastructure.reflection.Table;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.Serializable;

@Entity
@Table(name = "Machines")
public class Machine implements Serializable {
  @Column(name = "name", unique = true, nullable = false)
  private String name;
  private double time;

  @Transient
  private double currentTime;
  @Id
  @Column(name = "id", unique = true, nullable = false)
  private int id;
  @Column(name = "in_use")
  private boolean inUse;

  public Machine(String name) {
    this.name = name;
    this.currentTime = System.currentTimeMillis();

  }

  public Machine() {

  }

  public void setId(int id) {
    this.id = id;
  }

  @javax.persistence.Id
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


  public void setDatabaseTime(double time) {
    if (isAvailable()) {
      if (time > 0) {
        this.time = time;
        //calculateRemainedTime(); is added to test remained time
        calculateRemainedTime();
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

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  @Transient
  public boolean isAvailable() {
    return getTime() == 0;
  }

  private void remainedTime(double time) {
    if (time < 0) {
      this.time = 0;
    } else {
      this.time = time;
      }
  }


  public double getCurrentTime() {
    return this.currentTime + getTime() * 60000;
  }

  @Transient
  private boolean isOccupied() {
    return !isAvailable();
  }

  private void calculateRemainedTime() {
    double totalTime = getTime();
    double remainedTime = totalTime - System.currentTimeMillis();
    remainedTime((int) (remainedTime / 60000));
  }

  /**
   * This method checks whether machine is occupied or not.
   */

  @Transient
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
