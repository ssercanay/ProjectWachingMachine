package com.ssercan.washingmachine.infrastructure.persistence.jdbc;

import com.ssercan.washingmachine.domain.machine.Machine;
import com.ssercan.washingmachine.domain.machine.MachineRepository;
import com.ssercan.washingmachine.infrastructure.reflection.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JdbcMachineRepository implements MachineRepository {
  private String sql;
  static final String DB_URL = "jdbc:mysql://localhost:3307/sercan_db";


  @Override
  public List<Machine> findAll() {

    List<Machine> entryList = new ArrayList<>();
    sql = "SELECT name, id, remained_time FROM machines";
    double remainedTime = 0;
    try (Connection con = DriverManager.getConnection(DB_URL, "root", "example");
        Statement statement = con.createStatement()) {

      try (ResultSet resultSet = statement.executeQuery(sql)) {

        while (resultSet.next()) {
          String row = resultSet.getString("name");
          int id = resultSet.getInt("id");
          remainedTime = resultSet.getDouble("remained_time");
          Machine toAdd = new Machine(row);
          toAdd.setId(id);

          if (remainedTime > 0){
            toAdd.setDatabaseTime(remainedTime);

          }
          entryList.add(toAdd);

        }

      }
    } catch (SQLException e) {
      e.printStackTrace();

    }
    return entryList;
    }

  @Override
  public Machine findById(Integer id) {

    String machineName = null;
    double remainedTime = 0;
    sql = String.format("SELECT name, remained_time FROM machines WHERE id = %d", id);

    try (Connection con = DriverManager.getConnection(DB_URL, "root", "example");
        Statement statement = con.createStatement(); ) {
      try (ResultSet resultSet = statement.executeQuery(sql); ) {


        while (resultSet.next()) {
          // Retrieve by column name
          machineName = resultSet.getString("name");
          remainedTime = resultSet.getDouble("remained_time");
        }


      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    Machine willReturn = new Machine(machineName);
    if (remainedTime > 0){
      willReturn.setDatabaseTime(remainedTime);

    }
    willReturn.setId(id);

    return willReturn;
    }

  @Override
  public Machine deleteById(Integer id) {
    Machine deletedMachine = findById(id);
    sql = String.format("DELETE FROM machines WHERE id = %d", id);
    try (Connection con = DriverManager.getConnection(DB_URL, "root", "example");
         Statement statement = con.createStatement(); ) {
      statement.executeUpdate(sql);

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return deletedMachine;
  }

  @Override
  public Machine save(Machine machine) {
    int machineId = machine.getId();
    double machineTime = machine.getCurrentTime();
    String machineName = machine.getName();
    int newId = 0;
    if (machine.getId() != 0) {

      sql = String.format("UPDATE machines SET remained_time = %f WHERE id = %d",
              machineTime, machineId);
    } else {

      sql = String.format("INSERT INTO machines(name, remained_time) VALUES('%s', %d)", machineName, 0);
    }

    try (Connection con = DriverManager.getConnection(DB_URL, "root", "example");
         PreparedStatement pInsertOid = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
         ) {
     pInsertOid.executeUpdate();

     try (ResultSet resultSet = pInsertOid.getGeneratedKeys(); ) {

      if (resultSet.next()) {
        newId = resultSet.getInt(1);
      }

    }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return findById(newId);
  }


}


