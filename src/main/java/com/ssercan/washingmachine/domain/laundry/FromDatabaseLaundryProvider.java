package com.ssercan.washingmachine.domain.laundry;
import com.ssercan.washingmachine.domain.machine.Machine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FromDatabaseLaundryProvider  implements  LaundryProvider{
  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  static final String DB_URL = "jdbc:mysql://localhost:3307/sercan_db";

  //  Database credentials
  static final String USER = "root";
  static final String PASS = "example";

  public Laundry provide() {

    return new Laundry(createMachineList());
  }

  private List<Machine> createMachineList() {
    List<Machine> machines = new ArrayList<>();
    for (String machine : entryListFromDatabase()) {
      machines.add(new Machine(machine));
    }

    return machines;
  }

  private List<String> entryListFromDatabase () {
    List<String> entryList = new ArrayList<>();

    Connection conn = null;
    Statement stmt = null;

    try{
      //STEP 2: Register JDBC driver
      //Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute a query
      stmt = conn.createStatement();
      String sql;
      sql = String.format("SELECT name FROM machines");
      ResultSet rs = stmt.executeQuery(sql);

      //STEP 5: Extract data from result set
      while(rs.next()){
        //Retrieve by column name
        String row = rs.getString("name");
        //Display values
        entryList.add(row);
      }
      //STEP 6: Clean-up environment
      rs.close();
      stmt.close();
      conn.close();
    } catch(Exception se){
      //Handle errors for JDBC
      se.printStackTrace();
    }//Handle errors for Class.forName
    finally{
      //finally block used to close resources
      try{
        if(stmt!=null)
          stmt.close();
      }catch(SQLException ignored){
      }// nothing we can do
      try{
        if(conn!=null)
          conn.close();
      }catch(SQLException se){
        se.printStackTrace();
      }//end finally try
    }//end try
    return entryList;
  }


  }



