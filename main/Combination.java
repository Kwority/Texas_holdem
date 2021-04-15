import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class Combination implements Comparable{
    Card highCard;
    CombinationType combinationType;

    @Override
    public int compareTo(Object o) {
        Combination otherCombination = (Combination) o;
        int compareResult = Integer.compare(this.combinationType.ordinal(), otherCombination.combinationType.ordinal());
        if(compareResult!=0)
            return compareResult;
        else
            return Integer.compare(this.highCard.value, otherCombination.highCard.value);
    }
}
