import java.nio.charset.UnsupportedCharsetException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Lexer {

    String input;
    int position = 0;
    boolean readyForOperator = false;

    Lexer(String input){
        this.input=input.replaceAll("\\s","");
    }

    List<Token> analyse() throws Exception {
        List<Token> output = new LinkedList<>();
        while(position<input.length()){
            char c = input.toCharArray()[position];
            if(c == '!' && readyForOperator){
                output.add(new Factorial());
                position++;
                continue;
            }
            else if(c == 'c'){
                position++;
                match('o');
                match('s');
                output.add(new Cosine());
                readyForOperator = false;
                continue;
            }
            else if(c == '*' && readyForOperator){
                output.add(new Multiply());
                readyForOperator = false;
                position++;
                continue;
            }
            else if(c == '-'){
                if(readyForOperator) {
                    output.add(new Subtract());
                    readyForOperator = false;
                    position++;
                    continue;
                }
                else if(Character.isDigit(input.toCharArray()[position+1])){
                    position++;
                    output.add(new Number(number(true)));
                    readyForOperator = true;
                    continue;
                }
            }
            else if(c == '+' && readyForOperator){
                output.add(new Add());
                readyForOperator = false;
                position++;
                continue;
            }
            else if(Character.isDigit(c)){
                output.add(new Number(number(false)));
                readyForOperator = true;
                continue;
            }
            throw new Exception("Character Order Incorrect Or Unrecognised Character in Input");
        }
        return output;
    }

    void match(char m){
        if(input.toCharArray()[position] == m){
            position++;
        } else{
            System.out.println("Wrong Character: " + m +" does not match " + input.toCharArray()[position]);
        }

    }

    double number(boolean negative) throws Exception {
        double output = 0;
        boolean decimal = false;
        int decimalPlaces = 1;

        char c = '0';
        while(Character.isDigit(c)){
            if(position+1>input.length()){
                break;
            }

            c = input.toCharArray()[position];


            if(Character.isDigit(c)){
                if(!decimal){
                    output = output*10 + Character.digit(c,10);
                } else {
                    output += Character.digit(c,10)/(Math.pow(10.0,decimalPlaces++));
                }
            }

            if(position+1<input.length() && input.toCharArray()[position+1]=='.'){
                if(!decimal){
                    decimal = true;
                    position++;
                } else{
                    throw new Exception("Can't have 2 decimal points in a number");
                }

            }
            position++;
        }

        if(!Character.isDigit(c)){
            position--;
        }

        if(negative){
            return -output;
        } else{
            return output;
        }
    }

}
