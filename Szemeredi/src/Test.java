import java.util.*;

public class Test {


    public static void main(String[] args) {
        int n=40;
        int k=4;

        List<Integer> numbers = SetSampling.ChooseRandomSet(n);
        System.out.println(numbers);

//        lista gdzie na n-tym miejscu jest liczba ile jest par, między którymi różnica to n
        List<Integer> howManyAbs = new ArrayList<>(Arrays.asList(new Integer[2*n]));
        Collections.fill(howManyAbs, 0);

        Map<Integer, List<ArrayList<Integer>> > map = new HashMap<>();

        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                int difference = Math.abs(numbers.get(i) - numbers.get(j));
                howManyAbs.set(difference, howManyAbs.get(difference)+1);
                List<ArrayList<Integer>> listWithThisDifference;
                ArrayList<Integer> par = new ArrayList<>();
                par.add(numbers.get(i));
                par.add(numbers.get(j)); //mamy listę z parą którą rozważamy
                if (map.containsKey(difference)) {
                    listWithThisDifference = map.get(difference); // dostajemy się do danej listy z parami
                } else {
                    listWithThisDifference = new ArrayList<ArrayList<Integer>>();
                }
                listWithThisDifference.add(par); //do listy z parami o zadanej różnicy dodajemy kolejną parę o tej różnicy
                map.put(difference, listWithThisDifference);
            }
        }

        //System.out.println(howManyAbs);
        System.out.println("Wszystkie pary między którymi jest taka różnica:");
        System.out.println(map);

        Set<Integer> set = new HashSet<> ();
        for (int i = 0; i < map.size(); i++) {
            if (howManyAbs.get(i) < k-1){ //jesli jest mniej niż k liczb z dana różnicą, czyli mniej niż k-1 par to nie ma sensu ich rozważać
                set.add(i);
            }
        }
        map.keySet().removeAll(set);
        System.out.println("po usunieciu takich kluczy, dla których nie było wystraczająco par żeby utworzyć ciąg wymaganej długości:");
        System.out.println(map);


        List<ArrayList<Integer>> sequences = new ArrayList<ArrayList<Integer>>();

        // dla konkretnej różnicy n, sortujemy listę par po pierwszym elemencie
        List<ArrayList<Integer>> currentList = null;
        for (Integer key : map.keySet())  {
            currentList = map.get(key);
            currentList.sort((l1, l2) -> l1.get(0).compareTo(l2.get(0)));
            for (int j = 0; j < currentList.size()-1; j++) { //pętla do łączenia
            //int j = 0;
            //while (j < currentList.size()){
                //for (int l = j+1; l < currentList.size(); l++) {
                //System.out.println("key=" + key);
                //System.out.println("j=" + j);
                //System.out.println("rozmiar listy: " + currentList.size());
                int noOfElem = 1;
                while (currentList.get(j).get(noOfElem).equals(currentList.get(j+1).get(0))) {
                    // sprawdza czy jak jest np lista [[1, 2], [2, 3], [4, 5], [7,8]] to czy 2==2, 3==4, 5==7
                    // jakby tak było i by były takie k po kolei to mamy ciąg arytmatyczny
                    currentList.get(j).add(currentList.get(j+1).get(1));
                    noOfElem += 1;
                    currentList.remove(j+1);
                    //System.out.println(map);
                    //System.out.println("noOfElem=" + noOfElem);
                    //System.out.println("aktualny romziar listy: " + currentList.size());
                    if (j >= currentList.size()-1){
                        //System.out.println("bedzie break");
                        break;
                    }
                }
            }
// TO TWORZY MAPĘ: różnica - ciągi o tej różnicy
//            for (int j = 0; j < currentList.size(); j++) {
//                while (currentList.get(j).size() < k) {
//                    currentList.remove(j);
//                    if (j + 1 > currentList.size()) {
//                        //System.out.println("bedzie break");
//                        break;
//                    }
//                }
//            }
// TO TWORZY LISTĘ CIĄGÓW:
            for (ArrayList<Integer> integers : currentList) {
                if (integers.size() >= k) {
                    sequences.add(integers);
                }
            }
        }
        System.out.println("Połączne pary w ciągi, tam, gdzie można:");
        System.out.println(map);
        System.out.println("Zostawione tylko potrzebnej długości:");
        System.out.println(sequences);
    }
}


