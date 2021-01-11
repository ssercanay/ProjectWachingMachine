package com.ssercan.washingmachine.infrastructure.persistence;

import com.ssercan.washingmachine.domain.machine.Machine;
import com.ssercan.washingmachine.domain.machine.MachineRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcMachineRepository implements MachineRepository {
  private String sql;
  static final String DB_URL = "jdbc:mysql://localhost:3307/sercan_db";


  @Override
  public List<Machine> findAll() {

    List<Machine> entryList = new ArrayList<>();
    sql = "SELECT name, id FROM machines";

    try (Connection con = DriverManager.getConnection(DB_URL, "root", "example");
        Statement statement = con.createStatement(); ) {

      try (ResultSet rs = statement.executeQuery(sql);) {

        while (rs.next()) {
          String row = rs.getString("name");
          int id = rs.getInt("id");

          Machine toAdd = new Machine(row);
          toAdd.setId(id);
          entryList.add(toAdd);
        }

      } catch (SQLException e) {
        e.printStackTrace();
      }
    } catch (SQLException e) {
      e.printStackTrace();

    }
    return entryList;
    }

  @Override
  public Machine findById(String id) {

    String machineName = null;
    double remainedTime = 0;
    int idIntegerConversion = 0;
    sql = String.format("SELECT name, remained_time FROM machines WHERE id = %d", idIntegerConversion);
    try (Connection con = DriverManager.getConnection(DB_URL, "root", "example");
        Statement statement = con.createStatement(); ) {
      try (ResultSet resultSet = statement.executeQuery(sql); ) {

        idIntegerConversion = Integer.parseInt(id);


        while (resultSet.next()) {
          // Retrieve by column name
          machineName = resultSet.getString("name");
          remainedTime = resultSet.getDouble("remained_time");
        }


      } catch (SQLException e) {
        e.printStackTrace();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    Machine willReturn = new Machine(machineName);
    willReturn.setDatabaseTime(remainedTime);
    willReturn.setId(idIntegerConversion);

    return willReturn;
    }

  @Override
  public Machine deleteById(String id) {
    Machine deletedMachine = findById(id);
    int idIntegerConversion = Integer.parseInt(id);
    sql = String.format("DELETE FROM machines WHERE id = %d", idIntegerConversion);
    try (Connection con = DriverManager.getConnection(DB_URL, "root", "example");
         Statement statement = con.createStatement(); ) {
      try (ResultSet resultSet = statement.executeQuery(sql); ) {

    } catch (SQLException e) {
        e.printStackTrace();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return deletedMachine;
  }

  @Override
  public Machine save(Machine machine) {
    String machineName = machine.getName();
    double machineTime = machine.getCurrentTime();
    int newId = 0;
    if (machine.getId() != 0) {

      sql = String.format("UPDATE machines SET remained_time = %f WHERE name = '%s'",
              machineTime, machineName);
    } else {

      sql = String.format("INSERT INTO machines(name, remained_time) VALUES('%s', %f)", machineName, machineTime);
    }

    try (Connection con = DriverManager.getConnection(DB_URL, "root", "example");
         PreparedStatement pInsertOid = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
         ) {
     pInsertOid.executeUpdate();

     try (ResultSet resultSet = pInsertOid.getGeneratedKeys(); ) {

      if (resultSet.next()) {
        newId = resultSet.getInt(1);
      }

    }catch (SQLException e) {
        e.printStackTrace();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return findById("" + newId);
  }


}


