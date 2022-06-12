import java.util.*;

public class Game {

    public int x;
    public int k;
    public ArrayList<Integer> Set;
    public ArrayList<Integer> ComputerSet;
    public ArrayList<Integer> PlayerSet;
    public int Order;
    public static final Scanner Input = new Scanner( System.in );
    public Set<ArrayList<Integer>> sequences;
    public Set<MySet> PlayerSequences;
    public Set<MySet> ComputerSequences;

    /**
     WARTO ZACZĄĆ OD TEGO, ŻE TERAZ OBIEKT GRA MA POLA COMPUTERSEQUNENCES I PLAYERSEQUENCED.
     SĄ TO POLA, W KTÓRYCH PRZECHOWYWANE SĄ OBIEKTY MYSET, KTORE ODPOWIADAJĄ CIĄGOM KTÓRE GRACZ MOŻE JESZCZE UTWORZYĆ.
     NA STARCIE SĄ INIZJALIZOWANE W KONSTRUKTORZE TAK ŻE KAŻDY GRACZ MÓGŁBY UŁOŻYĆ KAŻDY CIĄG, A POTEM
     SUKCEWSYWNIE USUWANE.

     W MAINIE NIC SIĘ NIE ZMIENIŁO, LOGIKA JEST DOKŁADNIE TAKA SAMA.
     */

    public Game(int order, int x, int k) {
        this.k = k;
        this.x = x;
        Order = order;
        ComputerSet = new ArrayList<>();
        PlayerSet = new ArrayList<>();
        Set = (ArrayList<Integer>) SetSampling.GenerateSetWithProgression(x, k);
        sequences = SequenceOps.getSequences(Set,k);
        ComputerSequences = new HashSet<>();
        PlayerSequences = new HashSet<>();
        for (ArrayList<Integer> seq : sequences) {
            MySet n = new MySet(seq);
            ComputerSequences.add(n);
            PlayerSequences.add(n);
        }
    }

    public static Game DataInput(){
        System.out.println("Gra Szemeredi'ego");
        System.out.println("Ze względu na trudność w szukaniu długich ciągów pośród wielu liczb oraz przedłużone działanie programu," +
                "najlepiej wybrać x z przedziału od 3 do 1000 oraz k od 1 do 10.");
        System.out.println("Podaj wielkość wylosowanego zbioru:");
        int x;
        while(true) {
            String nS = Input.nextLine();
            try {
                x = Integer.parseInt(nS);
                if (x > 1000 || x < 3)
                    throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Podano nieprawidłowy format danych, proszę podać liczbę od 3 do 1000.");
            }
        }
        System.out.println("Podaj długość poszukiwanego ciągu arytmetycznego od " + Math.min(2,x/2) + " do " + x/2 +":");
        int k;
        while(true) {
            String kS = Input.nextLine();
            try {
                k = Integer.parseInt(kS);
                if (k > x/2 || k < 2)
                    throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Podano nieprawidłowy format danych, proszę podać liczbę od " + Math.min(2,x/2) + " do " + x/2 +":");
            }
        }
        int order = new Random().nextInt(2);
        return new Game(order, x, k);
    }

    public void updateProgressions(int player, int number){
        /**
         Funckja która odpowiada za aktualizowanie setów graczy.
         * player: 0 - ruch komputera, 1 - ruch gracza.
         Gdy gracz wybiera liczbę, we wszystkich MySetach należących do niego, tam gdzie jest element number,
         korzysta z funcji MySet.appendColored. A dodatkowo wyrzuca ze zbioru MySetów przeciwnika wszystkie
         MySety które odpowiadają ciągom zawierającym number - bo gracz przeciwny na pewno już ich nie ułoży.
         Dodatkowo tu updateuje się Set, ComputerSet i PlayerSet.
         */
        if (player == 0) {
            ComputerSet.add(number);
            PlayerSequences.removeIf(e -> e.sequence.contains(number));
            for (MySet e : ComputerSequences){
                if (e.sequence.contains(number))
                    e.appendColored(number);
            }
        } else {
            PlayerSet.add(number);
            ComputerSequences.removeIf(e -> e.sequence.contains(number));
            for (MySet e : PlayerSequences){
                if (e.sequence.contains(number))
                    e.appendColored(number);
            }
        }
        Set.removeAll(List.of(number));
    }

    public MySet getColoredMaxSeq(Set<MySet> seqs){
        /**
         * Funckja przyjmuje Set MySetów - komputera lub gracza, i zwraca obiekt MySet, który
         * odpowiada ciągowi, w któym gracz ma najwięcej pokolorowanych liczb.
         * Gdy jest więcej takich MySetów - wybiera pierwszy który trafi się podczas iterowania.
         */
        int maxcolored = 0;
        MySet max = null;
        for(MySet e: seqs){
            if (e.Colored >= maxcolored){
                maxcolored = e.Colored;
                max = e;
            }
        }
        return max;
    }

    public int block(){
        /**
         Funckja odpowiadająca za blokowania gracza.
         Blokowanie jest wykonywane wtedy, gdy:
         - człowiek ma dłuższy ciąg (pozyskany funckją getColoredMaxSeq)
         - komputer nie ma już ciągów do ułożenia.
         W pozostałych przypadkach komputer nie blokuje przeciwnika.
         Funckja przeszukuje MySety człowieka, znajduje ten który jest najwięcej pokolorwany
         i z niego wybiera losową liczbę, która jeszcze nie jest pokolorowana
         Funckja zwraca:
         - -1 gdy komputer nie blokuje
         - liczbę którą koloruje komputer, gdy blokuje.
         */
        MySet maxComp = getColoredMaxSeq(ComputerSequences);
        MySet maxPlayer = getColoredMaxSeq(PlayerSequences);
        if (maxPlayer == null){
            return -1;
        }
        if (maxComp == null || maxComp.Colored < maxPlayer.Colored){
            ArrayList<Integer> numbersToColor = new ArrayList<>(maxPlayer.sequence);
            numbersToColor.removeAll(maxPlayer.coloredNumbers);
            int index = new Random().nextInt(numbersToColor.size());
            int number = numbersToColor.get(index);
            updateProgressions(0, number);
            return number;
        }
        return -1;
    }

    public boolean checkRemis(){
        /**
         * funckja sprawdza czy jest remis
         * Jest ro w tym przpadku równoważne z tym, że ani komputer ani człowiek
         * nie mają już ciągów do ułożenia
         * Zwraca true gdy remis i false gdy nieremis.
         */
        if (PlayerSequences.isEmpty() && ComputerSequences.isEmpty()){
            return true;
        }
        return false;
    }

    public int computerFirstMove(){
        /**
         * pierwszy ruch komputera, analogicznie do tego wcześniej
         * komputer wybiera najczęściej występującą liczbę.
         * W pierwszym ruchu komputer jeszcze nie blokuje!!!
         * Funckja zwraca długość najwięcej pokolorowaneoo ciagu
         * lub -1, gdy komputer już nie ma ciągów do ułożenia.
         */
        System.out.println("Ruch komputera.");
        int element = SequenceOps.selectFirstNumber(sequences);
        updateProgressions(0, element);
        System.out.println("Komputer wybrał element " + element);
        System.out.println("Zbiór komputera:");
        System.out.println(ComputerSet);
        MySet Best = getColoredMaxSeq(ComputerSequences);
        if (Best == null){
            return -1;
        }
        return Best.Colored;
    }

    public int ComputerMove(){
        /**
         * Funckja odpowiedzialna za kolejne ruchy komputera.
         * Najpier wykonywana jest funckja block().
         * Jeśli block zwróciło -1, to wykonujemy normalne kolorwanie.
         * Polega ono na ponownym znaleznienie najbardziej pokolorwanego ciągu
         * i wybraniu losowej liczby która nie jest jeszcze pokolorowana.
         * Jeśli block zwróci liczbę inną niż -1 to oznacza, że to updateProgressions()
         * jest przekazywana właśnie ta liczba.
         * Funckja zwraca długość najwięcej pokolorowaneoo ciagu
         * lub -1, gdy komputer już nie ma ciągów do ułożenia.
         */
        int number = block();
        if(number == -1){
            MySet maxComp = getColoredMaxSeq(ComputerSequences);
            ArrayList<Integer> numbersToColor = new ArrayList<>(maxComp.sequence);
            numbersToColor.removeAll(maxComp.coloredNumbers);
            int index = new Random().nextInt(numbersToColor.size());
            number = numbersToColor.get(index);
            updateProgressions(0, number);
        }
        System.out.println("Komputer wybrał element " + number);
        System.out.println("Zbiór komputera:");
        System.out.println(ComputerSet);
        MySet best = getColoredMaxSeq(ComputerSequences);
        if (best == null){
            return -1;
        }
        return best.Colored;
    }

    public int PlayerMove(){
        /**
         * Funckja odpowedzialna za ruch gracza,
         * jest identyczna jak do tej pory, poza tym że używa updateProgressions() ;)
         * Funckja zwraca długość najwięcej pokolorowaneoo ciagu
         * lub -1, gdy człowiek już nie ma ciągów do ułożenia.
         */
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
                    updateProgressions(1, element);
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
        MySet best = getColoredMaxSeq(PlayerSequences);
        if (best == null){
            return -1;
        }
        return best.Colored;
    }



}
