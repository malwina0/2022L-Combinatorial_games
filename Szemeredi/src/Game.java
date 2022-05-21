import java.util.*;

public class Game {

    public int n;
    public int k;
    public ArrayList<Integer> Set;
    public ArrayList<Integer> ComputerSet;
    public ArrayList<Integer> PlayerSet;
    public int Order;
    public static final Scanner Input = new Scanner( System.in );
    public Set<ArrayList<Integer>> sequences;
    public int blockOpponent(){
        //System.out.println("BLOCK");
        List<Integer> copy = new ArrayList<>();
        List<Integer> seriesPlayer = setsUpdate(copy, PlayerSet);
        seriesPlayer.removeAll(PlayerSet);
        return seriesPlayer.get(0);
    }

    private List<Integer> setsUpdate(List<Integer> copy, ArrayList<Integer> playerSet) {
        copy.addAll(playerSet);
        copy.addAll(Set);
        Map<Integer, List<Integer>> best = ProgressionChecker.CheckProgressions(copy);
        int key = best.keySet().iterator().next();
        return best.get(key);
    }

    public Game(int order, int n, int k) {
        this.k = k;
        this.n = n;
        Order = order;
        ComputerSet = new ArrayList<>();
        PlayerSet = new ArrayList<>();
        Set = (ArrayList<Integer>) SetSampling.GenerateSetWithProgression(n, k);
        sequences = SequenceOps.getSequences(Set,k);
    }

    public static Game DataInput(){
        System.out.println("Gra Szemeredi'ego");
        System.out.println("Ze względu na trudność w szukaniu długich ciągów pośród wielu liczb oraz przedłużone działanie programu," +
                "najlepiej wybrać n z przedziału od 3 do 1000 oraz k od 1 do 10.");
        System.out.println("Podaj wielkość wylosowanego zbioru:");
        int n;
        while(true) {
            String nS = Input.nextLine();
            try {
                n = Integer.parseInt(nS);
                if (n > 1000 || n < 3)
                    throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Podano nieprawidłowy format danych, proszę podać liczbę od 3 do 1000.");
            }
        }
        System.out.println("Podaj długość poszukiwanego ciągu arytmetycznego od " + Math.min(2,n/2) + " do " + n/2 +":");
        int k;
        while(true) {
            String kS = Input.nextLine();
            try {
                k = Integer.parseInt(kS);
                if (k > n/2 || k < 2)
                    throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Podano nieprawidłowy format danych, proszę podać liczbę od " + Math.min(2,n/2) + " do " + n/2 +":");
            }
        }
        int order = new Random().nextInt(2);
        return new Game(order, n, k);
    }
    public int computerFirstMove(){
        System.out.println("Ruch komputera.");
        int element = SequenceOps.selectFirstNumber(sequences);
        Set.removeAll(List.of(element));
        SequenceOps.updateSequences(sequences, element);
        return finalizeMove(element);
    }

    private int finalizeMove(int element) {
        ComputerSet.add(element);
        System.out.println("Komputer wybrał element " + element);
        System.out.println("Zbiór komputera:");
        System.out.println(ComputerSet);
        //System.out.println(length);
        return ProgressionChecker.CheckProgressions(ComputerSet).keySet().iterator().next();
    }

    public int ComputerMove(){
        System.out.println("Ruch komputera.");
        List<Integer> copy = new ArrayList<>();
        boolean result = false;
        int element = -1;

        if (ProgressionChecker.CheckProgressions(PlayerSet).keySet().iterator().next() == k - 1) {
            List<Integer> copy2 = new ArrayList<>();
            List<Integer> series = setsUpdate(copy2,PlayerSet);
            series.removeAll(PlayerSet);
            if(series.size() == 1) {
                element = blockOpponent();
                Set.removeAll(List.of(element));
                result = true;
            }
        } if(!result) {
            List<Integer> series = setsUpdate(copy, ComputerSet);
            //System.out.println(series.toString());
            if (series.isEmpty())
                return 0;
            int index;
            while (true) {
                index = new Random().nextInt(series.size());
                if (Set.contains(series.get(index))) {
                    element = series.get(index);
                    Set.removeAll(List.of(element));
                    break;
                }
            }
        }
        return finalizeMove(element);

    }

    public int PlayerMove(){
        System.out.println("Ruch gracza.");
        System.out.println("Twój zbiór:");
        System.out.println(PlayerSet);
        System.out.println("Wybierz liczbę ze zbioru, którą chcesz pokolorować swoim kolorem");
        int element;
        while(true) {
            String kS = Input.nextLine();
            try {
                element = Integer.parseInt(kS);
                if (Set.contains(element)){
                    PlayerSet.add(element);
                    Set.removeAll(List.of(element));
                    break;
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Podano nieprawidłowy format danych lub liczbę, której nie ma w zbiorze, proszę podać liczbę znajdującą się w zbiorze:");
            }
        }
        System.out.println("Zbiór gracza:");
        System.out.println(PlayerSet);
        return ProgressionChecker.CheckProgressions(PlayerSet).keySet().iterator().next();
    }

    public boolean checkRemis(){
        List<Integer> SetC = new ArrayList<>();
        SetC.addAll(ComputerSet);
        SetC.addAll(Set);
        List<Integer> SetP = new ArrayList<>();
        SetP.addAll(PlayerSet);
        SetP.addAll(Set);
        //System.out.println(SetC);
        int lenC = ProgressionChecker.CheckProgressions(SetC).keySet().iterator().next();
        int lenP = ProgressionChecker.CheckProgressions(SetP).keySet().iterator().next();
        return lenC < k && lenP < k;
    }


}
