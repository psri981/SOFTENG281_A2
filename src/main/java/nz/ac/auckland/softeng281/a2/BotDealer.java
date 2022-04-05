package nz.ac.auckland.softeng281.a2;

import java.util.List;
import java.util.Random;

/**
 * you should change this class for TASK 2
 */
public class BotDealer extends Participant {

	private List<Participant> players;

	public BotDealer(String name, List<Participant> players) {
		super(name);
    this.players = players;
		// ADDHERE
	}

	@Override
	public Action decideAction() {
		// TODO
    int dealerScore = getCurrentHand().getScore();
    int higherHands = 0;
    //if count >= 2 dealer will HIT
    for (Participant player : players) {
			int playerScore = player.getCurrentHand().getScore();
      if (playerScore == 21){
        higherHands++;
      } else if ((playerScore <= 21) && (dealerScore > 21)) {
        higherHands++;
      } else if ((playerScore <= 21) && (dealerScore <= 21)) {
        if (playerScore > dealerScore){
          higherHands++;
        }
      } else {
        //do nothing do not increment higherHands
      }
    }
    if (higherHands >= 2) {
      return Action.HIT;
    } else {
      return Action.HOLD;
    }
	}

	@Override
	/**
	 * do not touch this method
	 */
	public int makeABet() {
		// the Dealer doesn't bet so is always zero
		return 0;
	}
}
