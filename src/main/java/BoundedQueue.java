
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
        qty++;
        last++;
        last%=elements.length;
        elements[last]=element;

    }

    public T dequeue(){
        if(isEmpty()){
            return null;
        }
        T toReturn=elements[first];
        first++;
        first%=elements.length;
        qty--;
        return toReturn;
    }

    public void dump(){
        for(int i=first;i<last;i++)
            System.out.println(elements[i].toString());
    }
}
