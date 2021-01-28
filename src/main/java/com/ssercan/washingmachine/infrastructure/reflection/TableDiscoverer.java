package com.ssercan.washingmachine.infrastructure.reflection;

import com.ssercan.washingmachine.ui.Test;
import org.reflections.Reflections;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.Set;

public class TableDiscoverer{
  private final Reflections reflections = new Reflections("com.ssercan.washingmachine.domain.machine");


  public void discoverTable() {
    Set<Class<?>> tableNotatedClasses = reflections.getTypesAnnotatedWith(Table.class);

    for (Class<?> klass: tableNotatedClasses) {
      Table getAnnotation = klass.getDeclaredAnnotation(Table.class);
      System.out.println(klass.getSimpleName() + " = " + getAnnotation.name());
      discoverColumn(klass);
      discoverId(klass);
    }

  }

  private void discoverColumn(Class<?> klass) {
    System.out.println("Columns = ");

      for (Field field: klass.getDeclaredFields()) {
        if (field.isAnnotationPresent(Column.class)) {
          Column getAnnotation = field.getDeclaredAnnotation(Column.class);
          System.out.println("  - " + field.getName() + " => " + "unique = " + getAnnotation.unique() +
                  " nullable = " + getAnnotation.nullable());
        }
      }

  }

  private void discoverId(Class<?> klass) {

      for (Field field : klass.getDeclaredFields()) {
        if (field.isAnnotationPresent(Id.class)) {
          System.out.println("Id = [" + field.getAnnotatedType() + "]");
        }
      }

    }
}
