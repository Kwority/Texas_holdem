import org.junit.Test;

import java.util.ArrayList;

public class Hand implements Comparable {
    public ArrayList<Card> cards;
    public ArrayList<Card> player_cards;
    int max_power = 0;   //сила комбинации

    @Override
    public int compareTo(Object object) {

        Hand otherHand = (Hand)object;
        cards.sort((c1,c2)->Integer.compare(c1.value,c2.value));

        int compareResult = 0;

        compareResult = Integer.compare(this.getRoyalFlush(),otherHand.getRoyalFlush());
        if(compareResult!=0)
            return compareResult;

        compareResult = Integer.compare(this.getStraightFlush(),otherHand.getStraightFlush());
        if(compareResult!=0)
            return compareResult;

        compareResult = Integer.compare(this.getFourKind(),otherHand.getFourKind());
        if(compareResult!=0)
            return compareResult;

        compareResult = Integer.compare(this.getFullHouse(),otherHand.getFullHouse());
        if(compareResult!=0)
            return compareResult;


        compareResult = Integer.compare(this.getFlush(),otherHand.getFlush());
        if(compareResult!=0)
            return compareResult;


        compareResult = Integer.compare(this.getStraight(),otherHand.getStraight());
        if(compareResult!=0)
            return compareResult;

        compareResult = Integer.compare(this.getTrips(),otherHand.getTrips());
        if(compareResult!=0)
            return compareResult;


        compareResult = Integer.compare(this.getTwoPair(),otherHand.getTwoPair());
        if(compareResult!=0)
            return compareResult;


        compareResult = Integer.compare(this.getPair(),otherHand.getPair());
        if(compareResult!=0)
            return compareResult;

        compareResult = Integer.compare(this.getHighest(),otherHand.getHighest());
        return compareResult;
    }


    private int getHighest() {
            return cards.get(cards.size()-1).value;
    }

    private int getPair() {
        int max_pair = 0;
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).value == cards.get(i + 1).value)
                max_pair =  15 + cards.get(i).value;
        }
        return max_pair;
    }

    private int getTwoPair() {
        int count = 0;
        int last_pair_idx = 0;
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).value == cards.get(i + 1).value) {
                last_pair_idx = i;
                count++;
                i++;
            }
        }

        if (count > 1)
            return 30 + cards.get(last_pair_idx).value;
        return 0;
    }

    private int getTrips() {
        for (int i = 0; i < cards.size() - 2; i++) {
            if (cards.get(i).value == cards.get(i + 1).value && cards.get(i).value == cards.get(i + 2).value)
                return 45 + cards.get(i).value;
        }
        return 0;
    }

    private int getStraight() {
        int max_straight=0;
        for(Card card:cards)
        {
            int value = card.value;
            if(
                    !findByValue(value+1).isEmpty() &&
                            !findByValue(value+2).isEmpty() &&
                            !findByValue(value+3).isEmpty() &&
                            !findByValue(value+4).isEmpty()
            )
                max_straight = 60+value;
        }
        return max_straight;
    }

    private int getFlush() {
        int Heart_count = 0;
        int Diamond_count = 0;
        int Spades_count = 0;
        int Clubs_count = 0;
        for (int i = 0; i < cards.size(); i++) {

            if (cards.get(i).suit == Suit.h)
                Heart_count++;
            if (cards.get(i).suit == Suit.d)
                Diamond_count++;
            if (cards.get(i).suit == Suit.s)
                Spades_count++;
            if (cards.get(i).suit == Suit.c)
                Clubs_count++;
        }

        Suit flush_suit = null;
        if (Heart_count >= 5)
            flush_suit = Suit.h;
        if (Diamond_count >= 5)
            flush_suit = Suit.d;
        if (Spades_count >= 5)
            flush_suit = Suit.s;
        if (Clubs_count >= 5)
            flush_suit = Suit.c;


        if (flush_suit == null)
            return 0;

        for (int i = cards.size() - 1; i >= 0; i--) {
            if (cards.get(i).suit == flush_suit)
                return 80 + cards.get(i).value;
        }
        return 0;
    }

    private int getFullHouse() {

        int max_triad = 0;
        for(Card card : cards)
        {
            int value = card.value;
            if( findByValue(value).size() >= 3)
                max_triad = card.value;
        }
        if(max_triad == 0)
            return 0;

        int finalMax_triad = max_triad;


        for(Card card : cards)
        {
            int value = card.value;
            if( findByValue(value).size() >= 2 && value!=max_triad)
                return 100+max_triad;
        }

        return 0;
    }

    private int getFourKind() {
        for (int i = 0; i < cards.size() - 3; i++) {
            if (cards.get(i).value == cards.get(i + 1).value && cards.get(i).value == cards.get(i + 2).value &&
                    cards.get(i).value == cards.get(i + 3).value)
                return 120 + cards.get(i).value;
        }
        return 0;
    }

    private int getStraightFlush() {

        int max_sf = 0;
        for(Card card: cards) {
            Suit suit = card.suit;
            int value = card.value;
            if(
                    cards.contains(new Card(value+1,suit)) &&
                    cards.contains(new Card(value+2,suit)) &&
                    cards.contains(new Card(value+3,suit)) &&
                    cards.contains(new Card(value+4,suit))
            )
                max_sf = 140 + value;

        }
        return max_sf;
    }

    private int getRoyalFlush() {
        ArrayList<Card> tens = findByValue(10);
        if(tens.size()==0)
            return 0;

        for(Card ten : tens)
        {
            Suit ten_suit = ten.suit;
            if(
                    cards.contains(new Card(11,ten_suit)) &&
                    cards.contains(new Card(12,ten_suit)) &&
                    cards.contains(new Card(13,ten_suit)) &&
                    cards.contains(new Card(14,ten_suit))
            )
                return 1000;
        }
        return 0;
    }

    ArrayList<Card> findByValue(int value){
        ArrayList<Card> new_cards = new ArrayList<>();
        for(Card card: cards) {
            if (card.value == value)
                new_cards.add(card);
        }
        return new_cards;
    }

    @Override
    public String toString() {
        return StringParser.handToString(this);
    }

    @Test
    public void testPower() {
        Hand hand = new Hand();
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(2, Suit.h));
        cards.add(new Card(10, Suit.h));
        cards.add(new Card(3, Suit.h));
        cards.add(new Card(9, Suit.h));
        cards.add(new Card(11, Suit.h));
        cards.add(new Card(11, Suit.h));
        cards.add(new Card(11, Suit.d));
        cards.add(new Card(11, Suit.h));
        cards.add(new Card(13, Suit.h));
        cards.add(new Card(14, Suit.h));
        cards.add(new Card(14, Suit.h));
        cards.add(new Card(12, Suit.h));
        cards.add(new Card(14, Suit.h));
        cards.sort((c1,c2)->Integer.compare(c1.value,c2.value));
        hand.cards = cards;


        System.out.println("Highest power : " + hand.getHighest());
        System.out.println("Pair power : " + hand.getPair());
        System.out.println("Two pair power : " + hand.getTwoPair());
        System.out.println("Tripple power : " + hand.getTrips());
        System.out.println("Straight power : " + hand.getStraight());
        System.out.println("Flush power : " + hand.getFlush());
        System.out.println("Full house power : " + hand.getFullHouse());
        System.out.println("FourKind power : " + hand.getFourKind());
        System.out.println("Straight flush power : " + hand.getStraightFlush());
        System.out.println("Royal flush power : " + hand.getRoyalFlush());

    }
}

