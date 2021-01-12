package com.ssercan.washingmachine.infrastructure.reflection;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;

import java.lang.reflect.Field;
import java.util.Set;

public class IdDiscoverer {

  public void discover() {
    Reflections reflections = new Reflections("com.ssercan.washingmachine.domain.machine", new FieldAnnotationsScanner());
    Set<Field> idNotatedFields = reflections.getFieldsAnnotatedWith(Id.class);
    for (Field field : idNotatedFields) {
      System.out.println("Id = [" + field.getAnnotatedType() + "]");
    }
  }

  }

