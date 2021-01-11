package com.ssercan.washingmachine.infrastructure.persistence;

import com.ssercan.washingmachine.domain.DB;
import com.ssercan.washingmachine.domain.finance.JdbcRepository;
import com.ssercan.washingmachine.domain.machine.Machine;
import com.ssercan.washingmachine.domain.machine.MachineRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcMachineRepository implements MachineRepository {
  Statement stmt = null;
  private String sql;
  private final DB database = new DB();

  public JdbcMachineRepository() {
    findAll();
  }

  @Override
  public List<Machine> findAll() {

    List<Machine> entryList = new ArrayList<>();

    try{

      stmt = database.connectDatabase().createStatement();

      sql = "SELECT name, id FROM machines";
      ResultSet rs = stmt.executeQuery(sql);

      //STEP 5: Extract data from result set
      while(rs.next()){
        //Retrieve by column name
        String row = rs.getString("name");
        int id = rs.getInt("id");
        //Display values
        Machine toAdd = new Machine(row);
        toAdd.setId(id);
        entryList.add(toAdd);
      }
      database.closeConnection();
    } catch(Exception se){
     se.printStackTrace();
    }//end try

    return entryList;
  }

  @Override
  public Machine findById(String id) {

    String machineName = null;
    double remainedTime = 0;
    int idIntegerConversion = 0;

    try{

      stmt = database.connectDatabase().createStatement();
      Statement statement = database.connectDatabase().createStatement();
      idIntegerConversion = Integer.parseInt(id);
      sql = String.format("SELECT name, remained_time FROM machines WHERE id = %d", idIntegerConversion);
      ResultSet nameResultSet = stmt.executeQuery(sql);

      while(nameResultSet.next()){
        //Retrieve by column name
        machineName = nameResultSet.getString("name");
        remainedTime = nameResultSet.getDouble("remained_time");

      }

      database.closeConnection();

      }catch(Exception se){
        se.printStackTrace();
      }
    Machine willReturn = new Machine(machineName);
    willReturn.setDatabaseTime(remainedTime);
    willReturn.setId(idIntegerConversion);
    return willReturn;
  }

  @Override
  public Machine deleteById(String id) {
    Machine deletedMachine = findById(id);
    try{


      stmt = database.connectDatabase().createStatement();
      int idIntegerConversion = Integer.parseInt(id);
      sql = String.format("DELETE FROM machines WHERE id = %d", idIntegerConversion);
      stmt.executeUpdate(sql);
      database.closeConnection();
    }catch(Exception se){
      se.printStackTrace();
    }
    return deletedMachine;
  }

  @Override
  public Machine save(Machine machine) {
    String machineName = machine.getName();
    double machineTime = machine.getCurrentTime();
    int newId = 0;
    try{
      if (machine.getId() != 0) {

        sql = String.format("UPDATE machines SET remained_time = %f WHERE name = '%s'",
                machineTime, machineName);
      } else {

        sql = String.format("INSERT INTO machines(name, remained_time) VALUES('%s', %f)", machineName, machineTime);
      }
      Connection connection = database.connectDatabase();
      PreparedStatement pInsertOid = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      pInsertOid.executeUpdate();
      ResultSet rs = pInsertOid.getGeneratedKeys();
      if (rs.next()) {
        newId = rs.getInt(1);
      }

      //connection.commit();
      database.closeConnection();

    }catch(SQLException se){
      se.printStackTrace();
    }
    return findById("" + newId);
  }


}


