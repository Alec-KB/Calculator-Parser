import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Parser {


    List<Token> input;
    Stack<Token> seen = new Stack<>();
    Stack<Integer> states = new Stack<>();
    Token output;
    Token current;

    Parser(List<Token> input){
        this.input = input;
        states.push(0);
        input.add(new EndToken());
        current = input.remove(0);
    }

    void shift(int state){
        seen.push(current);
        current = input.remove(0);
        //System.out.println(current);
        state(state);
    }

    void state(int state){
        states.push(state);
        //System.out.println("Going to state " + states.peek());
        switch (state) {
            case 0 -> state0();
            case 1 -> state1();
            case 2 -> state2();
            case 3 -> state3();
            case 4 -> state4();
            case 5 -> state5();
            case 6 -> state6();
            case 7 -> state7();
            case 8 -> state8();
            case 9 -> state9();
            case 10 -> state10();
            case 11 -> state11();
            case 12 -> state12();
            case 13 -> state13();
            case 14 -> state14();
            case 15 -> state15();
        }
    }

    void shiftNoStack(int state){
        seen.push(current);
        current = input.remove(0);
        //System.out.println(current);
        stateNoStack(state);
    }

    void stateNoStack(int state){
        //System.out.println("Going to state " + states.peek());
        switch (state) {
            case 0 -> state0();
            case 1 -> state1();
            case 2 -> state2();
            case 3 -> state3();
            case 4 -> state4();
            case 5 -> state5();
            case 6 -> state6();
            case 7 -> state7();
            case 8 -> state8();
            case 9 -> state9();
            case 10 -> state10();
            case 11 -> state11();
            case 12 -> state12();
            case 13 -> state13();
            case 14 -> state14();
            case 15 -> state15();
        }
    }

    void backtrack(int tokens){
        for(int i = 0; i<tokens; i++){
            states.pop();
            //System.out.println("Popped state " + );
        }
        state(states.pop());
    }

    void parse(){
        state0();
    }

    void state0(){
        if(current instanceof Number){
            shift(7);
        } else if(current instanceof Cosine && !((Cosine) current).initialised){
            shift(5);
        } else if(current instanceof Add){
            state(1);
        } else if(current instanceof Subtract){
            state(2);
        } else if(current instanceof Multiply){
            state(3);
        } else if(current instanceof Cosine){
            state(4);
        } else if(current instanceof Factorial){
            state(6);
        }
    }

    void state1(){
        if(current instanceof Add && !((Add) current).initialised){
            shift(10);
        } else if(current instanceof EndToken){
            output = seen.pop();
            System.out.println("Done");
            //System.out.println(output);
        } else if(((Operator) current).initialised){
            shiftNoStack(1);

        }
    }

    void state2(){
        if(current instanceof Subtract && !((Subtract) current).initialised){
            shift(12);
        } else if(((Operator) current).initialised){
            shiftNoStack(2);
        } else{
            Add temp = new Add();
            temp.initialiseSingleton(seen.pop());
            input.add(0,current);
            current = temp;
            backtrack(1);
        }
    }

    void state3(){
         if(((Operator) current).initialised){
             shiftNoStack(3);
        } else {
             input.add(0, current);
             Subtract temp = new Subtract();
             temp.initialiseSingleton(seen.pop());
             current = temp;
             backtrack(1);
         }
    }

    void state4(){
        if(current instanceof Multiply && !((Multiply) current).initialised){
            shift(14);
        } else if(((Operator) current).initialised){
            shiftNoStack(4);
        } else{
            input.add(0,current);
            Multiply temp = new Multiply();
            temp.initialiseSingleton(seen.pop());
            current = temp;
            backtrack(1);
        }
    }

    void state5(){
        if(current instanceof Number){
            shift(7);
        } else if(current instanceof Cosine && !((Cosine) current).initialised){
            shift(5);
        } else if(current instanceof Cosine){
            state(9);
        }
        else if(current instanceof Factorial){
            state(6);
        }
    }

    void state6(){
         if(((Operator) current).initialised){
             shiftNoStack(6);
         } else if(current instanceof Factorial && !((Factorial) current).initialised) {
             shift(8);
         } else{
             input.add(0, current);
             Cosine temp = new Cosine();
             temp.initialiseSingleton(seen.pop());
             current = temp;
             backtrack(1);
        }
    }

    void state7(){
        input.add(0,current);
        Factorial temp = new Factorial();
        temp.initialiseSingleton(seen.pop());
        current = temp;
        backtrack(1);
    }

    void state8(){
        input.add(0,current);
        Factorial temp = new Factorial();
        seen.pop();
        temp.initialiseOperator(seen.pop());
        current = temp;
        backtrack(2);
    }

    void state9(){
        if(((Operator) current).initialised){
            shiftNoStack(9);
        } else {
            input.add(0, current);
            Cosine temp = new Cosine();
            temp.initialiseOperator(seen.pop());
            seen.pop();
            current = temp;
            backtrack(2);
        }
    }

    void state10(){
        if(current instanceof Number){
            shift(7);
        } else if(current instanceof Cosine && !((Cosine) current).initialised){
            shift(5);
        } else if(current instanceof Subtract){
            state(11);
        } else if(current instanceof Multiply){
            state(3);
        } else if(current instanceof Cosine){
            state(4);
        } else if(current instanceof Factorial){
            state(6);
        }
    }

    void state11(){
        if(current instanceof Subtract && !((Subtract) current).initialised){
            shift(12);
        } else if(((Operator) current).initialised){
            shiftNoStack(11);
        } else{
            Add temp = new Add();
            Token op2 = seen.pop();
            seen.pop();
            Token op1 = seen.pop();
            temp.initialiseOperator(op1,op2);
            input.add(0,current);
            current = temp;
            backtrack(3);
        }

    }

    void state12(){
        if(current instanceof Number){
            shift(7);
        } else if(current instanceof Cosine && !((Cosine) current).initialised){
            shift(5);
        } else if(current instanceof Multiply){
            state(13);
        } else if(current instanceof Cosine){
            state(4);
        } else if(current instanceof Factorial){
            state(6);
        }
    }

    void state13(){
         if(((Operator) current).initialised){
             shiftNoStack(13);
        } else {
             Subtract temp = new Subtract();
             Token op2 = seen.pop();
             seen.pop();
             Token op1 = seen.pop();
             temp.initialiseOperator(op1, op2);
             input.add(0, current);
             current = temp;
             backtrack(3);
         }
    }

    void state14(){
        //System.out.println(current.getClass());
        if(current instanceof Number){
            shift(7);
        } else if(current instanceof Cosine && !((Cosine) current).initialised){
            shift(5);
        } else if(current instanceof Cosine){
            state(4);
        } else if(current instanceof Factorial){
            state(6);
        } else if(current instanceof Multiply){
            state(15);
        }
    }

    void state15(){
        if(((Operator) current).initialised){
            shiftNoStack(15);
        } else {
            Multiply temp = new Multiply();
            Token op2 = seen.pop();
            seen.pop();
            Token op1 = seen.pop();
            temp.initialiseOperator(op1, op2);
            input.add(0, current);
            current = temp;
            backtrack(3);
        }
    }


}
