package kr.ac.cnu.web.games.blackjack;

import java.util.Map;

/**
 * Created by rokim on 2018. 5. 27..
 */
public class Evaluator {
    private Map<String, Player> playerMap;
    private Dealer dealer;

    public Evaluator(Map<String, Player> playerMap, Dealer dealer) {
        this.playerMap = playerMap;
        this.dealer = dealer;
    }

    public boolean evaluate() {
        if (playerMap.values().stream().anyMatch(player -> player.isPlaying())) {
            return false;
        }

        int dealerResult = dealer.getHand().getCardSum();

        if (dealerResult > 21) {

            playerMap.forEach((s, player) ->{
                int playerResult_2 = player.getHand().getCardSum();
                if (playerResult_2 > 21) {
                    if(player.isDoubleDown() == false) {
                        player.lost();
                    }
                    else {
                        player.doubleLost();
                    }
                }
                else{
                    if(player.isDoubleDown() == false) {
                        player.win();
                    }
                    else {
                        player.doubleWin();
                    }
                }
            });
            return true;
        }//20180610_25_넘으면_패배

        playerMap.forEach((s, player) -> {
            int playerResult = player.getHand().getCardSum();
            if (playerResult > 21) {
                if(player.isDoubleDown() == false) {
                    player.lost();
                }
                else {
                    player.doubleLost();
                }
            } else if (playerResult > dealerResult) {
                if(player.isDoubleDown() == false) {
                    player.win();
                }
                else {
                    player.doubleWin();
                }
            } else if (playerResult == dealerResult) {
                player.tie();
            } else {
                if(player.isDoubleDown() == false) {
                    player.lost();
                }
                else {
                    player.doubleLost();
                }
            }
        });

        return true;
    }


}
