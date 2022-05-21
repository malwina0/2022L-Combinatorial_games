import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static void main(String[] args) throws Exception {

        Game game = Game.DataInput();
        int gameIters = 1;
        while (true){
            System.out.println(ANSI_BLUE_BACKGROUND + "-----------------------" + "Tura nr " + gameIters + "-----------------------" + ANSI_RESET);
            System.out.println("Zbiór do gry:");
            System.out.println(game.Set);
            int len1;
            if (gameIters == 1) len1 = game.computerFirstMove(); else len1 = game.ComputerMove();
            if (len1 >= game.k) {
                System.out.println("KOMPUTER WYGRAŁ!");
                Map<Integer, List<Integer>> winning = ProgressionChecker.CheckProgressions(game.ComputerSet);
                int key = winning.keySet().iterator().next();
                System.out.println("Wygrywający ciąg: " + ANSI_RED + winning.get(key) + ANSI_RESET);
                TimeUnit.SECONDS.sleep(5);
                break;
            }
            if(game.checkRemis()) {
                System.out.println("REMIS!");
                System.out.println("Żaden z zawodników nie ułoży już ciągu");
                TimeUnit.SECONDS.sleep(5);
                return;
            }
            System.out.println("Zbiór do gry:");
            System.out.println(game.Set);
            int len2 = game.PlayerMove();
            if (len2 >= game.k) {
                System.out.println("GRACZ WYGRAŁ!");
                Map<Integer, List<Integer>> winning = ProgressionChecker.CheckProgressions(game.PlayerSet);
                int key = winning.keySet().iterator().next();
                System.out.println("Wygrywający ciąg: " + ANSI_GREEN + winning.get(key) + ANSI_RESET);
                TimeUnit.SECONDS.sleep(5);
                break;
            }
            if(game.checkRemis()) {
                System.out.println("REMIS!");
                System.out.println("Żaden z zawodników nie ułoży już ciągu");
                TimeUnit.SECONDS.sleep(5);
                return;
            }
            gameIters++;
        }
    }
}
