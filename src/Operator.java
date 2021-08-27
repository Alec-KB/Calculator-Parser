public abstract class Operator extends Token{

    boolean initialised = false;
    boolean singleton = false;

    abstract double evaluate();
}
