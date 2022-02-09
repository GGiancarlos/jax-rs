package org.example.jpa.repository;

import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Singleton
public class EntityManagerInitializer {

    EntityManager entityManager = entityManager();

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager entityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2-alumno");
        return emf.createEntityManager();
    }
}
