import org.junit.Assert;
import java.util.ArrayList;

public class Main {
    @org.junit.Test
    public void testCompare_1() {
        ArrayList<Card> table_cards = new ArrayList<>();
        table_cards.add(new Card(Value.three, Suit.HEART));
        table_cards.add(new Card(Value.three, Suit.SPADES));
        table_cards.add(new Card(Value.five, Suit.HEART));
        table_cards.add(new Card(Value.six, Suit.DIAMOND));
        table_cards.add(new Card(Value.seven, Suit.HEART));

        ArrayList<Card> p1_cards = new ArrayList<>();
        p1_cards.add(new Card(Value.three, Suit.DIAMOND));
        p1_cards.add(new Card(Value.ace, Suit.SPADES));

        ArrayList<Card> p2_cards = new ArrayList<>();
        p2_cards.add(new Card(Value.five, Suit.DIAMOND));
        p2_cards.add(new Card(Value.jack, Suit.SPADES));

        Hand h1 = new Hand(p1_cards,table_cards);
        Hand h2 = new Hand(p2_cards,table_cards);

        Assert.assertTrue(h2.compareTo(h1) > 0);

    }
}

