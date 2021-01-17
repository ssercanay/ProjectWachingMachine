package com.ssercan.washingmachine.infrastructure.persistence.hibernate;

import com.ssercan.washingmachine.domain.machine.Machine;
import com.ssercan.washingmachine.domain.machine.MachineRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class HibernateMachineRepository implements MachineRepository {

  private EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
  private EntityManager em = emf.createEntityManager();

  @Override
  public List<Machine> findAll() {
    em.getTransaction().begin();
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<Machine> criteria = builder.createQuery(Machine.class);
    criteria.from(Machine.class);
    List<Machine> entryList = em.createQuery(criteria).getResultList();

    em.getTransaction().commit();
    em.close();
    return entryList;
  }

  @Override
  public Machine findById(Integer id) {
    em.getTransaction().begin();
    Machine machine = em.find(Machine.class, id);

    em.getTransaction().commit();
    em.close();
    return machine;
  }

  @Override
  public Machine deleteById(Integer id) {
    Machine deletedMachine = findById(id);
    em.getTransaction().begin();
    em.remove(id);
    em.getTransaction().commit();
    em.close();
    return deletedMachine;
  }

  @Override
  public Machine save(Machine input) {
    em.getTransaction().begin();

    if (!em.contains(input)) {
      em.persist(input);
    } else {
      input.setDatabaseTime(input.getTime());
    }
    em.getTransaction().commit();
    em.close();
    return input;
  }
}
