public class Factorial extends Operator{

    Token operand;


    void initialiseSingleton(Token Operand){
        this.operand = Operand;
        initialised = true;
        singleton = true;
    }

    void initialiseOperator(Token operand){
        this.operand = operand;
        initialised = true;
    }

    @Override
    double evaluate() {
        if(singleton){
            return operand.evaluate();
        } else {
            return factorial(Math.floor(operand.evaluate()));
        }
    }

    static double factorial(double a){
        if(a<=0){
            return 1;
        }
        return a*factorial(a-1);
    }

    @Override
    public String toString(){
        if(initialised){
            if(singleton){
                return "" + operand.toString() + "";
            } else {
                return "(" + operand.toString() + "!)";
            }
        } else{
            return "!";
        }
    }

}
