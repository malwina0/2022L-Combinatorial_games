import java.util.*;

public class Test2 {
    public static void main(String[] args) {
        //a tu sobie tylko testuje czy to co robie ma sens
        int n= 200;
        int k = 6;
        System.out.println("----------------------------------");
        List<Integer> numbers = SetSampling.GenerateSetWithProgression(n,k);
        Set<ArrayList<Integer>> sequences = Malwina.getSequences(numbers, k);
        System.out.println(numbers);
        System.out.println("Sequences:");
        System.out.println(sequences);
        List<ArrayList<Integer>> nnbg = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> person = new ArrayList<>();
        person.add(1);
        person.add(2);
        person.add(3);
        nnbg.add(person);
        ArrayList<Integer> person2 = new ArrayList<>();
        person2.add(4);
        person2.add(5);
        person2.add(6);
        nnbg.add(person2);
        //System.out.println(nnbg);
    }
}
