
// Shufflable interface
// This interface will allow an implementing class to shuffle the contents in
// a pseudo-random way.

public interface Shufflable
{
	public void shuffle();
	// Reorganize the items in the object in a pseudo-random way.  The exact
	// way is up to you but it should utilize a Random object (see Random in 
	// the Java API).  Note that this should not change the size of the
	// collection.
}