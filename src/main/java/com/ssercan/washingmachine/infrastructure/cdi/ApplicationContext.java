package com.ssercan.washingmachine.infrastructure.cdi;


import com.ssercan.washingmachine.infrastructure.reflection.Component;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ApplicationContext {
  private final Map<Class<?>, Class<?>> componentsByType;

  public ApplicationContext() {
    componentsByType = findComponents();
  }

  private Set<Class<?>> classesWithComponentMarker () {
    Reflections reflections = new Reflections("com.ssercan.washingmachine");
    Set<Class<?>> componentNotatedClasses = reflections.getTypesAnnotatedWith(Component.class);
    return  componentNotatedClasses;
  }

  public <T> T get(Class<T> klass) throws Exception {
    Class<?> foundComponent = componentsByType.get(klass);
    if ((foundComponent != null)) {
      Constructor<?>[] constructors = foundComponent.getDeclaredConstructors();
      if ((constructors.length == 1)) {
        Constructor<?> constructor = constructors[0];
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        if ((parameterTypes.length == 0 )) {
         return (T) constructor.newInstance();

        } else {
          Class<?> parameterType = parameterTypes[0];
          Object obj = get(parameterType);
          return (T) foundComponent.getConstructor(parameterTypes).newInstance(obj);
          }

        } else {
        throw new RuntimeException("more than one constructor");
      }

      }
    else {
      throw new RuntimeException("Could not find candidate component for: " + klass.getSimpleName());
    }
  }

  public Map<Class<?>, Class<?>> findComponents() {
    Map<Class<?>, Class<?>> mappedComponents = new HashMap<>();
    for (Class<?> klass : classesWithComponentMarker() ) {
      mappedComponents.put(klass, klass);
      for (Class<?> anInterface : klass.getInterfaces() ) {
          mappedComponents.put(anInterface, klass);
      }
    }

    return mappedComponents;
  }

}
