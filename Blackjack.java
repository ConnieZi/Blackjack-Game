

public class Blackjack {
    private static int rounds;
    private static BlackjackCards shoe;
    private static BlackjackCards dealerHand;
    private static BlackjackCards playerHand;
    private static BlackjackCards discardPile;
  //populate it with all the cards initially, this is where you getting the cards from.
                                  //It's like a big collection of cards. You never call the value for that, but you are
                                  //going to put all the cards in it, then you deal from the BlackjackCards shoe to the
                                  //player and dealer as they need cards.So, every card they get come from the shoe
                //For shoe, you are gonna shuffle

    public static void main(String [] args) {

        rounds = Integer.parseInt(args[0]);
        //creat the shoe
        shoe = new BlackjackCards(Integer.parseInt(args[1]) * 52);
        for (Card.Suits s : Card.Suits.values()) {
            for (Card.Ranks r : Card.Ranks.values()) {
                for (int i = 0; i < Integer.parseInt(args[1]); i++)
                    shoe.enqueue(new Card(s, r));
            }
        }

        playerHand=new BlackjackCards(22);
        dealerHand=new BlackjackCards(22);
        discardPile=new BlackjackCards(Integer.parseInt(args[1])*52);

        int origSize=Integer.parseInt(args[1]) * 52;

        System.out.println("Starting Blackjack with " + rounds + " rounds and " + Integer.parseInt(args[1]) + " decks in the shoe\n");

        int dealerWin=0;
        int playerWin=0;
        int pushes=0;

        shoe.shuffle();

        //Trace
        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            if(i<Integer.parseInt(args[2])) {
                System.out.println("Round " + i + " beginning");

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
                System.out.println("Player: " + playerHand.toString() + " : " + playerHand.getValue());
                System.out.println("Dealer: " + dealerHand.toString() + " : " + dealerHand.getValue());

                //Initially blackjack case
                if (playerHand.getValue() == 21 || dealerHand.getValue() == 21) {
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
                }

                //Normal Case
                while (playerHand.getValue() < 17) {
                    playerHand.enqueue(shoe.getFront());
                    System.out.println("Player hits:" + shoe.getFront().toString());
                    shoe.dequeue();
                }

                if (playerHand.getValue() > 21) {
                    System.out.println("Player BUSTS:" + playerHand.toString() + " : " + playerHand.getValue());
                    System.out.println("Result: Dealer wins!");
                    dealerWin++;

                    for (int k = 0; k < playerHand.size(); k++)
                        discardPile.enqueue(playerHand.get(k));
                    for (int k = 0; k < dealerHand.size(); k++)
                        discardPile.enqueue(dealerHand.get(k));

                    playerHand.clear();
                    dealerHand.clear();


                }

                if (playerHand.getValue() >= 17 && playerHand.getValue() <= 21) {
                    System.out.println("Player STANDS: " + playerHand.toString() + " : " + playerHand.getValue());

                    while (dealerHand.getValue() < 17) {
                        dealerHand.enqueue(shoe.getFront());
                        System.out.println("Dealer hits: " + shoe.getFront().toString());
                        shoe.dequeue();
                    }

                    if (dealerHand.getValue() >= 17 && dealerHand.getValue() <= 21) {
                        System.out.println("Dealer STANDS: " + dealerHand.toString() + " : " + dealerHand.getValue());
                        if (playerHand.getValue() > dealerHand.getValue()) {
                            System.out.println("Result: Player wins!");
                            playerWin++;
                        } else if (playerHand.getValue() < dealerHand.getValue()) {
                            System.out.println("Result: Dealer wins!");
                            dealerWin++;
                        } else {
                            System.out.println("Result: Push!");
                            pushes++;
                        }
                    }

                    if (dealerHand.getValue() > 21) {
                        System.out.println("Dealer BUSTS:" + dealerHand.toString() + " : " + dealerHand.getValue());
                        System.out.println("Result: Player wins!");
                        playerWin++;
                    }

                    for (int k = 0; k < playerHand.size(); k++)
                            discardPile.enqueue(playerHand.get(k));
                    for (int k = 0; k < dealerHand.size(); k++)
                            discardPile.enqueue(dealerHand.get(k));

                    playerHand.clear();
                    dealerHand.clear();

                }

                //check the size of the shoe at the end of each round
                if (shoe.size() <= origSize / 4) {
                    for (int k = 0; k < discardPile.size(); k++)
                        shoe.enqueue(discardPile.get(k));
                    discardPile.clear();
                    shoe.shuffle();
                }
                System.out.println("\n");
            }

            if(i>=Integer.parseInt(args[2]))
            {
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
                        if (playerHand.getValue() == 21 && dealerHand.getValue() < 21)
                            playerWin++;
                        else if (playerHand.getValue() < 21 && dealerHand.getValue() == 21)
                            dealerWin++;
                        else if (playerHand.getValue() == 21 && dealerHand.getValue() == 21)
                            pushes++;

                        for (j = 0; j < 2; j++)
                            discardPile.enqueue(playerHand.get(j));
                        for (j = 0; j < 2; j++)
                            discardPile.enqueue(dealerHand.get(j));

                        playerHand.clear();
                        dealerHand.clear();
                    }

                    //Normal Case
                    while (playerHand.getValue() < 17) {
                        playerHand.enqueue(shoe.getFront());
                        shoe.dequeue();
                    }

                    if (playerHand.getValue() > 21) {
                        dealerWin++;

                        for (int k = 0; k < playerHand.size(); k++)
                            discardPile.enqueue(playerHand.get(k));
                        for (int k = 0; k < dealerHand.size(); k++)
                            discardPile.enqueue(dealerHand.get(k));

                        playerHand.clear();
                        dealerHand.clear();

                        //jump out of the for loop? And get into next round
                    }

                    if (playerHand.getValue() >= 17 && playerHand.getValue() <= 21) {

                        while (dealerHand.getValue() < 17) {
                            dealerHand.enqueue(shoe.getFront());
                            shoe.dequeue();
                        }

                        if (dealerHand.getValue() >= 17 && dealerHand.getValue() <= 21) {
                            if (playerHand.getValue() > dealerHand.getValue())
                                playerWin++;
                            else if (playerHand.getValue() < dealerHand.getValue())
                                dealerWin++;
                            else
                                pushes++;
                        }

                        if (dealerHand.getValue() > 21) {
                            playerWin++;
                        }

                        for (int k = 0; k < playerHand.size(); k++)
                            discardPile.enqueue(playerHand.get(k));
                        for (int k = 0; k < dealerHand.size(); k++)
                            discardPile.enqueue(dealerHand.get(k));

                        playerHand.clear();
                        dealerHand.clear();

                    }

                    //check the size of the shoe at the end of each round
                    if (shoe.size() <= origSize / 4) {
                        for (int k = 0; k < discardPile.size(); k++)
                            shoe.enqueue(discardPile.get(k));
                        discardPile.clear();
                        shoe.shuffle();
                        System.out.print("Reshuffling the shoe in round " + i);
                        System.out.println("\n");
                    }
            }

        }
            //Final results
            System.out.println("After " + rounds + " rounds, here are the results:");
            System.out.println("Dealer wins: " + dealerWin);
            System.out.println("Player Wins: " + playerWin);
            System.out.println("Pushes: " + pushes);
        }
}
