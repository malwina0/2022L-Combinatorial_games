import java.util.Set;

public class MySet {
    Set<Integer> nums, coloured, colouredPlayer;
    int lenSet, diff;

    public boolean blockOpponent(){
        return coloured.size() < colouredPlayer.size();
    }
    public boolean blockOpponentNow(){
        return coloured.size() == lenSet - 1;
    }
}
