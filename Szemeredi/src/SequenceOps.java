import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SequenceOps {

    public static Set<ArrayList<Integer>> getSequences(List<Integer> numbers, int k) {
        /**
         * przyjmuje listę liczb oraz długość szukanego ciągu arytmetycznego
         * zwraca listę wszytskich ciągów arytmatycznych o długości = k
         */
        int x = numbers.size();
        Set<ArrayList<Integer>> sequences = new HashSet<>(); //wynikowy set

//        lista gdzie na x-tym miejscu jest liczba ile jest par, między którymi różnica to x
        List<Integer> howManyAbs = new ArrayList<>(Arrays.asList(new Integer[5*x]));
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
                    listWithThisDifference = new ArrayList<ArrayList<Integer>>(); // jeśli takiej nie ma to towrzymy
                }
                listWithThisDifference.add(par); //do listy z parami o zadanej różnicy dodajemy kolejną parę o tej różnicy
                map.put(difference, listWithThisDifference);
            }
        }
        //jesli jest mniej niż k liczb z dana różnicą, czyli mniej niż k-1 par to nie ma sensu ich rozważać:
        Set<Integer> set = new HashSet<> ();
        for (int i = 0; i < howManyAbs.size(); i++) {
            if (howManyAbs.get(i) < k-1){
                set.add(i);
            }
        }
        map.keySet().removeAll(set);

        // dla konkretnej różnicy x, sortujemy listę par po pierwszym elemencie
        List<ArrayList<Integer>> currentList = null;
        for (Integer key : map.keySet())  {
            currentList = map.get(key);
            currentList.sort((l1, l2) -> l1.get(0).compareTo(l2.get(0)));
            for (int j = 0; j < currentList.size()-1; j++) { //pętla do łączenia
                int noOfElem = 1;
                int iledalej = 1;
                // sprawdza czy jak jest np lista [[1, 2], [2, 3], [4, 5], [7,8]] to czy 2==2, 3==4, 5==7:
                while (currentList.get(j).get(noOfElem) >= currentList.get(j+iledalej).get(0)){
                    if (currentList.get(j).get(noOfElem).equals(currentList.get(j+iledalej).get(0)) && currentList.get(j).size()+1 <= k){
                        // && currentList.get(j).size()+1 <= k
                        currentList.get(j).add(currentList.get(j+iledalej).get(1));
                        noOfElem += 1;
                        iledalej = 1;
                    } else {
                        iledalej +=1;
                    }
                    if (j+iledalej >= currentList.size() || noOfElem >= k-1){
                        //przerywa jak już stworzono ciąg długości k, żeby nie szukało dłuższego, albo jak elementy się kończą
                        break;
                    }
                }
            }
            for (ArrayList<Integer> integers : currentList) {
                if (integers.size() >= k) { //do wynikowego setu dodaje tylko te listy, które są długości k (wcześniej apewniliśmy długość <=k)
                    sequences.add(integers);
                }
            }
        }
        return sequences;
    }


    public static Integer selectFirstNumber(Set<ArrayList<Integer>> sequences) {
        /**
         * przyjmuje listę ciągów arytmetycznych
         * zwraca liczbę którą pokoloruje komputer, w przypadku gdy jest to jego pierwszy ruch
         * ta liczba to ta, która w wyznaczonych ciągach arytmetycznych występuje najczęściej,
         * lub jeżeli każda występuje tylko raz to jest to środkowa liczba z najdłuższego ciągu arytmetycznego
         */
        List<Integer> flatSeq = sequences.stream() //spłaszczenie listy list do listy
                .flatMap(List::stream)
                .collect(Collectors.toList());

        // najczęściej występująca liczba oraz ile razy wystąpiła
        Map.Entry<Integer, Long> mostFrequentNo = flatSeq.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max((o1, o2) -> o1.getValue().compareTo(o2.getValue()))
                .get();

        if (mostFrequentNo.getValue() == 1) { //jeśli żadna liczba nie występuje więcej niż 1 raz
            List<Integer> list = sequences.stream().max(Comparator.comparing(List::size)).get();
            return list.get((list.size()/2) + (list.size() % 2)-1); //środkowy element w najdłuższym ciągu albo ten pierwszy środkowy
        } else {
            return mostFrequentNo.getKey(); //jak jakaś liczba występuje więcej niż raz to ją wybieramy i tyle
        }
    }
    public static void updateSequences(Set<ArrayList<Integer>> sequences, int element){
        //System.out.println("---------------UPDATE SEQUENCES-----------------");
        //System.out.println(sequences);
        Iterator<ArrayList<Integer>> sequenceIterator = sequences.iterator();
        List<Integer> currentSequence;
        while (sequenceIterator.hasNext()){
            currentSequence = sequenceIterator.next();
            currentSequence.removeAll(List.of(element));
        }
        //System.out.println(sequences);
    }


}



