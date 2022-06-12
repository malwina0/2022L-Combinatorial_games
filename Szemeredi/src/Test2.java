import java.util.*;

public class Test2 {

    public static void main(String[] args) {
        //a tu sobie tylko testuje czy to co robie ma sens
        int n = 40;
        int k = 4;
        System.out.println("----------------------------------");
        List<Integer> numbers = SetSampling.GenerateSetWithProgression(n,k);
        Set<ArrayList<Integer>> sequences = SequenceOps.getSequences(numbers, k);
        System.out.println(numbers);
        System.out.println("Sequences:");
        System.out.println(sequences);
        Map<List, ArrayList<Integer>> mapa = new HashMap<>();
//        ArrayList<Integer> graczeProg = new ArrayList<>(Arrays.asList(new Integer[2]));
//        Collections.fill(graczeProg, k);

        for (ArrayList<Integer> ciag: sequences) {
            ArrayList<Integer> graczeProg = new ArrayList<>(Arrays.asList(new Integer[2]));
            Collections.fill(graczeProg, k);
            mapa.put(ciag, graczeProg);
        }
        System.out.println("Mapa: " + mapa);

        ProgressionChecker.updateProgress(mapa, 116, 0, k);
        System.out.println("-------------jnhbgvfrtdfyguh---------");
        System.out.println("Mapa: " + mapa);

        ProgressionChecker.updateProgress(mapa, 122, 1, k);
        System.out.println("-------------jnhbgvfrtdfyguh---------");
        System.out.println("Mapa: " + mapa);

        ProgressionChecker.updateProgress(mapa, 113, 0, k);
        System.out.println("-------------jnhbgvfrtdfyguh---------");
        System.out.println("Mapa: " + mapa);

        ProgressionChecker.updateProgress(mapa, 111, 0, k);
        System.out.println("-------------jnhbgvfrtdfyguh---------");
        System.out.println("Mapa: " + mapa);

        System.out.println("oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        Map.Entry<List, ArrayList<Integer>> komputerToWin = ProgressionChecker.checkHowManyToWin(mapa, 1, k);
        System.out.println("Komputer toWin " + komputerToWin);

        System.out.println("oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        Map.Entry<List, ArrayList<Integer>> graczToWin = ProgressionChecker.checkHowManyToWin(mapa, 0, k);
        System.out.println("Gracz toWin " + graczToWin);

        System.out.println("oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        System.out.println(ProgressionChecker.checkWhoCloserToWin(graczToWin, komputerToWin));

//        System.out.println("Pierwsza liczba wybrana przez komputer: " + SequenceOps.selectFirstNumber(sequences));
//        List<ArrayList<Integer>> nnbg = new ArrayList<ArrayList<Integer>>();
//        ArrayList<Integer> person = new ArrayList<>();
//        person.add(2);
//        person.add(3);
//        person.add(4);
//        System.out.println("TEST");
//        System.out.println(person);
//        Set<ArrayList<Integer>> sequences2 = SequenceOps.getSequences(person, 2);
//        System.out.println(sequences2);
//        nnbg.add(person);
//        ArrayList<Integer> person2 = new ArrayList<>();
//        person2.add(4);
//        person2.add(5);
//        person2.add(6);
//        nnbg.add(person2);
        //System.out.println(nnbg);
    }
}