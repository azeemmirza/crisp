package Lexical;

public class Token {
    String lexeme;
    String type;
    int lineNo;

    public Token(Token obj) {

    }

    public Token(String lexeme, String type, int lineNo) {
        this.lexeme = lexeme;
        this.type = type;
        this.lineNo = lineNo;
    }
}
