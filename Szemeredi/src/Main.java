import java.util.*;

public class Main {

    public static void main(String[] args) {

        int n=100;
        int k=3;

//        create random set of numbers:
        List<Integer> numbers = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < n; i++){
            numbers.add(rand.nextInt(10*n));
        }
        System.out.println(numbers);


//        check if there is arithmetic sequence of length=k:

//        lista gdzie na n-tym miejscu jest liczba ile jest par, między którymi różnica to n
        List<Integer> howManyAbs = new ArrayList<>(Arrays.asList(new Integer[10*n]));
        Collections.fill(howManyAbs, 0);

//        lista gdzie na n-tym miejscu jest lista par, między ktorymi jest różnica n
        List<List<Integer>> whichParsWithThisAbs = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++){
            for (int j = i+1; j < numbers.size(); j++){
                int index = Math.abs(numbers.get(i) - numbers.get(j));
                howManyAbs.set(index, howManyAbs.get(index)+1);

                ArrayList<Integer> par = new ArrayList<>();
                par.add(numbers.get(i), numbers.get(j));
                whichParsWithThisAbs.add(new ArrayList<>());
            }
        }
        System.out.println(howManyAbs);
    }

}
