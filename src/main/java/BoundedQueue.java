import java.sql.Array;

public class BoundedQueue<T> {
    private T[] elements;
    private int first=0;
    private int last=-1;
    private int qty=0;

    public BoundedQueue(int limit){
            elements=(T[]) new Object[limit];
    }

    public  boolean isEmpty(){
        return qty==0;
    }

    public boolean isFull(){
        return qty==elements.length;
    }

    public void enqueue(T element){
        if(isFull()){
            throw new RuntimeException();
        }
        elements[last+1]=element;
        qty++;
        last=last+1;
    }

    public T dequeue(){
        if(isEmpty()){
            return null;
        }
        T toReturn=elements[first];
        first++;
        qty--;
        return toReturn;
    }

    public void dump(){
        for(int i=first;i<last;i++)
            System.out.println(elements[i].toString());
    }

}
