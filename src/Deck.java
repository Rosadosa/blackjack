import java.util.ArrayList;
import java.util.List;

public class Deck {
    public List<Card> makeDeck(){
        List<Card> deck = new ArrayList<Card>();
        String[] colors = {"Harten", "Ruiten", "Schoppen", "Klaveren"};
        String[] plaatjes = {"A", "B", "V", "H"};
        for (String col : colors){
            for (int i=2; i<11; i++){
                Card card = new Card(i, col);
                deck.add(card);
            }
            for (String plaatje : plaatjes){
                Card card = new Card(col, plaatje);
                deck.add(card);
            }
        }
        return deck;
    }
}
