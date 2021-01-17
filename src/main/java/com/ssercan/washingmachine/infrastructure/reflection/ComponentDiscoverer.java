package com.ssercan.washingmachine.infrastructure.reflection;

import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.Set;

public class ComponentDiscoverer {

  public void discover() {
    Reflections reflections = new Reflections("com.ssercan.washingmachine");
    Set<Class<?>> tableNotatedClasses = reflections.getTypesAnnotatedWith(Component.class);

    for (Class<?> klass: tableNotatedClasses) {

      for (Constructor<?> constructor: klass.getDeclaredConstructors()) {
        for (Class<?> parameter: constructor.getParameterTypes() ) {
          System.out.println(klass.getSimpleName() + " needs " + parameter.getSimpleName());

        }

      }


    }
  }}