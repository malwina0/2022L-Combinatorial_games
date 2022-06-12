import java.util.ArrayList;
import java.util.Collections;

public class MySet {

    protected ArrayList<Integer> sequence;
    protected int Colored;
    protected ArrayList<Integer> coloredNumbers;

    public MySet(ArrayList<Integer> seq){
        sequence = seq;
        Colored = 0;
        coloredNumbers = new ArrayList<>();
    }

    public void appendColored(int liczba){
        /**
         * Służy do aktualizowania MySetu, gdy gracz wybiera liczbę, która znajduje się w sequence.
         * Zwiększa liczbę pokolorwanych liczb i dodaje tę liczbę do zbioru już pokolorwanych.
         */
        Colored++;
        coloredNumbers.add(liczba);
    }

    @Override
    public String toString() {
        return  "(" + sequence + " " + Colored + ")";
    }
}
