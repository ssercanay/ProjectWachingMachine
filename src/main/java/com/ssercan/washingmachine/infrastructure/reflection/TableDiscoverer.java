package com.ssercan.washingmachine.infrastructure.reflection;

import org.reflections.Reflections;
import java.lang.reflect.Field;
import java.util.Set;

public class TableDiscoverer{
  private final Reflections reflections = new Reflections("com.ssercan.washingmachine.domain.machine");
  private final Set<Class<?>> tableNotatedClasses = reflections.getTypesAnnotatedWith(Table.class);;


  public void discoverTable() {

    for (Class<?> klass: tableNotatedClasses) {
      Table getAnnotation = klass.getDeclaredAnnotation(Table.class);
      System.out.println(klass.getSimpleName() + " = " + getAnnotation.name());
    }

  }

  public void discoverColumn() {
    System.out.println("Columns = ");
    for (Class<?> klass: tableNotatedClasses) {
      for (Field field: klass.getDeclaredFields()) {
        if (field.isAnnotationPresent(Column.class)) {
          Column getAnnotation = field.getDeclaredAnnotation(Column.class);
          System.out.println("  - " + field.getName() + " => " + "unique = " + getAnnotation.unique() +
                  " nullable = " + getAnnotation.nullable());
        }
      }
    }
  }

  public void discoverId() {
    for (Class<?> klass : tableNotatedClasses) {
      for (Field field : klass.getDeclaredFields()) {
        if (field.isAnnotationPresent(Id.class)) {
          System.out.println("Id = [" + field.getAnnotatedType() + "]");
        }
      }
    }
    }
}
