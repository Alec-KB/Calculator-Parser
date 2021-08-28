public class Main {

    public static void main(String[] args) throws Exception {
        Tests test = new Tests();
        test.runTests();

        //Lexer l = new Lexer(args[0]); Uncomment this line if you want to supply the input after compilation
        Lexer l = new Lexer("1+3*5!-cos3.14");
        Parser p = new Parser(l.analyse());
        p.parse();
        Token output = p.output;
        System.out.println(output.evaluate());
        System.out.println(output);
    }
}
