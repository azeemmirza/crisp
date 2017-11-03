package Lexical;

public class Token {
    String lexeme;
    String type;
    int lineNo;
    Token NextToken;

    public Token(Token obj){

    }
    public Token(String lexeme, String type, int lineNo){
        this.lexeme = lexeme;
        this.type = type;
        this.lineNo = lineNo;
    }
    public Token get(){
        return this;
    }
}
