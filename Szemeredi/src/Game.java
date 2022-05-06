import javax.swing.*;
import java.util.*;

public class Game {

    public int n;
    public int k;
    public ArrayList<Integer> Set;
    public ArrayList<Integer> ComputerSet;
    public ArrayList<Integer> PlayerSet;
    public int Order;
    public static final Scanner Input = new Scanner( System.in );


    public Game(int order, int n, int k) {
        this.k = k;
        this.n = n;
        Order = order;
        ComputerSet = new ArrayList<>();
        PlayerSet = new ArrayList<>();
        Set = (ArrayList<Integer>) SetSampling.GenerateSetWithProgression(n, k);
    }

    public static Game DataInput(){
        System.out.println("Gra Szemeredi'ego");
        System.out.println("Ze względu na trudność w szukaniu długich ciągów pośród wielu liczb oraz przedłużone działanie programu," +
                "najlepiej wybrać n z przedziału od 3 do 1000 oraz k od 1 do 10.");
        System.out.println("Podaj wielkość wylosowanego zbioru:");
        int n = 0;
        while(true) {
            String nS = Input.nextLine();
            try {
                n = Integer.parseInt(nS);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Podano nieprawidłowy format danych, proszę podać liczbę od 3 do 1000.");
            }
        }
        while(true){
            if (n >= 1){
                break;
            } else {
                System.out.println("Liczba musi być większa od 0. Podaj liczbę liczbę od 1 do 1000");
            }
            if (n <= 2000) {
                break;
            } else {
                System.out.println("Podana liczba jest zbyt duża. Podaj jeszcze raz:");
            }
        }

        System.out.println("Podaj długość poszukiwanego ciągu arytmetycznego (najlepiej od 2 do 10):");
        int k;
        while(true) {
            String kS = Input.nextLine();
            try {
                k = Integer.parseInt(kS);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Podano nieprawidłowy format danych, proszę podać liczbę od 1 do " + n/2 +":");
            }
        }
        while(true){
            if (k <= n/2) {
                break;
            } else {
                System.out.println("Ciąg powinien być długości mniejszej niż połowa wielkości zbioru! Podaj jeszcze raz:");
            }
            if (k >= 1){
                break;
            } else {
                System.out.println("Liczba musi być większa od 0. Podaj liczbę liczbę od 1 do \" + n/2 +\":\"");
            }
        }
        int order = new Random().nextInt(2);
        Game game = new Game(order, n, k);
        return game;
    }


    public int ComputerMove(){
        System.out.println("Ruch komputera.");
        List<Integer> copy = new ArrayList<>();
        copy.addAll(ComputerSet);
        copy.addAll(Set);
        Map<Integer, List<Integer>> best = ProgressionChecker.CheckProgressions(copy);
        int key = best.keySet().iterator().next();
        List<Integer> series = best.get(key);
        int index;
        int element;
        while(true) {
            index = new Random().nextInt(series.size());
            if (Set.contains(series.get(index))) {
                element = series.get(index);
                Set.removeAll(Arrays.asList(element));
                break;
            }
        }
        ComputerSet.add(element);
        System.out.println("Komputer wybrał element " + element);
        System.out.println("Zbiór komputera:");
        System.out.println(ComputerSet);

        int length = ProgressionChecker.CheckProgressions(ComputerSet).keySet().iterator().next();
        return length;

    }

    public int PlayerMove(){
        System.out.println("Ruch gracza.");
        System.out.println("Twój zbiór:");
        System.out.println(PlayerSet);
        System.out.println("Wybierz liczbę ze zbioru, którą chcesz pokolorwać swoim kolorem");
        int element;
        while(true) {
            String kS = Input.nextLine();
            try {
                element = Integer.parseInt(kS);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Podano nieprawidłowy format danych, proszę podać liczbę znajdującą się w zbiorze:");
            }
        }
        while(true){
            if (Set.contains(element)){
                PlayerSet.add(element);
                Set.removeAll(Arrays.asList(element));
                break;
            } else {
                System.out.println("Brak elementu w zbiorze. Wybierz jeszcze raz.");
            }
        }
        System.out.println("Zbiór gracza:");
        System.out.println(PlayerSet);
        int length = ProgressionChecker.CheckProgressions(PlayerSet).keySet().iterator().next();
        return length;
    }

    public boolean checkRemis(){
        List<Integer> SetC = new ArrayList<>();
        SetC.addAll(ComputerSet);
        SetC.addAll(Set);
        List<Integer> SetP = new ArrayList<>();
        SetC.addAll(PlayerSet);
        SetC.addAll(Set);
        int lenC = ProgressionChecker.CheckProgressions(SetC).keySet().iterator().next();
        int lenP = ProgressionChecker.CheckProgressions(SetP).keySet().iterator().next();
        boolean remis = false;
        if (lenC < k && lenP < k)
            remis = true;
        return remis;
    }


}
