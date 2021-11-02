
// MyQ<T> interface
// implement them correctly in your RandIndexQueue<T> class.km

// The MyQ<T> interface extends the textbook's QueueInterface<T> and
// adds the setMoves() and getMoves() methods.  Thus, MyQ<T> is incorporating
// the functionalities of both QueueInterface<T> and Moves.  It also
// has a few extra methods, as explained below.

// For the details of the queue operations, see QueueInterface<T>.  Note
// that you will need QueueInterface.java in order to use this interface.

// The interface will be implemented:
// 1) The underlying data must be a simple Java array
// 2) No Java collection classes may be used in this implementation.  All of
//    the methods must act directly on the underlying array
// 3) The enqueue() and dequeue() methods require only a single data assignment
//    each.  In other words, there should be no shifting to create or to fill
//    a space in your array. 
// 4) The enqueue() method must always succeed (barring some extreme event).  This
//    means that the implementation have a way to dynamically resize the
//    underlying array when necessary. 

public interface MyQ<T> extends QueueInterface<T>
{
	
	// Return the number of items currently in the MyQ.  Determining the
	// length of a queue can sometimes very useful.
	public int size();
	
	// Return the length of the underlying data structure which is storing the
	// data.  In an array implementation, this will be the length of the array.
	// This method would not normally be part of a queue but is included here to
	// enable testing of your resizing operation.
	public int capacity();

	// Methods to get and set the value for the moves variable.instead
	// of a separate interface these are incorporated into the MyQ<T>
	// interface.  The value of your moves variable should be updated during
	// an enqueue() or dequeue() method call.  However, any data movement required
	// to resize the underlying array should not be counted in the moves.
	public int getMoves();
	public void setMoves(int moves);
}