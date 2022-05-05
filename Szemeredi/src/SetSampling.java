import javax.swing.plaf.basic.BasicMenuUI;
import java.util.*;

public class SetSampling {

    public static List<Integer> ChooseRandomSet(int n) {
        int bound = n*2;
        List<Integer> numbers = new ArrayList<>();
        Random rand = new Random();
        boolean repeat = false;
        for (int i = 0; i < n; i++){
            int number = rand.nextInt(bound);
            if (numbers.contains(number)){
                if (!repeat){
                    i = i-1;
                    repeat = true;
                }
            }
            if (!numbers.contains(number))
                numbers.add(number);
                repeat = false;
        }
        Collections.sort(numbers);
        return numbers;
    }

    public static List<Integer> GenerateSetWithProgression(int n, int k){
        List<Integer> numbers = new ArrayList<>();
        while (true) {
            List<Integer> test = ChooseRandomSet(n);
            int length = ProgressionChecker.CheckProgressions(test).keySet().iterator().next();
            if (length >= k) {
                numbers = test;
                break;
            }
        }
        return numbers;
    }
}
