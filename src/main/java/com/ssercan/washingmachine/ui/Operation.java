package main.java.com.ssercan.washingmachine.ui;

public enum Operation {
  EXIT(0);

  private final int value;
  Operation(int value) {
    this.value = value;
  }

  public int getValue() {
    return this.value;
  }
}
