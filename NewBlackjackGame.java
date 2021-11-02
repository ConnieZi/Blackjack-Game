
import java.util.Scanner;

public class NewBlackjackGame {

    private static int rounds;
    private static BlackjackCards shoe;
    private static BlackjackCards dealerHand;
    private static BlackjackCards playerHand;
    private static BlackjackCards discardPile;

    private static double playerMoney;

    public static void main(String[] args) {
        //creat the shoe
        shoe = new BlackjackCards(Integer.parseInt(args[0]) * 52);
        for (Card.Suits s : Card.Suits.values()) {
            for (Card.Ranks r : Card.Ranks.values()) {
                for (int i = 0; i < Integer.parseInt(args[0]); i++)
                    shoe.enqueue(new Card(s, r));
            }
        }

        playerHand=new BlackjackCards(22);
        dealerHand=new BlackjackCards(22);
        discardPile=new BlackjackCards(Integer.parseInt(args[0]) *52);

        int origSize=Integer.parseInt(args[0]) * 52;

        System.out.println("Starting Blackjack with " + Integer.parseInt(args[0]) + " decks in the shoe\n");

        int dealerWin=0;
        int playerWin=0;
        int pushes=0;

        shoe.shuffle();

        //How much money the player bring?
        System.out.println("How much money do you have with you?");
        Scanner totalMoney = new Scanner(System.in);
        playerMoney= totalMoney.nextDouble();

        Scanner userInput = new Scanner(System.in);

        while(playerMoney>0)
        {
            //Take a bet
            System.out.println("You have $" + playerMoney + ", how much would you like to bet?");
            double playerBet = userInput.nextDouble();
            boolean endRound = false;

            if(playerBet > playerMoney){
                System.out.println("You cannot bet more than you have.");
                break;
            }

            //Start
            System.out.println("Dealing...");
            //First 2 cards respectively
            int j;
            for (j = 0; j < 2; j++) {
                playerHand.enqueue(shoe.get(j));
                shoe.dequeue();
            }
            for (j = 2; j < 4; j++) {
                dealerHand.enqueue(shoe.get(j));
                shoe.dequeue();
            }

            //Initially blackjack case
            if (playerHand.getValue() == 21 || dealerHand.getValue() == 21) {

                //Display player cards
                System.out.println("Player: " + playerHand.toString() + " : " + playerHand.getValue());
                //Display dealer cards, note that the second card should be hidden!
                System.out.println("Dealer: " + dealerHand.toString() + " : " + dealerHand.getValue());

                if (playerHand.getValue() == 21 && dealerHand.getValue() < 21) {
                    System.out.println("Result: Player Blackjack wins!");
                    playerWin++;
                } else if (playerHand.getValue() < 21 && dealerHand.getValue() == 21) {
                    System.out.println("Result: Dealer Blackjack wins!");
                    dealerWin++;
                } else if (playerHand.getValue() == 21 && dealerHand.getValue() == 21) {
                    System.out.println("Result: Push!");
                    pushes++;
                }

                for (j = 0; j < 2; j++)
                    discardPile.enqueue(playerHand.get(j));
                for (j = 0; j < 2; j++)
                    discardPile.enqueue(dealerHand.get(j));

                playerHand.clear();
                dealerHand.clear();

                break;
            }

            while(true) {
                //Display player cards
                System.out.println("Player: " + playerHand.toString() + " : " + playerHand.getValue());
                //Display dealer cards, note that the second card should be hidden!
                System.out.println("Dealer: " + dealerHand.get(0).toString() + " and [hidden]");

                //User choose what to do next
                System.out.println("Would you like to (1)Hit or (2)Stand");
                int userResponse = userInput.nextInt();

                //Hit
                if (userResponse == 1) {
                    playerHand.enqueue(shoe.getFront());
                    System.out.println("Player hits:" + shoe.getFront().toString());
                    shoe.dequeue();

                    //Bust if the value is over 21
                    if (playerHand.getValue() >21) {
                        System.out.println("Player BUSTS:" + playerHand.toString() + " : " + playerHand.getValue());
                        System.out.println("Dealer: " + dealerHand.toString() + " : " + dealerHand.getValue());
                        System.out.println("Result: Dealer wins!");
                        playerMoney -= playerBet;
                        dealerWin++;
                        endRound = true;
                        break;
                    }
                }

                //Stand
                if (userResponse == 2) {
                    System.out.println("Player STANDS: " + playerHand.toString() + " : " + playerHand.getValue());
                    //Reveal Dealer Cards
                    System.out.println("Dealer: " + dealerHand.toString() + " : " + dealerHand.getValue());
                    break;
                }
            }


            //See if dealer has more points than player
            if(dealerHand.getValue() > playerHand.getValue()&&endRound == false)
            {
                System.out.println("Result: Dealer wins!");
                playerMoney -= playerBet;
                dealerWin++;
                endRound = true;
            }

            //Dealer hits before stands at 17
            while(dealerHand.getValue() <17&&endRound == false)
            {
                dealerHand.enqueue(shoe.getFront());
                System.out.println("Dealer hits: " + shoe.getFront().toString());
                shoe.dequeue();
            }

            //Determine if dealer busted
            if((dealerHand.getValue()>21)&& endRound == false){
                System.out.println("Dealer BUSTS:" + dealerHand.toString() + " : " + dealerHand.getValue());
                System.out.println("Result: Player wins!");
                playerMoney += playerBet;
                playerWin++;
                endRound = true;
            }

            //Determine if push
            if((dealerHand.getValue() == playerHand.getValue()) && endRound == false){
                System.out.println("Dealer STANDS: " + dealerHand.toString() + " : " + dealerHand.getValue());
                System.out.println("Result: Push!");
                pushes++;
                endRound = true;
            }

            //Determine if player wins
            if((playerHand.getValue() > dealerHand.getValue()) && endRound == false){
                System.out.println("Dealer STANDS: " + dealerHand.toString() + " : " + dealerHand.getValue());
                System.out.println("Result: Player wins!");
                playerMoney += playerBet;
                playerWin++;
                endRound = true;
            }
            else if(endRound == false) //dealer wins
                {
                    System.out.println("Dealer STANDS: " + dealerHand.toString() + " : " + dealerHand.getValue());
                    System.out.println("Result: Dealer wins!");
                    playerMoney -= playerBet;
                    dealerWin++;
                }

            //check the size of the shoe at the end of each round
            if (shoe.size() <= origSize / 4) {
                for (int k = 0; k < discardPile.size(); k++)
                    shoe.enqueue(discardPile.get(k));
                discardPile.clear();
                shoe.shuffle();
                System.out.print("Reshuffling the shoe in round ");
                System.out.println("\n");
            }

            for (int k = 0; k < playerHand.size(); k++)
            discardPile.enqueue(playerHand.get(k));
            for (int k = 0; k < dealerHand.size(); k++)
                discardPile.enqueue(dealerHand.get(k));

            playerHand.clear();
            dealerHand.clear();
            System.out.println("End of A Round.\n");
        }
        //Game is over
        System.out.println("Game over! You lost all your money. :(");
        System.out.println("Here are the results:");
        System.out.println("Dealer wins: " + dealerWin);
        System.out.println("Player Wins: " + playerWin);
        System.out.println("Pushes: " + pushes);

        //Close Scanner
        userInput.close();
    }

}
