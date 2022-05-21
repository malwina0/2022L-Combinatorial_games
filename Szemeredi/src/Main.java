import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Game game = Game.DataInput();
        int gameIters = 0;
        while (true){
            System.out.println("Zbiór do gry:");
            System.out.println(game.Set);
            int len1;
            if (gameIters == 0) len1 = game.computerFirstMove(); else len1 = game.ComputerMove();
            if (len1 >= game.k) {
                System.out.println("KOMPUTER WYGRAŁ!");
                break;
            }
            if(game.checkRemis()) {
                System.out.println("REMIS!");
                System.out.println("Żaden z zawodników nie ułoży już ciągu");
                return;
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
                return;
            }
            gameIters++;
        }
    }
}
