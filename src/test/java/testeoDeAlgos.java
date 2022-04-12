import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testeoDeAlgos {
    @Test
    public void testeoInfija(){
        // inyecto entrada estandard
        String input= "3 + 10 * 2 / 1";
        InputStream inputstream= new ByteArrayInputStream(input.getBytes());
        System.setIn(inputstream);
        Double rta = new Evaluator().evaluate();
        assertEquals(23, rta);
        System.setIn(System.in);
    }
}
