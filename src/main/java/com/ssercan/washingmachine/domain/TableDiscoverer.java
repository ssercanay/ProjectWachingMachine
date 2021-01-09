package com.ssercan.washingmachine.domain;

import org.reflections.Reflections;
import java.util.Set;

public class TableDiscoverer{

  public void discover() {

    Reflections reflections = new Reflections("com.ssercan.washingmachine.domain.machine");
    Set<Class<?>> challengeClasses = reflections.getTypesAnnotatedWith(Table.class);

    for (Class klass: challengeClasses) {
      Table getAnnotation = (Table) klass.getDeclaredAnnotation(Table.class);
      System.out.println(klass.getSimpleName() + " = " + getAnnotation.name());
    }

  }
}
