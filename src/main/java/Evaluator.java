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
            put("^",4);
            put("(",5);
        }
    };

    boolean[][] precedenceMatriz =
            {       {true, true, false, false,false,false,true},
                    {true, true, false, false,false,false,true},
                    {true, true, true, true,false,false,true},
                    {true, true, true, true,false,false,true},
                    {true, true, true, true,false,false,true},
                    {false, false, false, false,false,false,false},
            };

    public double evaluate() {
        double resp = 0;
        Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
        System.out.println("Introduzca la expresión en notación infija:");
        inputScanner.hasNextLine();

        String line = inputScanner.nextLine();

        line=infijaToPostfija(line);

        Stack<Double> myStack = new Stack<>();

        Scanner lineScanner = new Scanner(line).useDelimiter("\\s+");

        while (lineScanner.hasNext()) {
            String token = lineScanner.next();
            if (token.matches("[\\^+*\\/-]")) {
                if (token.matches("\\+"))
                    resp = myStack.pop() + myStack.pop();
                else if (token.matches("\\*"))
                    resp = myStack.pop() * myStack.pop();
                else if (token.matches("\\/")) {
                    double aux = myStack.pop();
                    resp = myStack.pop() / aux;
                }
                else if(token.matches("\\^")){
                    double aux=myStack.pop();
                    resp=Math.pow(myStack.pop(),aux);
                }
                else {
                    double aux=myStack.pop();
                    resp = myStack.pop() - aux;
                }
                myStack.push(resp);
            } else {
                double num = Double.valueOf(token);
                myStack.push(num);
            }
        }
        return resp;
    }

    private String infijaToPostfija(String line) {
        Stack<String> myStack = new Stack<>();
        StringBuilder toReturn=new StringBuilder();
        int numberCount=0,operandCount=0;

        Scanner lineScanner = new Scanner(line).useDelimiter("\\s+");

        while(lineScanner.hasNext()){
            String token=lineScanner.next();
            if(token.matches("[(^+*/-]")){
                    while (!myStack.isEmpty() && getPrecedence(myStack.peek(),token)){
                        toReturn.append(myStack.pop()+" ");
                }
                myStack.push(token);
                if(!token.equals("("))
                    operandCount++;
            }
            else if(token.matches("[)]")) {
                while (!myStack.peek().equals("(")) {
                    toReturn.append(myStack.pop()+" ");
                    if(myStack.isEmpty())
                        throw new IllegalArgumentException("Falta parentesis de inicio");
                }
                myStack.pop(); //saco el (
            }
            else{
                Double.valueOf(token);
                numberCount++;
                toReturn.append(token+" ");
            }
        }
        if (numberCount-1!=operandCount)
            throw new IllegalArgumentException("Cantidad incorrecta de operadores");
        while (!myStack.isEmpty()) {
            if(myStack.peek().equals("("))
                throw new IllegalArgumentException("Falta parentesis de cierre");
            toReturn.append(myStack.pop()+" ");
        }
        return toReturn.toString();
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
