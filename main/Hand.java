import java.util.ArrayList;
import java.util.List;

public class Hand implements Comparable {
    public ArrayList<Card> cards;
    public ArrayList<Card> player_cards;
    public ArrayList<Combination> combinations;
    public Hand(List<Card> player_cards, List<Card> table_cards) {
        this.player_cards = (ArrayList<Card>) player_cards;
        cards = new ArrayList<>(player_cards);
        cards.addAll(table_cards);
        cards.sort((c1, c2) -> Integer.compare(c1.value, c2.value));
        combinations = new ArrayList<>();
        getCombinations();
    }

    private void getCombinations() {
        getHighest();
        getPair();
        getTrips();
        getTwoPair();
        getStraight();
        getFlush();
        getFullHouse();
        getFourKind();
        getStraightFlush();
        getRoyalFlush();
    }

    public void getHighest() {
        Card max_card = cards.get(cards.size()-1);
        combinations.add(new Combination(max_card, CombinationType.Highest));
    }

    public void getPair() {
        Card max_card = null;
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).value == cards.get(i + 1).value) {
                max_card = cards.get(i);
            }
        }
        if (max_card != null)
            combinations.add(new Combination(max_card, CombinationType.Pair));
    }

    public void getTwoPair() {
       Card max_card = null;
       int pair_count = 0;
       for(int i = 0; i <cards.size()-1; i++)
           if(cards.get(i).value == cards.get(i+1).value) {
               max_card = cards.get(i);
               pair_count++;
               i++;
           }
       if(pair_count>=2)
           combinations.add( new Combination(max_card,CombinationType.TwoPair));
    }

    public void  getTrips() {
        Card max_card = null;
        for (int i = 0; i < cards.size() - 2; i++) {
            if (cards.get(i).value == cards.get(i + 1).value && cards.get(i).value == cards.get(i + 2).value)
                max_card =  cards.get(i);
        }
        if(max_card!=null)
            combinations.add(new Combination(max_card,CombinationType.Trips));
    }

    public void getStraight() {
        Card max_straight = null;
        for (int i = 0; i< cards.size(); i++) {
            int value = cards.get(i).value;
            if (
                    !findByValue(value + 1).isEmpty() &&
                            !findByValue(value + 2).isEmpty() &&
                            !findByValue(value + 3).isEmpty() &&
                            !findByValue(value + 4).isEmpty()
            )
                max_straight = cards.get(i);
        }
        if(max_straight !=null)
            combinations.add(new Combination(max_straight,CombinationType.Straight));
    }

    public void getFlush() {
        int Heart_count = 0;
        int Diamond_count = 0;
        int Spades_count = 0;
        int Clubs_count = 0;

        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).suit == Suit.HEART)
                Heart_count++;
            if (cards.get(i).suit == Suit.DIAMOND)
                Diamond_count++;
            if (cards.get(i).suit == Suit.SPADES)
                Spades_count++;
            if (cards.get(i).suit == Suit.CLUB)
                Clubs_count++;
        }
        Suit flush_suit = null;
        if (Heart_count >= 5)
            flush_suit = Suit.HEART;
        if (Diamond_count >= 5)
            flush_suit = Suit.DIAMOND;
        if (Spades_count >= 5)
            flush_suit = Suit.SPADES;
        if (Clubs_count >= 5)
            flush_suit = Suit.CLUB;

        if (flush_suit != null)
            for (int i = cards.size() - 1; i >= 0; i--) {
                if (cards.get(i).suit == flush_suit)
                {
                    combinations.add( new Combination( cards.get(i),CombinationType.Flush));
                    break;
                }
            }
    }

    public void getFullHouse() {
        Card max_triad = null;
        for (Card card : cards) {
            int value = card.value;
            if (findByValue(value).size() >= 3)
                max_triad = card;
        }
        if (max_triad == null)
            return ;

        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            int value = card.value;
            if (findByValue(value).size() >= 2 && value != max_triad.value) {
                combinations.add(new Combination(max_triad, CombinationType.FullHouse));
                break;
            }
        }
    }

    public void getFourKind() {
        Card max_card = null;
        for (int i = 0; i < cards.size() - 3; i++) {
            if (cards.get(i).value == cards.get(i + 1).value && cards.get(i).value == cards.get(i + 2).value &&
                    cards.get(i).value == cards.get(i + 3).value)
                max_card = cards.get(i);
        }
        if(max_card!=null)
            combinations.add(new Combination(max_card,CombinationType.FourKind));
    }

    public void getStraightFlush() {
        Card max_sf = null;
        for (Card card : cards) {
            Suit suit = card.suit;
            int value = card.value;
            if (
                    cards.contains(new Card(value + 1, suit)) &&
                            cards.contains(new Card(value + 2, suit)) &&
                            cards.contains(new Card(value + 3, suit)) &&
                            cards.contains(new Card(value + 4, suit))
            )
                max_sf = card;
        }
        if(max_sf!=null)
            combinations.add(new Combination(max_sf,CombinationType.StraightFlush));
    }

    public void getRoyalFlush() {
        ArrayList<Card> tens = findByValue(10);
        if (tens.size() == 0)
            return;

        for (Card ten : tens) {
            Suit ten_suit = ten.suit;
            if (
                    cards.contains(new Card(11, ten_suit)) &&
                            cards.contains(new Card(12, ten_suit)) &&
                            cards.contains(new Card(13, ten_suit)) &&
                            cards.contains(new Card(14, ten_suit))
            ) {
                combinations.add(new Combination(ten, CombinationType.RoyalFlush));
                break;
            }
        }
    }

    ArrayList<Card> findByValue(int value) {
        ArrayList<Card> new_cards = new ArrayList<>();
        for (Card card : cards) {
            if (card.value == value)
                new_cards.add(card);
        }
        return new_cards;
    }

    @Override
    public int compareTo(Object o) {
        Hand otherHand = (Hand)o;
        CombinationType[] possible_combinations = CombinationType.values();
        int compareResult = 0;
        for(int i = possible_combinations.length-1; i >=0; i--)
        {
            Combination thisHandCombination = this.getCombination(possible_combinations[i]);
            Combination otherHandCombination = otherHand.getCombination(possible_combinations[i]);
            if(thisHandCombination == null && otherHandCombination == null)
                continue;
            if(thisHandCombination == null)
                return -1;
            if(otherHandCombination == null)
                return 1;
            compareResult = thisHandCombination.compareTo(otherHandCombination);
            if(compareResult==0)
                continue;
            return compareResult;
        }
        return compareResult;
    }

    Combination getCombination(CombinationType type)
    {
        for(Combination combination : combinations) {
            if (combination.combinationType == type)
                return combination;
        }
        return null;
    }
}

