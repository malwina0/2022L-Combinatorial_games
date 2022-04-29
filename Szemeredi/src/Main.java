import java.util.*;

public class Main {

    public static void main(String[] args) {
        // ostrzegam że robię tu wszystko bardzo siłowo bo w pocągu słabe warunki do myślenia a czas goni
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
        List<List<List<Integer>>> whichParsWithThisAbs = new ArrayList<>();


        for (int i = 0; i < numbers.size(); i++){
            for (int j = i+1; j < numbers.size(); j++){
                int index = Math.abs(numbers.get(i) - numbers.get(j));
                howManyAbs.set(index, howManyAbs.get(index)+1);

                ArrayList<Integer> par = new ArrayList<>(Arrays.asList(new Integer[2]));
                par.add(numbers.get(i), numbers.get(j)); //nie no nei wiem jak to dodawać i czy w ogóle coś takiego ma sens
                // ogólnie chodzi o to że nie wiem jak to zaincjalizować żeby ta gówna lista miała 10*n pustych list
                System.out.println(whichParsWithThisAbs.get(index)); // .add(par); //dodanie do listy znajdującej się na n-tym miejscu pary liczb, które różnią się o n
                //whichParsWithThisAbs.add(new ArrayList<>());
            }
        }
        System.out.println(howManyAbs);
        System.out.println(whichParsWithThisAbs);
        // dla konkretnej różnicy n, sortujemy listę par po pierwszym elemencie
        List<List<Integer>> currentList = null;
        for (int i = 0; i < whichParsWithThisAbs.size(); i++)
            currentList = whichParsWithThisAbs.get(i);
            currentList.sort((l1, l2) -> l1.get(0).compareTo(l2.get(0)));
            // sprawdzamy czy są kolejnymi liczbami w ciągu
            for (int j = 0; j < currentList.size(); j++) {
                if (currentList.get(j).get(1).equals(currentList.get(j + 1).get(0))){
                    // sprawdza czy jak jest np lista [[1, 2], [2, 3], [4, 5], [7,8]] to czy 2==2, 3==4, 5==7
                    // jakby tak było i by były takie k po kolei to mamy ciąg arytmatyczny
                    System.out.println("idk");
                }
                // idk wysiadam z pociągu
            }
    }

}
