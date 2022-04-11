import java.util.Scanner;

public class pruebaScanner {
    public static void main(String[] args){
        Scanner inputScanner=new Scanner(System.in).useDelimiter("\\n");
        System.out.print("Introduzca la expresión en notación postfija: ");
        inputScanner.hasNextLine();

        String line = inputScanner.nextLine();
        Scanner lineScanner = new Scanner(line).useDelimiter("\\s+");
        while(lineScanner.hasNext()) {
            String token = lineScanner.next();
            System.out.println(token);
            if (token.matches("¡!|,|;|##|¿\\?") )
                System.out.println("OK");
            else
                System.out.println("invalid " + token);
        }
    }
}
