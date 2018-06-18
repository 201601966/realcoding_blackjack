package kr.ac.cnu.web.games.blackjack;

import kr.ac.cnu.web.exceptions.BetTooMuchException;
import kr.ac.cnu.web.exceptions.NoUserException;
import kr.ac.cnu.web.exceptions.NotEnoughBalanceException;
//import kr.ac.cnu.web.exceptions.BetZeroException;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by rokim on 2018. 5. 26..
 */
public class Player {
    @Getter
    private long balance;
    @Getter
    private long currentBet;
    @Getter
    private boolean isPlaying;
    @Getter
    private Hand hand;

    public Player(long seedMoney, Hand hand) {
        this.balance = seedMoney;
        this.hand = hand;

        isPlaying = false;
    }

    public void reset() {
        hand.reset();
        isPlaying = false;
    }

    public void placeBet(long bet) {

        if (bet > 10000) {
            throw new BetTooMuchException();
        }
        if(bet == 0){ // bet금액이 0일때
            throw new NoUserException();
        }
        if(balance <= 1000) {
            bet = balance;
        }
        if(balance < bet) {
            balance -= bet/2;
             throw new NotEnoughBalanceException();
        }
        else{
            balance -= bet/2;
        }

        currentBet = bet;

        isPlaying = true;
    }

    public void deal() {
        hand.drawCard();
        hand.drawCard();
    }

    public void win() {
        balance += currentBet * 1.5;
        //currentBet = 0;
    }

    public void tie() {
        balance += currentBet;
        //currentBet = 0;
    }

    public void lost() {
        //currentBet = 0;
    }

    public Card hitCard() {
        if(hand.getCardSum() < 21){
            return hand.drawCard();
        }
        stand();
        return null;
    }

    public void stand() {
        this.isPlaying = false;
    }

}
