public class Number extends Token{

    double value;

    Number(double value){
        this.value = value;
    }

    double evaluate(){
        return value;
    }

    @Override
    public String toString(){
        return String.valueOf(value);

    }
}
