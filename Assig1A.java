

// Assig1A driver program.  This program must work as is with your
// RandIndexQueue<T> class.
// See file A1A-out.txt for the output.
public class Assig1A
{
	public static void main(String [] args)
	{
		// Testing constructor -- the argument here is the original size
		// of the underlying array.
		MyQ<Integer> theQ = new RandIndexQueue<Integer>(4);

		// Testing enqueue.  Note that your array should have to resize 2 times
		// during these enqueue operations.
		theQ.setMoves(0);
		for (int i = 0; i < 10; i++)
		{
			Integer newItem = Integer.valueOf(i);
			theQ.enqueue(newItem);
			System.out.println(newItem + " added to Q");
		}
		int sz = theQ.size();
		System.out.println("There are " + sz + " items in the Q");
		int cap = theQ.capacity();
		System.out.println("The Q has a capacity of " + cap);

		// The moves should be tracked in the same way that you tracked them
		// in your recitation exercise.  Note, however, that in this implementation
		// the number of moves needed for both enqueue() and dequeue() should only be
		// 1 per operation.  See more on this requirement in the MyQ.java comments
		// and see the output in A1Out.txt
		int mv = theQ.getMoves();
		System.out.println("Total moves needed: " + mv);
		System.out.println();

		// Testing dequeue
		theQ.setMoves(0);
		while (!(theQ.isEmpty()))
		{
			Integer oldItem = theQ.dequeue();
			System.out.println(oldItem + " retrieved from Q");
		}
		mv = theQ.getMoves();
		System.out.println("Total moves needed: " + mv);
		System.out.println();

		// Testing array management
		int count = 1;
		MyQ<String> theQ2 = new RandIndexQueue<String>(5);
		String theItem = new String("Item " + count);
		System.out.println("Adding " + theItem);
		theQ2.enqueue(theItem);
		for (int i = 0; i < 8; i++)
		{
			count++;
			theItem = new String("Item " + count);
			System.out.print("Adding " + theItem);
			theQ2.enqueue(theItem);
			theItem = theQ2.dequeue();
			System.out.println("...and removing " + theItem);
		}
		sz = theQ2.size();
		System.out.println("There are " + sz + " items in the Q");
		cap = theQ2.capacity();
		System.out.println("The Q has a capacity of " + cap);
		System.out.println();
		
		// Testing copy constructor, toString() and equals() method
		RandIndexQueue<Integer> R1 = new RandIndexQueue<Integer>(5);
		RandIndexQueue<Integer> R2 = new RandIndexQueue<Integer>(6);
		for (int i = 0; i < 6; i++)
		{
			R1.enqueue(Integer.valueOf(i));
			R2.enqueue(Integer.valueOf(i));
		}
		checkEqual(R1, R2);  System.out.println();
		
		R1.dequeue();  R2.dequeue();
		R1.enqueue(Integer.valueOf(6));  R1.enqueue(Integer.valueOf(7));
		R2.enqueue(Integer.valueOf(6));  R2.enqueue(Integer.valueOf(7));
		checkEqual(R1, R2);  System.out.println();
		
		R1.enqueue(Integer.valueOf(22));
		R2.enqueue(Integer.valueOf(33));
		checkEqual(R1, R2);  System.out.println();	
		
		RandIndexQueue<Integer> R3 = new RandIndexQueue<Integer>(R1);
		checkEqual(R1, R3);  System.out.println();
		
		R1.dequeue();
		checkEqual(R1, R3);  System.out.println();
		
		// This code will test the Shufflable interface and the Indexable interface.
		System.out.println("Initializing a new RandIndexQueue...");
		RandIndexQueue<Integer> newData = new RandIndexQueue<Integer>(10);
		for (int i = 0; i < 9; i++)
		{
			newData.enqueue(Integer.valueOf(i));
		}
		
		System.out.println(newData.toString());
		
		System.out.println("Removing 3 items then adding 2");
		Integer bogus = newData.dequeue();
		bogus = newData.dequeue();
		bogus = newData.dequeue();
		newData.enqueue(Integer.valueOf(9));
		newData.enqueue(Integer.valueOf(10));
		System.out.println(newData.toString());
		System.out.println();

		// Testing Indexable<T> interface	
		iterate(newData);
		System.out.println(newData.toString());
		System.out.println();
		iterate(newData);
		System.out.println(newData.toString());
		
		System.out.println("\nAbout to test Shufflable...");
		newData.clear();
		for (int i = 0; i < 15; i++)
		{
			newData.enqueue(Integer.valueOf(i));
		}
		System.out.println(newData.toString());
		System.out.println("Capacity: " + newData.capacity());
		System.out.println("Shuffling...");
		newData.shuffle();
		System.out.println(newData.toString());
		System.out.println("Removing 3 and adding 1");
		bogus = newData.dequeue();
		bogus = newData.dequeue();
		bogus = newData.dequeue();
		newData.enqueue(Integer.valueOf(22));
		System.out.println(newData.toString());
		System.out.println("Shuffling again...");
		newData.shuffle();
		System.out.println(newData.toString());
	}
	
	// Generic method to test the Indexable interface
	public static <T> void iterate(Indexable<T> R)
	{
		System.out.println("Testing Indexable...");
		for (int i = 0; i < R.size()-1; i++)
		{
			T item1 = R.get(i);
			int j = i + 1;
			T item2 = R.get(j);
			System.out.println("Swapping " + item1 + " and " + item2);
			R.set(i, item2);
			R.set(j, item1);
		}
	}
	
	public static <T> void checkEqual(RandIndexQueue<T> R1, RandIndexQueue<T> R2)
	{
		// The toString() method should produce the items in logical order (from
		// logical front to logical back) in a single string, separated by blank 
		// spaces.  Note that this will not necessarily be the beginning to the
		// end of the underlying array.
		System.out.println(R1.toString() + " Cap: " + R1.capacity());
		System.out.println(R2.toString() + " Cap: " + R2.capacity());
		if (R1.equals(R2))
			System.out.println("The queues are equal");
		else
			System.out.println("The queues are not equal");
	}
}
