public class Tests {

    void runTests() throws Exception {
        SubBeforeAdd();
        MultBeforeAdd();
        CosineBeforeAdd();
        FactBeforeAdd();
        MultBeforeSub();
        CosineBeforeSub();
        FactBeforeSub();
        CosineBeforeMult();
        FactBeforeMult();
        FactBeforeCosine();
    }

    void SubBeforeAdd() throws Exception {
        Lexer l = new Lexer("1+2-3");
        Parser p = new Parser(l.analyse());
        p.parse();
        if (p.output.evaluate() != 0) {
            System.out.println("SubBeforeAdd Failed");
        }
    }
    void MultBeforeAdd() throws Exception {
        Lexer l = new Lexer("1+2*3");
        Parser p = new Parser(l.analyse());
        p.parse();
        if (p.output.evaluate() != 7) {
            System.out.println("MultBeforeAdd Failed");
        }
    }
    void CosineBeforeAdd() throws Exception {
        Lexer l = new Lexer("1+cos3.14");
        Parser p = new Parser(l.analyse());
        p.parse();
        if (p.output.evaluate()>0.01 && p.output.evaluate() < -0.01) {
            System.out.println("CosineBeforeAdd Failed");
        }
    }
    void FactBeforeAdd() throws Exception {
        Lexer l = new Lexer("1+4!");
        Parser p = new Parser(l.analyse());
        p.parse();
        if (p.output.evaluate() != 25) {
            System.out.println("FactBeforeAdd Failed");
        }
    }

    void MultBeforeSub() throws Exception {
        Lexer l = new Lexer("7-2*3");
        Parser p = new Parser(l.analyse());
        p.parse();
        if (p.output.evaluate() != 1) {
            System.out.println("MultBeforeSub Failed");
        }
    }
    void CosineBeforeSub() throws Exception {
        Lexer l = new Lexer("2-cos3.14");
        Parser p = new Parser(l.analyse());
        p.parse();
        if (p.output.evaluate()>1.01 && p.output.evaluate() < -1.01) {
            System.out.println("CosineBeforeSub Failed");
        }
    }
    void FactBeforeSub() throws Exception {
        Lexer l = new Lexer("30-4!");
        Parser p = new Parser(l.analyse());
        p.parse();
        if (p.output.evaluate() != 6) {
            System.out.println("FactBeforeSub Failed");
        }
    }

    void CosineBeforeMult() throws Exception {
        Lexer l = new Lexer("3*cos3.14");
        Parser p = new Parser(l.analyse());
        p.parse();
        if (p.output.evaluate()>0.03 && p.output.evaluate() < -0.03) {
            System.out.println("CosineBeforeMult Failed");
        }
    }
    void FactBeforeMult() throws Exception {
        Lexer l = new Lexer("2*4!");
        Parser p = new Parser(l.analyse());
        p.parse();
        if (p.output.evaluate() != 48) {
            System.out.println("FactBeforeMult Failed");
        }
    }

    void FactBeforeCosine() throws Exception {
        Lexer l = new Lexer("cos3!");
        Parser p = new Parser(l.analyse());
        p.parse();
        if (p.output.evaluate()<0.96 && p.output.evaluate()>0.97) {
            System.out.println(p.output.evaluate());
            System.out.println("FactBeforeCosine Failed");
        }
    }





}
