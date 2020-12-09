package org.example.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class JPAUtil {
    private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
    private static EntityManagerFactory factory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return factory;
    }
    public static void shutdown() {
        if (factory != null) {
            factory.close();
        }
    }

    public static Room getRoom(int o) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        // Getting model from db by id
        Room newRoom = entityManager.find(Room.class, o);

        entityManager.getTransaction().commit();
        entityManager.close();
        JPAUtil.shutdown();

        return newRoom;
    }

    public static ArrayList<Room> getListOfRooms(){
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        String hql = "FROM Room";
        Query query = entityManager.createQuery(hql);
        List results = query.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        JPAUtil.shutdown();

        return new ArrayList<Room>(results);
    }
    public static NPC getNPC(int o) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        // Getting model from db by id
        NPC newNPC = entityManager.find(NPC.class, o);

        entityManager.getTransaction().commit();
        entityManager.close();
        JPAUtil.shutdown();

        return newNPC;
    }
    public static Item getItem(int o) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        // Getting model from db by id
        Item newItem = entityManager.find(Item.class, o);

        entityManager.getTransaction().commit();
        entityManager.close();
        JPAUtil.shutdown();

        return newItem;
    }
    public static Chest getChest(int o) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        // Getting model from db by id
        Chest newChest = entityManager.find(Chest.class, o);

        entityManager.getTransaction().commit();
        entityManager.close();
        JPAUtil.shutdown();

        return newChest;
    }
    public static ArrayList<Score> getScore() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        String hql = "FROM Score s ORDER BY s.score DESC";
        Query query = entityManager.createQuery(hql);
        List results = query.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        JPAUtil.shutdown();

        return new ArrayList<Score>(results);
    }
    public static void setScore(String playerName, int scoreValue) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        Score newScore = new Score();
        newScore.setPlayerName(playerName);
        newScore.setScore(scoreValue);
        entityManager.persist(newScore);

        entityManager.getTransaction().commit();
        entityManager.close();
        JPAUtil.shutdown();
    }

    public static Integer getNoOfEntries(String entity) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        long query = (Long) entityManager.createQuery(
                "SELECT COUNT(*) from " + entity).getResultList().get(0);

        entityManager.getTransaction().commit();
        entityManager.close();
        JPAUtil.shutdown();

        return (int) query;
    }
}
