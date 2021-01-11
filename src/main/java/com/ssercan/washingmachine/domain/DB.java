package com.ssercan.washingmachine.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB {
  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  static final String DB_URL = "jdbc:mysql://localhost:3307/sercan_db";
  static final String USER = "root";
  static final String PASS = "example";
  Connection connect = null;
  Statement statement = null;
  ResultSet resultSet = null;
  private String sql;

  public Connection connectDatabase() {
    try{
      this.connect = DriverManager.getConnection(DB_URL,USER,PASS);
    } catch (Exception e) {
      e.printStackTrace();

    }

    return this.connect;
  }



  public void closeConnection() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {
      e.printStackTrace();

    }
  }
}


