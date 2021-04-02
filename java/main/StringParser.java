import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StringParser {
    public static Map<Character, Integer> stringToInt;
    public static Map<Integer, String> intToString;
    static {
       stringToInt = new HashMap<>();
       stringToInt.put('2',2);
       stringToInt.put('3',3);
       stringToInt.put('4',4);
       stringToInt.put('5',5);
       stringToInt.put('6',6);
       stringToInt.put('7',7);
       stringToInt.put('8',8);
       stringToInt.put('9',9);
       stringToInt.put('T',10);
       stringToInt.put('J',11);
       stringToInt.put('Q',12);
       stringToInt.put('K',13);
       stringToInt.put('A',14);

       intToString = new HashMap<>();
       intToString.put(2,"2");
       intToString.put(3,"3");
       intToString.put(4,"4");
       intToString.put(5,"5");
       intToString.put(6,"6");
       intToString.put(7,"7");
       intToString.put(8,"8");
       intToString.put(9,"9");
       intToString.put(10,"T");
       intToString.put(11,"J");
       intToString.put(12,"Q");
       intToString.put(13,"K");
       intToString.put(14,"A");
    }

    //Input:
    //4cKs4h8s7s Ad4s Ac4d As9s KhKd 5d6d
    static ArrayList<Hand> readHands(String str)
    {
        ArrayList<Hand> hands = new ArrayList<>();
        ArrayList<Card> table = new ArrayList<>();

        String[] strings = str.split(" ");
        String tableStr = strings[0];
        table = getCards(tableStr);

        for(int k = 1 ; k<strings.length; k++)
        {
            Hand hand = new Hand();
            ArrayList<Card> player_cards = getCards(strings[k]);
            hand.player_cards = player_cards;
            hand.cards = new ArrayList<>(table) ;
            hand.cards.addAll(player_cards);
            hands.add(hand);
        }
        return hands;
    }

    private static ArrayList<Card> getCards(String str)
    {
        ArrayList<Card> cards = new ArrayList<>();
        for (int i=0; i < str.length();i+=2)
        {
            cards.add(new Card(stringToInt.get(str.charAt(i)),Suit.valueOf(String.valueOf(str.charAt(i+1)))));
        }
        return  cards;

    }

    public static String handToString(Hand hand)
    {
        String str = "";
        for(Card card : hand.player_cards)
        {
            str+= intToString.get(card.value);
            str+= Suit.values()[card.suit.ordinal()];
        }
        return str;
    }




}
