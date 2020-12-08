import org.example.Game;
import org.example.entity.JPAUtil;
import org.example.entity.Room;

import java.util.*;

public class Main
{
    static Scanner stdin = new Scanner(System.in);
    public static void main(String[] args)
    {
        Game game = new Game();
        game.menu();
        System.out.print("Test");


        ormTest();
    }

    public static void ormTest() {
//        Room newRoom = JPAUtil.getRoom(1);
//        System.out.println(newRoom.getName());
        //JPAUtil.setScore("Someone", 69);

        ArrayList<Room> rooms = JPAUtil.getListOfRooms();


        for (Room room : rooms) {
            System.out.println(room.getName());
        }

    }
}