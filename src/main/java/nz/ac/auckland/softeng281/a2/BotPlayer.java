package nz.ac.auckland.softeng281.a2;

import java.util.Random;

/**
 * you should change this class for TASK 1
 */
public class BotPlayer extends Participant {

	public BotPlayer(String name) {
		super(name);

	}

	@Override
	public Action decideAction() {
		// TODO
    if(getCurrentHand().getScore() < 17) {
      return Action.HIT;
    } else {
      return Action.HOLD;
    } 
	}

	@Override
	public int makeABet() {
		// TODO
    Random r = new Random();
    int lowBoundary = 1;
    int highBoundary = 101;
    int randomNumber = r.nextInt(highBoundary - lowBoundary) + lowBoundary;
    return randomNumber;
	}
}