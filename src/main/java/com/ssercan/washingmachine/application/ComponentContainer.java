package com.ssercan.washingmachine.application;

import com.ssercan.washingmachine.domain.machine.Machine;
import com.ssercan.washingmachine.domain.machine.MachineRepository;
import com.ssercan.washingmachine.infrastructure.reflection.Component;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.Set;

public class ComponentContainer {

  private Set<Class<?>> classesWithComponentMarker () {
    Reflections reflections = new Reflections("com.ssercan.washingmachine");
    Set<Class<?>> componentNotatedClasses = reflections.getTypesAnnotatedWith(Component.class);
    return  componentNotatedClasses;
  }

  public void loadClasses() {
    for (Class<?> klass: classesWithComponentMarker()) {

      Constructor<?>[] constructors = klass.getDeclaredConstructors();

      for (Constructor<?> constructor: constructors) {

        if (constructor.getParameterCount() == 0){
          System.out.println("Repository name: " + klass.getSimpleName());
        }



        for (Class<?> parameter: constructor.getParameterTypes() ) {
          System.out.println(
              "class name: "
                  + klass.getSimpleName()
                  + " cons:"
                  + constructor.getName()
                  + " param:"
                  + parameter.getSimpleName()
                  );

          try{
            ClassLoader classLoader = klass.getClassLoader();
            Class<?> loadedMyClass = classLoader.loadClass(klass.getName());
            Constructor<?> ctor = loadedMyClass.getConstructor(parameter);
            System.out.println("Loaded class name: " + ctor.getName());

          } catch (Exception e) {
            e.printStackTrace();
          }

        }

      }


    }
  }
}
