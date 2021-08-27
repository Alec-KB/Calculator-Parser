public class Main {

    public static void main(String[] args) throws Exception {
        Lexer l = new Lexer("1+-3!*3");
        Parser p = new Parser(l.analyse());
        p.parse();
        Token output = p.output;
        System.out.println(output);}
}
