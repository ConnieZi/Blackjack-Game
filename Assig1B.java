
// Assig1B driver program.  This program works as is with
// BlackjackCards class.  The output is in file
// A1B-out.txt.  Note in particular the behavior of the getValue()
// method.

public class Assig1B
{
	public static void main(String [] args)
	{
		BlackjackCards B1 = new BlackjackCards(10);
		
		// Value is 0 because of empty hand
		int val = B1.getValue();
		System.out.println(B1.toString());
		System.out.println("Hand value is " + val);
		System.out.println();
		
		// Single ace has a value of 11
		B1.enqueue(new Card(Card.Suits.Hearts, Card.Ranks.Ace));
		val = B1.getValue();
		System.out.println(B1.toString());
		System.out.println("Hand value is " + val);
		System.out.println();
		
		// 11 + 7 = 18
		B1.enqueue(new Card(Card.Suits.Spades, Card.Ranks.Seven));
		val = B1.getValue();
		System.out.println(B1.toString());
		System.out.println("Hand value is " + val);
		System.out.println();
		
		// Second Ace is now a 1 since an 11 would cause the hand
		// to bust.  Total is now 11 + 7 + 1 = 19
		B1.enqueue(new Card(Card.Suits.Diamonds, Card.Ranks.Ace));
		val = B1.getValue();
		System.out.println(B1.toString());
		System.out.println("Hand value is " + val);		
		System.out.println();
		
		// Now first Ace is also a 1, so the total actually goes
		// down to 17.  Total is now 1 + 7 + 1 + 8 = 17
		B1.enqueue(new Card(Card.Suits.Clubs, Card.Ranks.Eight));
		val = B1.getValue();
		System.out.println(B1.toString());
		System.out.println("Hand value is " + val);	
	}	
}
