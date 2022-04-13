import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testeoDeAlgos {
    @Test
    public void testeoInfija(){
        // inyecto entrada estandard
        String input= "3 * ( ( 5 - 10.2 ) / 0.5 ) - 2";
        InputStream inputstream= new ByteArrayInputStream(input.getBytes());
        System.setIn(inputstream);
        Double rta = new Evaluator().evaluate();
        assertEquals(23, rta);
        System.setIn(System.in);
    }

    @Test
    public void testeoDeQueue(){
        BoundedQueue myQueue=new BoundedQueue<>(10);
        myQueue.enqueue(10);
        myQueue.enqueue(20);
        myQueue.enqueue(30);
        myQueue.enqueue(40);
        System.out.println(myQueue.dequeue() );
        System.out.println(myQueue.dequeue() );
        myQueue.enqueue(50);
        myQueue.enqueue(60);
        myQueue.enqueue(70);
        System.out.println("----");
        myQueue.dump();

    }
}
