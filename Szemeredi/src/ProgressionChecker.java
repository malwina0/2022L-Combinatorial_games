import java.security.KeyStore;
import java.util.*;

public class ProgressionChecker {


    public static Map<Integer, List<Integer>> CheckProgressions(List<Integer> numbers) {
        int maxlength = Math.min(1,numbers.size());
        Collections.sort(numbers);
        List<Integer> series = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++ ){
            for (int j = i+1; j < numbers.size(); j++){
                List<Integer> singleProgression = new ArrayList<>();
                int first = numbers.get(i);
                int next = numbers.get(j);
                singleProgression.add(first);
                singleProgression.add(next);
                int roznica = Math.abs(numbers.get(j) - numbers.get(i));
                while(numbers.contains(next + roznica)) {
                    next = next + roznica;
                    //System.out.println(next);
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

    public static void updateProgress(Map<List, ArrayList<Integer>> seqWithProgressMap, int liczba, int gracz, int k){
        /**
         * mapa to mapa gdzie kluczem jest ciąg, który można ułożyć, a wartością jest Lista,
         * gdzie na 0-wym miejscu jest ile zostało graczowi do zwycięstwa
         * a na 1-wszym miejscu jest ile zostało komputerowi do zwysięstwa
         *
         * metoda szuka ciągów, w których występuje wybrana przez gracza/komputer liczba a następnie
         * zmniejsza progres wybranego użytkownika o 1 na tym ciągu
         * w przypadku, gdy ten ciąg jest już układany przez przeciwnika, usuwa całkiem ten ciąg, ponieważ
         * nie może on już zostać ułożony przez żadnego z nich
         */
        Iterator<Map.Entry<List, ArrayList<Integer> >> iter = seqWithProgressMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<List, ArrayList<Integer>> e = iter.next();
            if (e.getKey().contains(liczba)) {
                e.getValue().set(gracz, e.getValue().get(gracz) - 1);
                int drugiGracz = (gracz+1) % 2;
                if (e.getValue().get(drugiGracz) < k){ //jeśli drugi miał coś w tym ciągu ułozone to jest zablokowany
                    // ale też gracz który wybrał tę liczbę nie może ułożyć tego ciągu, więc usuwamy ciąg do rozważania
                    iter.remove();
                }
            }
        }
    }

    public static Map.Entry<List, ArrayList<Integer>> checkHowManyToWin(Map<List, ArrayList<Integer>> mapa, int gracz, int k) {
        /**
         *
         * metoda zwraca mapę, gdzie kluczem jest ciąg, którego ułożenia gracz/komputer jest najbliżej
         */
        Iterator<Map.Entry<List, ArrayList<Integer> >> iter = mapa.entrySet().iterator();
        Map.Entry<List, ArrayList<Integer>> toWin = null;
        while (iter.hasNext()) {
            Map.Entry<List, ArrayList<Integer>> e = iter.next();
            if (e.getValue().get( (gracz+1) % 2 ) == k) {
                toWin = e;
                break;
            }
        }
        while (iter.hasNext()) {
            Map.Entry<List, ArrayList<Integer>> e = iter.next();
            if (e.getValue().get( (gracz+1) % 2 ) == k && e.getValue().get(gracz) < toWin.getValue().get(gracz)) {
                toWin = e;
            }
        }
        return toWin;
    }

    public static Integer checkWhoCloserToWin (Map.Entry<List, ArrayList<Integer>> gracz, Map.Entry<List, ArrayList<Integer>> komp){
        /**
         * metoda zwraca 0 gdy gracz jest bliżej zwycięstwa
         * lub 1 gdy komputer jest bliżej zwycięstwa
         */
        if (gracz.getValue().get(0) < komp.getValue().get(1)){
            return 0;
        } else {
            return 1;
        }
    }

}
