public class Cosine extends Operator{

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
            return Math.cos(operand.evaluate());
        }
    }

    @Override
    public String toString(){
        if(initialised) {
            if(singleton){
                return "Cosine(" + operand.toString() + ")";
            } else {
                return "Cosine(cos(" + operand.toString() + "))";
            }
        } else{
            return "cos";
        }
    }
}
