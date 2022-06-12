import java.util.*;

public class SetSampling {

    public static List<Integer> ChooseRandomSet(int x) {
        int bound = x*5;
        List<Integer> numbers = new ArrayList<>();
        Random rand = new Random();
        //rand.setSeed(23);
        boolean repeat = false;
        for (int i = 0; i < x; i++){
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

    public static List<Integer> GenerateSetWithProgression(int x, int k){
        List<Integer> numbers = new ArrayList<>();
        while (true) {
            List<Integer> test = ChooseRandomSet(x);
            int length = SequenceOps.getSequences(test, k).size();
            if (length >= 1) {
                numbers = test;
                break;
            }
        }
        return numbers;
    }
}
