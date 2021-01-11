package com.ssercan.washingmachine.domain.finance;


import java.util.List;

public interface JdbcRepository<T> {
  List<T> findAll();

  T findById(String id);

  T deleteById(String id);

  // if machine already exists, update
  T save(T input);
}
