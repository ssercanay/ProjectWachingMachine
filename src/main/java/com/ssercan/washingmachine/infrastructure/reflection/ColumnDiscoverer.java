package com.ssercan.washingmachine.infrastructure.reflection;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;

import java.lang.reflect.Field;
import java.util.Set;

public class ColumnDiscoverer {

  public void discover() {
    Reflections reflections = new Reflections("com.ssercan.washingmachine.domain.machine",
            new FieldAnnotationsScanner());
    Set<Field> columnNotatedFields = reflections.getFieldsAnnotatedWith(Column.class);
    System.out.println("Columns = ");
    for (Field field : columnNotatedFields) {
      Column getAnnotation = field.getDeclaredAnnotation(Column.class);

      System.out.println("  - " + field.getName() + " => " + "unique = " + getAnnotation.unique() +
              " nullable = " + getAnnotation.nullable() + getAnnotation.annotationType().getSimpleName());
    }
  }

}
