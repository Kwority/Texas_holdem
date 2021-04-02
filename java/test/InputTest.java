import org.junit.Test;

import java.util.ArrayList;

public class InputTest {



    //Input:
    //4cKs4h8s7s Ad4s Ac4d As9s KhKd 5d6d
    @Test
    public void inputTest()
    {
        ArrayList<Hand> hands = StringParser.readHands("4cKs4h8s7s Ad4s Ac4d As9s KhKd 5d6d");
        hands.sort(Hand::compareTo);
        for(int i = 0; i < hands.size();)
        {
            System.out.print(hands.get(i));
            int k = i+1;
            if(k == hands.size()) {
                i=k;
                continue;
            }
            while (hands.get(i).compareTo(hands.get(k))==0) {
                System.out.print("=" + hands.get(k));
                if (k < hands.size() - 1)
                    k++;
                else
                    break;
            }
            System.out.print(" ");
            i=k;

        }
    }
}
