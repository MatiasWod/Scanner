import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Evaluator {
    Map<String, Integer> mapping = new HashMap<String, Integer>() {
        {
            put("+", 0);
            put("-", 1);
            put("*", 2);
            put("/", 3);
        }
    };

    boolean[][] precedenceMatriz =
            {{true, true, false, false},
                    {true, true, false, false},
                    {true, true, true, true},
                    {true, true, true, true},
            };

    Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
    String line = inputScanner.nextLine();
    Scanner lineScanner = new Scanner(line).useDelimiter("\\s+");

    public double evaluate() {
        double resp = 0;
        System.out.println("Introduzca la expresión en notación postfija:");
        inputScanner.hasNextLine();
        Stack<Double> myStack = new Stack<>();
        while (lineScanner.hasNext()) {
            String token = lineScanner.next();
            if (token.matches("[+*/\\-]")) {
                if (token.matches("\\+"))
                    resp = myStack.pop() + myStack.pop();
                else if (token.matches("\\*"))
                    resp = myStack.pop() * myStack.pop();
                else if (token.matches("/"))
                    resp = myStack.pop() / myStack.pop();
                else {
                    resp = myStack.pop() - myStack.pop();
                }
                myStack.push(resp);
            } else {
                double num = Double.valueOf(token);
                myStack.push(num);
            }
        }
        return resp;
    }

    private String infijaToPostfija() {
        {
            String postfija= "";
            Stack<String> theStack= new Stack<String>();

            while( lineScanner.hasNext() )   {
                String currentToken = lineScanner.next();

                if ( currentToken.matches("[+*/\\-]") ) {
                    postfija+= String.format("%s ", currentToken);
                }
                else {
                    while (!theStack.empty()  && getPrecedence(theStack.peek(), currentToken) ) {
                        postfija+= String.format("%s ", theStack.pop() );
                    }
                    theStack.push(currentToken);
                }
            }
            while ( !theStack.empty() ) {
                postfija+= String.format("%s ", theStack.pop() );
            }
            return postfija;
        }

    }

    private boolean getPrecedence(String tope, String current) {
        Integer topeIndex;
        Integer currentIndex;

        if ((topeIndex = mapping.get(tope)) == null)
            throw new RuntimeException(String.format("tope operator %s not found", tope));

        if ((currentIndex = mapping.get(current)) == null)
            throw new RuntimeException(String.format("current operator %s not found", current));

        return precedenceMatriz[topeIndex][currentIndex];
    }
}
