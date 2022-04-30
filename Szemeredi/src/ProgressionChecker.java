import java.security.KeyStore;
import java.util.*;

public class ProgressionChecker {


    public static Map<Integer, List<Integer>> CheckProgressions(List<Integer> numbers) {
        // Funckja, która szuka najdłuższego ciągu i zwraca słownik postaci {klucz: długość, wartość: najdłuższy ciąg}
        int maxlength = 2;
        List<Integer> series = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++ ){
            for (int j = i+1; j < numbers.size(); j++){
                List<Integer> singleProgression = new ArrayList<>();
                int first = numbers.get(i);
                int next = numbers.get(j);
                singleProgression.add(first);
                singleProgression.add(next);
                int roznica = numbers.get(j) - numbers.get(i);
                while(numbers.contains(next + roznica)) {
                    next = next + roznica;
                    singleProgression.add(next);
                }
                if (singleProgression.size() > maxlength) {
                    maxlength = singleProgression.size();
                    series = singleProgression;
                }
            }
        }
        Map<Integer, List<Integer>> result = new HashMap<>();
        result.put(maxlength, series);
        return result;

    }


//    public static Map<Integer, List<List<Integer>>> CheckLength(List<Integer> numbers, int k) {
//        List<List<Integer>> series = new ArrayList<>();
//        Map<Integer, List<List<Integer>>> roznicePary = new HashMap<Integer, List<List<Integer>>>();
//        for (int i = 0; i < numbers.size(); i++) {
//            for (int j = i + 1; j < numbers.size(); j++) {
//                int roznica = Math.abs(numbers.get(i) - numbers.get(j));
//                List<Integer> para = Arrays.asList(numbers.get(i), numbers.get(j));
//                if (roznicePary.containsKey(roznica)) {
//                    List<List<Integer>> pary = roznicePary.get(roznica);
//                    pary.add(Arrays.asList(numbers.get(i), numbers.get(j)));
//                    roznicePary.put(roznica, pary);
//                } else {
//                    List<List<Integer>> pary = new ArrayList<>();
//                    pary.add(para);
//                    roznicePary.put(roznica, pary);
//                }
//            }
//        }
//        int maxlength = 2;
//        for (int key : roznicePary.keySet()) {
//            List<List<Integer>> pary = roznicePary.get(key);
//
//
//        }
//
//    }
//
//    public void RecurssiveSearch(List<List<Integer>> pary, List<List<Integer>> series, List<Integer> progression, int next){
//        if (pary.isEmpty())
//            return
//        for (List<Integer> para : pary){
//            if (next == para.get(0)) {
//                int cos = para.get(0);
//                progression = progression.add();
//
//            }
//        }
//    }



    //        check if there is arithmetic sequence of length=k:

    //        lista gdzie na n-tym miejscu jest liczba ile jest par, między którymi różnica to n
//    List<Integer> howManyAbs = new ArrayList<>(Arrays.asList(new Integer[10*n]));
//        Collections.fill(howManyAbs, 0);
//
//                //        lista gdzie na n-tym miejscu jest lista par, między ktorymi jest różnica n
//                List<List<List<Integer>>> whichParsWithThisAbs = new ArrayList<>();
//
//
//        for (int i = 0; i < numbers.size(); i++){
//        for (int j = i+1; j < numbers.size(); j++){
//        int index = Math.abs(numbers.get(i) - numbers.get(j));
//        howManyAbs.set(index, howManyAbs.get(index)+1);
//
//        ArrayList<Integer> par = new ArrayList<>(Arrays.asList(new Integer[2]));
//        par.add(numbers.get(i), numbers.get(j)); //nie no nei wiem jak to dodawać i czy w ogóle coś takiego ma sens
//        // ogólnie chodzi o to że nie wiem jak to zaincjalizować żeby ta gówna lista miała 10*n pustych list
//        System.out.println(whichParsWithThisAbs.get(index)); // .add(par); //dodanie do listy znajdującej się na n-tym miejscu pary liczb, które różnią się o n
//        //whichParsWithThisAbs.add(new ArrayList<>());
//        }
//        }
//        System.out.println(howManyAbs);
//        System.out.println(whichParsWithThisAbs);
//        // dla konkretnej różnicy n, sortujemy listę par po pierwszym elemencie
//        List<List<Integer>> currentList = null;
//        for (int i = 0; i < whichParsWithThisAbs.size(); i++)
//        currentList = whichParsWithThisAbs.get(i);
//        currentList.sort((l1, l2) -> l1.get(0).compareTo(l2.get(0)));
//        // sprawdzamy czy są kolejnymi liczbami w ciągu
//        for (int j = 0; j < currentList.size(); j++) {
//        if (currentList.get(j).get(1).equals(currentList.get(j + 1).get(0))){
//        // sprawdza czy jak jest np lista [[1, 2], [2, 3], [4, 5], [7,8]] to czy 2==2, 3==4, 5==7
//        // jakby tak było i by były takie k po kolei to mamy ciąg arytmatyczny
//        System.out.println("idk");
//        }
//        // idk wysiadam z pociągu
//        }

}
