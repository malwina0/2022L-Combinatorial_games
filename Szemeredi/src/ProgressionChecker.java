import java.security.KeyStore;
import java.util.*;

public class ProgressionChecker {


    public static Map<Integer, List<Integer>> CheckProgressions(List<Integer> numbers) {
        /**
         * Funkcja szuka najdłuższego ciągu i zwraca słownik postaci {klucz: długość, wartość: najdłuższy ciąg}
         */
        // korzystając trochę z tego napisałam getSequences i selectFirstNumber, ale może to się jakoś przyda
        int maxlength = Math.min(1,numbers.size());
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
}
