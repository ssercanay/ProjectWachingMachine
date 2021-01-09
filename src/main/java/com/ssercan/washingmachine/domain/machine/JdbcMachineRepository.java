package com.ssercan.washingmachine.domain.machine;

import com.ssercan.washingmachine.domain.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JdbcMachineRepository implements MachineRepository{
  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  static final String DB_URL = "jdbc:mysql://localhost:3307/sercan_db";

  //  Database credentials
  static final String USER = "root";
  static final String PASS = "example";
  Connection conn = null;
  Statement stmt = null;
  private String sql;

  @Override
  public List<Machine> findAll() {

    List<Machine> entryList = new ArrayList<>();

    try{
      //STEP 3: Open a connection
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute a query
      stmt = conn.createStatement();

      sql = "SELECT name FROM machines";
      ResultSet rs = stmt.executeQuery(sql);

      //STEP 5: Extract data from result set
      while(rs.next()){
        //Retrieve by column name
        String row = rs.getString("name");
        //Display values

        entryList.add(new Machine(row));
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

  @Override
  public Machine findById(String id) {
    String machineName = null;
    try{
      //STEP 2: Register JDBC driver
      //STEP 3: Open a connection
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute a query
      stmt = conn.createStatement();

      sql = String.format("SELECT name FROM machines WHERE id = '%s'", id);
      ResultSet rs = stmt.executeQuery(sql);

      while(rs.next()){
        //Retrieve by column name
        machineName = rs.getString("name");
        //Display values

      }
      //STEP 5: Extract data from result set
        //Retrieve by column name
        //Display values

      rs.close();
      stmt.close();
      conn.close();

      }catch(Exception se){
        se.printStackTrace();
      }
    
    return new Machine(machineName);
  }

  @Override
  public Machine deleteById(String id) {
    Machine deletedMachine = findById(id);
    try{

      //STEP 3: Open a connection
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute a query
      stmt = conn.createStatement();

      sql = String.format("DELETE FROM machines WHERE id = '%s'", id);
      stmt.executeUpdate(sql);

      stmt.close();
      conn.close();

    }catch(Exception se){
      se.printStackTrace();
    }
    return deletedMachine;
  }

  @Override
  public Machine save(Machine machine) {
    String machineName = machine.getName();
    String id = UUID.randomUUID().toString();

    try{
      //STEP 3: Open a connection
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute a query
      stmt = conn.createStatement();

      //update machine if it is already exist in database
      sql = String.format("INSERT INTO machines(name) VALUES('%s')", machineName);
      stmt.executeUpdate(sql);
      stmt.close();
      conn.close();

    }catch(SQLException se){
      se.printStackTrace();
    }
    return null;
  }
}
