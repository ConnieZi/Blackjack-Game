

public class BlackjackCards extends RandIndexQueue<Card>{

    public BlackjackCards(int sz){
        super(sz);
    }

    //give the value of the entire hand
    //get the value of every item in the queue
    public int getValue(){
        int sum=0;
        int aceCounter=0;
        for(int i=0;i<size();i++) {
            if (get(i).value() == 11)
                aceCounter++;
            sum = sum + get(i).value2();
        }
            if(aceCounter>=1&&sum+10<21)
                sum=sum+10;

        return sum;
    }
}
