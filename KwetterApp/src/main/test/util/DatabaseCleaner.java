package util;
import backend.src.main.java.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.sql.SQLException;

public class DatabaseCleaner {
    private final EntityManager em;

    public DatabaseCleaner(EntityManager entityManager) {
        em = entityManager;
    }

    private static final Class<?>[] ENTITY_TYPES = {
            User.class
    };

    public void clean() {
        em.getTransaction().begin();

        for (Class<?> entityType : ENTITY_TYPES) {
            deleteEntities(entityType);
        }
        em.getTransaction().commit();
        em.close();
    }

    private void deleteEntities(Class<?> entityType) {
        em.createQuery("DELETE FROM " + getEntityName(entityType)).executeUpdate();
    }

    protected String getEntityName(Class<?> clazz) {
        EntityType et = em.getMetamodel().entity(clazz);
        return et.getName();
    }
}
