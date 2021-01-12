package com.ssercan.washingmachine.domain;


import java.util.List;

public interface Repository<ID, T> {

  List<T> findAll();

  T findById(ID id);

  T deleteById(ID id);

  // if machine already exists, update
  T save(T input);
}
