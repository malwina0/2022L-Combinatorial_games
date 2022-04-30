import java.util.*;

public class Main {

    public static void main(String[] args) {

        Game game = Game.DataInput();

        while (true){
            System.out.println("Zbiór do gry:");
            System.out.println(game.Set);
            int len1 = game.ComputerMove();
            if (len1 >= game.k) {
                System.out.println("KOMPUTER WYGRAŁ!");
                break;
            }
            if(game.checkRemis()) {
                System.out.println("REMIS!");
                System.out.println("Żaden z zawodników nie ułoży już ciągu");
            }
            System.out.println("Zbiór do gry:");
            System.out.println(game.Set);
            int len2 = game.PlayerMove();
            if (len2 >= game.k) {
                System.out.println("GRACZ WYGRAŁ!");
                break;
            }
            if(game.checkRemis()) {
                System.out.println("REMIS!");
                System.out.println("Żaden z zawodników nie ułoży już ciągu");
            }
        }
    }
}
