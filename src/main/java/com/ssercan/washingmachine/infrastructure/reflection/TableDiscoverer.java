package com.ssercan.washingmachine.infrastructure.reflection;

import org.reflections.Reflections;
import java.util.Set;

public class TableDiscoverer{

  public void discover() {

    Reflections reflections = new Reflections("com.ssercan.washingmachine.domain.machine");
    Set<Class<?>> tableNotatedClasses = reflections.getTypesAnnotatedWith(Table.class);

    for (Class<?> klass: tableNotatedClasses) {
      Table getAnnotation = klass.getDeclaredAnnotation(Table.class);
      System.out.println(klass.getSimpleName() + " = " + getAnnotation.name());
    }

  }
}
