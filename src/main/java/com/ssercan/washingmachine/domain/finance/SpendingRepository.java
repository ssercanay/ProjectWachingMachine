package com.ssercan.washingmachine.domain.finance;

import com.ssercan.washingmachine.domain.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpendingRepository implements JdbcRepository<Spending>{
  Statement stmt = null;
  private String sql;
  private final DB database = new DB();

  @Override
  public List<Spending> findAll(){
    List<Spending> entryList = new ArrayList<>();

    try{
      stmt = database.connectDatabase().createStatement();

      sql = "SELECT * FROM spendings";
      ResultSet rs = stmt.executeQuery(sql);

      //STEP 5: Extract data from result set
      while(rs.next()){
        //Retrieve by column name
        Spending row = (Spending) rs;
        //Display values

        entryList.add(row);
      }
      database.closeConnection();
    } catch(Exception se){
      se.printStackTrace();
    }

    return entryList;
  }

  @Override
  public Spending findById(String id) {
    Spending spending = null;
    try{

      //STEP 4: Execute a query
      Statement stmt = database.connectDatabase().createStatement();
      int idIntegerConversion = Integer.parseInt(id);
      String sql = String.format("SELECT * FROM spendings WHERE id = %d", idIntegerConversion);
      ResultSet rs = stmt.executeQuery(sql);
      while(rs.next()){
        //Retrieve by row
        spending = (Spending) rs;

      }

      database.closeConnection();

    }catch(Exception se){
      se.printStackTrace();
    }

    return spending;
  }

  @Override
  public Spending deleteById(String id) {
    Spending deletedSpending = findById(id);
    try{

      //STEP 4: Execute a query
      stmt = database.connectDatabase().createStatement();
      int idIntegerConversion = Integer.parseInt(id);
      sql = String.format("DELETE FROM spendings WHERE id = %d", idIntegerConversion);
      stmt.executeUpdate(sql);
      database.closeConnection();
    }catch(Exception se){
      se.printStackTrace();
    }
    return deletedSpending;
  }

  @Override
  public Spending save(Spending input) {
    //int spending = (int) input;
    int newId = 0;
    try{
      
     // sql = String.format("INSERT INTO spendings(amount) VALUES(%d)", spending);
      sql = String.format("INSERT INTO spendings VALUES(%s)", input);

      PreparedStatement pInsertOid = database.connectDatabase().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      pInsertOid.executeUpdate();
      ResultSet rs = pInsertOid.getGeneratedKeys();
      if (rs.next()) {
        newId = rs.getInt(1);
      }
      database.closeConnection();

    }catch(SQLException se){
      se.printStackTrace();
    }
    return findById("" + newId);
  }

}
