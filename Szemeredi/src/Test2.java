import java.util.*;

public class Test2 {
    public static void main(String[] args) {
        //a tu sobie tylko testuje czy to co robie ma sens
        int n = 10;
        int k = 3;
        System.out.println("----------------------------------");
        List<Integer> numbers = SetSampling.GenerateSetWithProgression(n,k);
        Set<ArrayList<Integer>> sequences = Malwina.getSequences(numbers, k);
        System.out.println(numbers);
        System.out.println("Sequences:");
        System.out.println(sequences);
        System.out.println("Pierwsza liczba wybrana przez komputer: " + Malwina.selectFirstNumber(sequences));
        List<ArrayList<Integer>> nnbg = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> person = new ArrayList<>();
        person.add(2);
        person.add(3);
        person.add(4);
        System.out.println("TEST");
        System.out.println(person);
        Set<ArrayList<Integer>> sequences2 = Malwina.getSequences(person, 2);
        System.out.println(sequences2);
        nnbg.add(person);
        ArrayList<Integer> person2 = new ArrayList<>();
        person2.add(4);
        person2.add(5);
        person2.add(6);
        nnbg.add(person2);
        //System.out.println(nnbg);
    }
}
