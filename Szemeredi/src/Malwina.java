import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Malwina{

    public static List<ArrayList<Integer>> getSequences(List<Integer> numbers, int k) {
        /**
         * przyjmuje listę liczb oraz długość szukanego ciągu arytmetycznego
         * zwraca listę wszytskich ciągów arytmatycznych o długości >= k
         */
        int n = numbers.size();
        List<ArrayList<Integer>> sequences = new ArrayList<ArrayList<Integer>>(); //wynikowa lista

//        lista gdzie na n-tym miejscu jest liczba ile jest par, między którymi różnica to n
        List<Integer> howManyAbs = new ArrayList<>(Arrays.asList(new Integer[5*n]));
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
        for (int i = 0; i < map.size(); i++) {
            if (howManyAbs.get(i) < k-1){
                set.add(i);
            }
        }
        map.keySet().removeAll(set);

        // dla konkretnej różnicy n, sortujemy listę par po pierwszym elemencie
        List<ArrayList<Integer>> currentList = null;
        for (Integer key : map.keySet())  {
            currentList = map.get(key);
            currentList.sort((l1, l2) -> l1.get(0).compareTo(l2.get(0)));
            for (int j = 0; j < currentList.size()-1; j++) { //pętla do łączenia
                int noOfElem = 1;
                while (currentList.get(j).get(noOfElem).equals(currentList.get(j+1).get(0))) {
                    // sprawdza czy jak jest np lista [[1, 2], [2, 3], [4, 5], [7,8]] to czy 2==2, 3==4, 5==7
                    currentList.get(j).add(currentList.get(j+1).get(1));
                    noOfElem += 1;
                    currentList.remove(j+1);
                    if (j >= currentList.size()-1){
                        break;
                    }
                }
            }
// TO TWORZY MAPĘ: różnica : ciągi o tej różnicy
//            for (int j = 0; j < currentList.size(); j++) {
//                while (currentList.get(j).size() < k) {
//                    currentList.remove(j);
//                    if (j + 1 > currentList.size()) {
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
        return sequences;
    }


    public static Integer selectFirstNumber(List<ArrayList<Integer>> sequences) {
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
}



