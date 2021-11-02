
// Indexable<T> interface
// This interface will allow an implementing class to access the data in the
// collection by index -- both to set and get the data at a given location.  Note
// that the index values in this interface should translate into logical locations
// in the underlying collection.  In other words, index i in the get() and set()
// methods below does not necessarily map to location i in an array.

public interface Indexable<T>
{
	// Get and return the value located at logical location i in the implementing
	// collection, where location 0 is the logical beginning of the collection.
	// If the collection has fewer than (i+1) items, throw an IndexOutOfBoundsException 
	public T get(int i);
	
	// Assign item to logical location i in the implementing collection, where location
	// 0 is the logical beginning of the collection.  If the collection has fewer than
	// (i+1) items, throw an IndexOutOfBoundsException
	public void set(int i, T item);
	
	// Return the number of items currently in the Indexable. Note that this is the
	// same method specified in the MyQ<T> interface.  It is fine for a single method
	// to be part of more than one interface
	public int size();
}