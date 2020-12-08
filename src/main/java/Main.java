import org.example.JPAUtil;
import org.example.Room;

import javax.persistence.EntityManager;

import java.util.*;

public class Main
{
    static Scanner stdin = new Scanner(System.in);
    public static void main(String[] args)
    {
        System.out.print("Test");


        ormTest();
    }



    public static void ormTest() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        Room newRoom = new Room();
        newRoom.setName("Anor Londo");
        newRoom.setDescription("An abandoned city of Gods.");
        newRoom.setNoOfNpcs(2);
        newRoom.setNoOfChests(3);
        newRoom.setIs_boss(false);
        entityManager.persist(newRoom);

        entityManager.getTransaction().commit();

        entityManager.close();
        JPAUtil.shutdown();
    }
}