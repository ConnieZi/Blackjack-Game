
// Card class. Use this class as given in the implementation
// of Blackjack card game.
 
public class Card implements Comparable<Card>
{
	// These are enum types that make the suits and ranks of the cards more readable.  They
	// are also used for comparison of cards, as shown in equals and compareTo below.  You
	// can access the types themselves using the class, which you will need to do to
	// initialize your deck of cards.  See A1Help.java for a demo of this.
	public static enum Suits {Spades, Clubs, Diamonds, Hearts}
	public static enum Ranks {Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace}
	
	private Suits suit;
	private Ranks rank;

	public Card(Suits s, Ranks r)
	{
		suit = s;
		rank = r;
	}

	// Equals is only true if both the suit and the rank of the cards match
	public boolean equals(Object rhs)
	{
		Card rside = (Card) rhs;
		if (suit == rside.suit && rank == rside.rank)
			return true;
		else
			return false;
	}

	public String toString()
	{
		return (rank + "-of-" + suit);
	}

	// Compare this object to another Card.  Cards of the same rank are
	// considered equal, regardless of the suit.  Note that this differs
	// from the equals method.  Java enum types are automatically Comparable,
	// based on the order in which the values are defined (smallest to largest).
	// Note: You will likely not need this method in your Blackjack game.
	public int compareTo(Card rhs)
	{
		return rank.compareTo(rhs.rank);
	}
	
	// Blackjack value of a card.  2 through 10 have face value; Jack, Queen and King
	// are 10 and Ace is 11
	public int value()
	{
		if ((rank.compareTo(Ranks.Two) >= 0) && (rank.compareTo(Ranks.Ten) <= 0))
			return rank.ordinal() + 2;
		else if ((rank.compareTo(Ranks.Jack) >= 0) && (rank.compareTo(Ranks.King) <= 0))
			return 10;
		else
			return 11;
	}
		
	// For the value2() method, if the Card is an Ace, return 1; otherwise return the
	// same value() result as above.
	public int value2()
	{
		if (rank.equals(Ranks.Ace))
			return 1;
		else
			return value();
	}	
}
