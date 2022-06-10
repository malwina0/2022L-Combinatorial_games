import java.awt.event.KeyEvent;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.Console;
public class Main {
    public static void main(String[] args) throws Exception {

        Game game = Game.DataInput();
        int gameIters = 0;
        int whoStarts;
        Scanner myInput = new Scanner( System.in );
        while (true) {
            System.out.println("1 - zaczyna gracz, 0 - zaczyna komputer");
            whoStarts = myInput.nextInt();
            if (whoStarts == 0 || whoStarts == 1)
                break;
            System.out.println("Podano nieprawidłową wartość, podaj 1 lub 0");
        }
        while (true){
            System.out.println("---------------------" + "Tura nr " + gameIters + "-----------------------");
            System.out.println("Zbiór do gry:");
            System.out.println(game.Set);
            int len1;
            if (gameIters == 0){
                if (whoStarts == 0)
                    len1 = game.computerFirstMove();
                else
                    len1 = game.PlayerMove();
            } else {
                if (whoStarts == 0)
                    len1 = game.ComputerMove();
                else
                    len1 = game.PlayerMove();
            }
            if (len1 >= game.k) {
                Map<Integer, List<Integer>> winning;
                if (whoStarts == 0) {
                    System.out.println("KOMPUTER WYGRAŁ!");
                    winning = ProgressionChecker.CheckProgressions(game.ComputerSet);
                }
                else {
                    System.out.println("GRACZ WYGRAŁ!");
                    winning = ProgressionChecker.CheckProgressions(game.PlayerSet);
                }
                int key = winning.keySet().iterator().next();
                System.out.println("Wygrywający ciąg: " + winning.get(key));
                System.out.println("Wciśnij dowolny klawisz żeby zakończyć program");
                System.in.read();
                //TimeUnit.SECONDS.sleep(5);
                break;
            }
            if(game.checkRemis()) {
                System.out.println("REMIS!");
                System.out.println("Żaden z zawodników nie ułoży już ciągu");
                System.out.println("Wciśnij dowolny klawisz żeby zakończyć program");
                System.in.read();
                //TimeUnit.SECONDS.sleep(5);
                return;
            }
            System.out.println("Zbiór do gry:");
            System.out.println(game.Set);
            int len2;
            if (whoStarts == 0)
                len2 = game.PlayerMove();
            else
                len2 = game.ComputerMove();
            if (len2 >= game.k) {
                Map<Integer, List<Integer>> winning;
                if (whoStarts == 0) {
                    System.out.println("GRACZ WYGRAŁ!");
                    winning = ProgressionChecker.CheckProgressions(game.PlayerSet);
                }
                else {
                    System.out.println("KOMPUTER WYGRAŁ!");
                    winning = ProgressionChecker.CheckProgressions(game.ComputerSet);
                }
                int key = winning.keySet().iterator().next();
                System.out.println("Wygrywający ciąg: " + winning.get(key));
                System.out.println("Wciśnij dowolny klawisz żeby zakończyć program");
                System.in.read();
                //TimeUnit.SECONDS.sleep(5);
                return;
            }
            if(game.checkRemis()) {
                System.out.println("REMIS!");
                System.out.println("Żaden z zawodników nie ułoży już ciągu");
                System.out.println("Wciśnij dowolny klawisz żeby zakończyć program");
                System.in.read();
                //TimeUnit.SECONDS.sleep(5);
                return;
            }
            gameIters++;
        }
    }
}
