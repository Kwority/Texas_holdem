import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Objects;

@ToString
@EqualsAndHashCode
public class Card {
    int value;
    Suit suit;

    public Card(int value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    public Card(Value value, Suit suit) {
        this.value =  value.ordinal();
        this.suit = suit;
    }
}
