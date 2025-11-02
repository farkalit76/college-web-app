package com.usman.keycloak.repository;

import com.usman.keycloak.model.MyUserEntity;
import jakarta.persistence.*;
import jakarta.persistence.spi.PersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.Collections;
import java.util.List;

public class MyUserRepository {

    private final EntityManager entityManager;

    public MyUserRepository() {

        PersistenceUnitInfo persistenceUnitInfo = new CustomPersistenceUnitInfo();
        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(persistenceUnitInfo, Collections.emptyMap());
        this.entityManager = entityManagerFactory.createEntityManager();
        System.out.println("MyUserRepository entityManager :"+ entityManager);
    }

    public MyUserEntity findByUsername(String username) {
        try {
            TypedQuery<MyUserEntity> query = entityManager.createQuery(
                    "SELECT u FROM MyUserEntity u WHERE u.username = :username", MyUserEntity.class);
            query.setParameter("username", username);
            MyUserEntity singleResult = query.getSingleResult();
            System.out.println("MyUserRepository findByUsername result :"+ singleResult);
            return singleResult;
        } catch (NoResultException e) {
            System.err.println("MyUserRepository *** findByUsername error :"+ e.getMessage());
            return null;
        }
    }

    public boolean validatePassword(String username, String password) {
        MyUserEntity user = findByUsername(username);
        if (user == null) return false;
        System.out.println("MyUserRepository user.getPassword().equals(password) :"+user.getPassword().equals(password));
        // In production, use BCrypt instead of plain equals!
        //return user.getPassword().equals(password);
        boolean isValidPassword = EncryptUtil.checkPassword(password, user.getPassword());
        System.out.println("MyUserRepository isValidPassword :"+isValidPassword);
        return isValidPassword;
    }

    public MyUserEntity findByEmail(String email) {
        try {
            TypedQuery<MyUserEntity> query = entityManager.createQuery(
                    "SELECT u FROM MyUserEntity u WHERE u.email = :email", MyUserEntity.class);
            TypedQuery<MyUserEntity> email1 = query.setParameter("email", email);
            MyUserEntity singleResult = email1.getSingleResult();
            System.out.println("MyUserRepository findByEmail result :"+ singleResult);
            return singleResult;
        } catch (NoResultException e) {
            System.err.println("MyUserRepository *** findByEmail error :"+ e.getMessage());
            return null;
        }
    }

    public MyUserEntity findByUserId(String id) {
        try {
            TypedQuery<MyUserEntity> query = entityManager.createQuery(
                    "SELECT u FROM MyUserEntity u WHERE u.id = :id", MyUserEntity.class);
            query.setParameter("id", id);
            MyUserEntity singleResult = query.getSingleResult();
            System.out.println("MyUserRepository findByUserId result :"+ singleResult);
            return singleResult;
        } catch (NoResultException e) {
            System.err.println("MyUserRepository *** findByUserId error :"+ e.getMessage());
            return null;
        }
    }

    //For other task:
    public List<MyUserEntity> findAll(int firstResult, int maxResults) {
        TypedQuery<MyUserEntity> query = entityManager.createQuery(
                "SELECT u FROM MyUserEntity u ORDER BY u.id ASC", MyUserEntity.class);
        System.out.println("MyUserRepository findAll query :"+query);
        if (firstResult >= 0) {
            query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    public List<MyUserEntity> searchByUsernameOrEmail(String search, int firstResult, int maxResults) {
        TypedQuery<MyUserEntity> query = entityManager.createQuery(
                "SELECT u FROM MyUserEntity u " +
                        "WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :search, '%')) " +
                        "   OR LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%')) " +
                        "ORDER BY u.id ASC", MyUserEntity.class);
        System.out.println("MyUserRepository searchByUsernameOrEmail query :"+query);
        query.setParameter("search", search);
        if (firstResult >= 0) {
            query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    public int countAll() {
        Query query = entityManager.createQuery("SELECT COUNT(u) FROM MyUserEntity u");
        System.out.println("MyUserRepository countAll query :"+query);
        return ((Number) query.getSingleResult()).intValue();
    }

    public void persist(MyUserEntity entity) {
        System.out.println("MyUserRepository persist  :"+entity);
        entityManager.persist(entity);
    }

    public void remove(MyUserEntity entity) {
        entityManager.remove(entity);
    }
}
