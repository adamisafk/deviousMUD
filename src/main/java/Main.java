import org.example.Game;
import org.example.entity.Item;
import org.example.entity.JPAUtil;

import java.util.*;

public class Main
{
    static Scanner stdin = new Scanner(System.in);
    public static void main(String[] args)
    {
        Game game = new Game();
        game.menu();

    }

    public static void ormTest() {
        Item item = JPAUtil.getItemByNPCId(1);
        System.out.println(item.getName());
    }
}