package com.ssercan.washingmachine.domain.finance;

import com.ssercan.washingmachine.infrastructure.reflection.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class SpendingRepository implements JdbcRepository<Spending>{
  Statement stmt = null;
  private String sql;
  static final String DB_URL = "jdbc:mysql://localhost:3307/sercan_db";

  @Override
  public List<Spending> findAll(){
    List<Spending> entryList = new ArrayList<>();
    sql = "SELECT * FROM spendings";

    try (Connection con = DriverManager.getConnection(DB_URL, "root", "example");
         Statement statement = con.createStatement(); ) {

      try (ResultSet resultSet = statement.executeQuery(sql);) {

      //STEP 5: Extract data from result set
      while(resultSet.next()){
        //Retrieve by column name
        Spending row = (Spending) resultSet;
        //Display values

        entryList.add(row);
      }
    } } catch (SQLException e) {
        e.printStackTrace();

      }
    return entryList;
  }

  @Override
  public Spending findById(String id) {
    Spending spending = null;
    int idIntegerConversion = Integer.parseInt(id);

    String sql = String.format("SELECT * FROM spendings WHERE id = %d", idIntegerConversion);

    try (Connection con = DriverManager.getConnection(DB_URL, "root", "example");
         Statement statement = con.createStatement(); ) {

      try (ResultSet resultSet = statement.executeQuery(sql);) {
      //STEP 4: Execute a query
      while(resultSet.next()){
        spending = (Spending) resultSet;

      }


    } } catch (SQLException e) {
      e.printStackTrace();

    }

    return spending;
  }

  @Override
  public Spending deleteById(String id) {
    Spending deletedSpending = findById(id);
    int idIntegerConversion = Integer.parseInt(id);
    sql = String.format("DELETE FROM spendings WHERE id = %d", idIntegerConversion);

    try (Connection con = DriverManager.getConnection(DB_URL, "root", "example");
         Statement statement = con.createStatement(); ) {

        statement.executeUpdate(sql);

      } catch (SQLException e) {
        e.printStackTrace();
      }
    return deletedSpending;
  }

  @Override
  public Spending save(Spending input) {
    int newId = 0;
    sql = String.format("INSERT INTO spendings VALUES(%s)", input);

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
    return findById("" + newId);
  }

}
