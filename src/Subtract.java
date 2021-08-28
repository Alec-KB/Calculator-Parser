public class Subtract extends Operator{

    Token operand1;
    Token operand2;


    void initialiseSingleton(Token Operand){
        this.operand1 = Operand;
        initialised = true;
        singleton = true;
    }

    void initialiseOperator(Token operand1,Token operand2){
        this.operand1 = operand1;
        this.operand2 = operand2;
        initialised = true;
    }

    @Override
    double evaluate() {
        if(singleton){
            return operand1.evaluate();
        } else {
            return operand1.evaluate() - operand2.evaluate();
        }
    }


    @Override
    public String toString(){
        if(initialised) {
            if(singleton){
                return "" + operand1.toString() + "";
            } else {
                return "(" + operand1.toString() + "-" + operand2.toString() + ")";
            }
        }
        else{ return "-";}    }
}
