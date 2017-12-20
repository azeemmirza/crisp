package LexicalDep;

public class Token {
    String lexeme;
    String type;
    int lineNo;

    public Token(Token obj) {
        this.lexeme = obj.lexeme;
        this.type = obj.type;
        this.lineNo = obj.lineNo;
    }

    public Token(String lexeme, String type, int lineNo) {
        this.lexeme = lexeme;
        this.type = type;
        this.lineNo = lineNo;
    }
}
