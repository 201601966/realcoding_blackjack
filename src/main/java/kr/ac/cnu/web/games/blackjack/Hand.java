package kr.ac.cnu.web.games.blackjack;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rokim on 2018. 5. 26..
 */
public class Hand {
    private Deck deck;
    @Getter
    private List<Card> cardList = new ArrayList<>();

    public Hand(Deck deck) {
        this.deck = deck;
    }

    public Card drawCard() {
        Card card = deck.drawCard();
        cardList.add(card);
        return card;
    }

    public int getCardSum() {
        int sum=0;
        for(int i=0;i<cardList.size();i++){
            if(10<cardList.get(i).getRank()&&cardList.get(i).getRank()<14){
                sum+=10;
            }
            else{
                sum+=cardList.get(i).getRank();
            }
        }
        if(checkAce()==true && sum+11 < 22) {
            sum += 11;
        }
        return sum;
    }

    public boolean checkAce() {
        for(int i=0;i<cardList.size();i++) {
            if (cardList.get(i).getRank() == 1) {
                return true;
            }
        }
        return false;
    }

    public void reset() {
        cardList.clear();
    }
}
