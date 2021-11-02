
import java.util.Random;

public class RandIndexQueue<T> implements MyQ<T>, Indexable<T> ,Shufflable {

    private int moves;
    private T[] theQueue;
    private int size;
    private int front;
    private int back;

    //constructor: physical size
    public RandIndexQueue(int sz){
        if(sz<=0)
            throw new IllegalArgumentException("Size must be greater or equals 0.");
        @SuppressWarnings("unchecked")
        T[] temp=(T[])new Object[sz];
        theQueue=temp;
        front=0;
        back=0;
        size=0;
    }

    @Override
    public T get(int i) {
        if(size<i+1)
            throw new IndexOutOfBoundsException("Index out of bounds");
        else
            return theQueue[(front+i)% theQueue.length];
    }

    @Override
    public void set(int i, T item) {
        if(size<i+1)
            throw new IndexOutOfBoundsException("Index out of bounds");
        else{
            theQueue[(front+i)% theQueue.length]=item;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return theQueue.length;
    }

    @Override
    public int getMoves() {
        return moves;
    }

    @Override
    public void setMoves(int moves) {
        this.moves=moves;
    }

    @Override
    //Resize functionality in it
    public void enqueue(T newEntry) {
        if(size==0){  //a special case!
            front=0;
            back=0;
            theQueue[0]=newEntry;
        }
        else if (size == theQueue.length)
        {
        //resize here, do not count moves!
            T[] oldQueue=theQueue;
            int oldSize= oldQueue.length;
            int newSize=oldSize*2;
            @SuppressWarnings("unchecked")
            T[] tempQueue = (T[]) new Object[newSize];
            theQueue=tempQueue;  //now theQueue is null, we have to fill it
            for(int i=0;i<oldSize;i++){
                theQueue[i]=oldQueue[front];
                front=(front+1)% oldSize;
            }
            front=0;
            back= oldSize;
            theQueue[back]=newEntry;
        }
        else {
            back = (back + 1) % theQueue.length;
            theQueue[back] = newEntry;
        }
            moves++;
            size++;
    }

    @Override
    public T dequeue() {

        if(isEmpty())
            throw new EmptyQueueException("Queue is empty");
        else {
            T frontItem = theQueue[front];
            theQueue[front] = null;
            front = (front + 1) % theQueue.length;
            size--;
            moves++;

            return frontItem;
        }
    }

    @Override
    // Return the value that used to be in the front
    public T getFront() {

        if(isEmpty())
            throw new EmptyQueueException("Queue is empty");
        else
            return theQueue[front];

    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public void clear() {
        @SuppressWarnings("unchecked")
        T[] temp=(T[])new Object[theQueue.length];
        theQueue=temp;
        size=0;
    }

    @Override
    public void shuffle() {
        Random randomElement1=new Random();
        Random randomElement2=new Random();

        for(int i=0;i<=50;i++){
            int rand1=(randomElement1.nextInt(size) + front) % theQueue.length;
            int rand2=(randomElement2.nextInt(size) + front) % theQueue.length;

            T temp=theQueue[rand1];
            theQueue[rand1]=theQueue[rand2];
            theQueue[rand2]=temp;
        }

    }

    //a copy constructor (somewhere in between)
    public RandIndexQueue(RandIndexQueue<T> old){
        int frontItem=old.front;
        theQueue=(T[])new Object[old.theQueue.length];
        size= old.size;
        for(int i=0;i<size;i++){
            theQueue[i]=old.theQueue[frontItem];
            frontItem=(frontItem+1)% old.theQueue.length;
        }
        front=0;
        back= size-1;
    }

    //check if the data and relative positions are the same
    public boolean equals(RandIndexQueue<T> rhs) {
        int left=front;
        int right=rhs.front;
        if (size != rhs.size)
            return false;
        else
        {
            for (int i = 0; i < size; i++) {
                if (theQueue[left].equals(rhs.theQueue[right])){
                    left=(left+1)% theQueue.length;
                    right=(right+1)% rhs.theQueue.length;
                }
                else
                    return false;
            }
            return true;
        }
    }

    // contents: 0 1 2 3 4 5
    public String toString () {
        int frontItem=front;
        StringBuilder total = new StringBuilder();
        for(int i=0;i<size;i++){
            //System.out.print("Size:" + size + "\tfrontItem:"+frontItem +"\n");
            total.append(theQueue[frontItem].toString()+" ");
            frontItem=(frontItem+1)% theQueue.length;
        }
            return "Contents: "+total.toString();
        }
}
