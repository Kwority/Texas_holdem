import org.junit.Assert;

import java.util.ArrayList;

public class Test {
    //тест проверки на равенство
    @org.junit.Test
    public void testEqual() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(3, Suit.h));
        cards.add(new Card(3, Suit.s));
        cards.add(new Card(5, Suit.h));
        cards.add(new Card(6, Suit.d));
        cards.add(new Card(7, Suit.h));
        cards.add(new Card(8, Suit.h));
        cards.add(new Card(9, Suit.h));

        Hand h1 = new Hand();
        Hand h2 = new Hand();

        h1.cards = cards;
        h2.cards = cards;

        Assert.assertEquals(h1.compareTo(h2), 0);

    }

    @org.junit.Test
    public void testCompare_1() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(3, Suit.h));
        cards.add(new Card(3, Suit.s));
        cards.add(new Card(5, Suit.h));
        cards.add(new Card(6, Suit.d));
        cards.add(new Card(7, Suit.h));
        cards.add(new Card(8, Suit.h));
        cards.add(new Card(9, Suit.h));
        Hand h1 = new Hand();
        h1.cards = cards;

        cards = new ArrayList<>();
        cards.add(new Card(4, Suit.h));
        cards.add(new Card(4, Suit.s));
        cards.add(new Card(5, Suit.h));
        cards.add(new Card(6, Suit.d));
        cards.add(new Card(7, Suit.h));
        cards.add(new Card(8, Suit.h));
        cards.add(new Card(9, Suit.h));
        Hand h2 = new Hand();
        h2.cards = cards;

        Assert.assertTrue(h2.compareTo(h1) > 0);

    }

    @org.junit.Test
    public void myTest() {

        Assert.assertTrue(2+2==4);
    }
}

