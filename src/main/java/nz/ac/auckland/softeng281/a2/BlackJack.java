package nz.ac.auckland.softeng281.a2;

import java.util.ArrayList;
import java.util.List;

/**
 * you should change this class for TASK 1, 2, 3, 4.
 */
public class BlackJack {

	private List<Participant> players;
	private Participant dealer;
  private double totalGain;
  private double gain;

	public BlackJack() {
		players = new ArrayList<>();
		dealer = (new BotDealer("Dealer",players));
		players.add(new HumanPlayer("Player1"));
    players.add(new BotPlayer("Bot1"));
    players.add(new BotPlayer("Bot2"));
		// ADDHERE Task 1
	}

	// getter setter for testing purposes
	public List<Participant> getPlayers() {
		return players;
	}

	public Participant getDealer() {
		return dealer;
	}

	public void setPlayers(List<Participant> players) {
		this.players = players;
	}

	public void setDealer(Participant dealer) {
		this.dealer = dealer;
	}

	public static void main(String[] args) {
		BlackJack game = new BlackJack();
		game.start();
	}

	protected void start() {
		Utils.printBlackJack();
		// create a new deck of cards
		Deck deck = new Deck();
		String result;
		do {
			for (Participant player : players) {
				player.play(deck);
			}
			// ADDHERE Task 2
      dealer.play(deck);
			checkWinner();
			System.out.println("Do you want to play again?");
			result = Utils.scanner.next();
			while (!result.equals("yes") && !result.equals("no")) {
				System.out.println("please type either \"yes\" or \"no\"");
				result = Utils.scanner.next();
			}
		} while (result.equals("yes"));
		printPlayerHighestGain();
	}

	public void checkWinner() {
		// TODO Task 3
    for (Participant player : players) {
      int dealerScore = dealer.getCurrentHand().getScore();
      int playerScore = player.getCurrentHand().getScore();
      if (player.getCurrentHand().isBlackJack()){
        System.out.println(player.getName() + " wins");
      }else if ((playerScore <= 21) && (dealerScore > 21)) {
        System.out.println(player.getName() + " wins");
      }else if ((playerScore <= 21) && (dealerScore <= 21)) {
        if (playerScore > dealerScore){
          System.out.println(player.getName() + " wins");
        }
      }else {
          //do nothing
      }
    }
  }
	
  

	public void printPlayerHighestGain() {
		// TODO Task 4
    int handIndex = 0;
    double totalGain = 0;
    String name = new String(" ");

    for (Participant player : players) {
      int numOfHands = player.getHands().size() - 1;
      while (handIndex <= numOfHands){
        int dealerScore = dealer.getHandIndex(handIndex).getScore();
        int playerScore = player.getHandIndex(handIndex).getScore();
        if (player.getHandIndex(handIndex).isBlackJack()){
          gain = player.getHandIndex(handIndex).getBet() * 1.5;
          player.updateTotalGain(gain);
        }else if ((playerScore <= 21) && (dealerScore > 21)) {
          gain = player.getHandIndex(handIndex).getBet();
          player.updateTotalGain(gain);
        }else if ((playerScore <= 21) && (dealerScore <= 21)) {
          if (playerScore > dealerScore){
            gain = player.getHandIndex(handIndex).getBet();
            player.updateTotalGain(gain);
          }
        }else {
          gain = player.getHandIndex(handIndex).getBet() * -1;
          player.updateTotalGain(gain);
        }
      handIndex++;
      }

      if (totalGain <= player.getTotalGain()){
        totalGain = player.getTotalGain();
        name = player.getName();
      }
    }
    System.out.println("The player with the highest gain is: "+ name +" with " + totalGain + " chips"); 

  }

}

