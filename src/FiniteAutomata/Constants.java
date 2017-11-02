package FiniteAutomata;

public class Constants {
    public enum STATE{
        START,ACCEPT, DEAD, A,B,C
    }
    STATE states;
    STATE result;
    //ASCII Code check from character '0 to 9'
    public boolean isDigit(char c){
        if(c>=48 && c<=57){
            return true;
        }else return false;
    }
    //ASCII Code check for character '+' and '-' only
    protected boolean isSign(char c){
        if(c == 43 || c== 45){
            return true;
        }else return false;
    }
    //ASCII Code check for character '+' and '-' only
    protected boolean isChar(char c){
        if((c >= 65 && c<= 90) || (c >= 97 && c<= 122)){
            return true;
        }else return false;
    }

    //ASCII Code check for character '.'
    protected boolean isDecimal(char c){
        if(c == 46){
            return true;
        }else return false;
    }

    protected boolean isSingleQuote(char c){
        if(c == 39){
            return true;
        }else return false;
    }

    protected boolean isDoubleQuote(char c){
        if(c == 34){
            return true;
        }else return false;
    }

    protected boolean isBS(char c){
        if(c == 92){
            return true;
        }else return false;
    }

    protected boolean isSpecialChar(char c){
        if((c >= 33 || c <= 47) && !(isBS(c)) && !(isSingleQuote(c))){
            return true;
        }else  return false;
    }

    protected boolean isCharSpecialEscapeSequence(char c){
        if(isBS(c) || isSingleQuote(c)){
            return true;
        }else return false;
    }
    protected boolean isStringSpecialEscapeSequence(char c){
        if(isBS(c) || isDoubleQuote(c)){
            return true;
        }else return false;
    }
    protected boolean isEscapseSequence(char c){
        if(c == 110 || c == 114 || c == 116 ){
            return true;
        }else return false;
    }

    protected boolean isCharDigitsSS(char c){
        if(isDigit(c) || isChar(c) || isSpecialChar(c)){
            return true;
        }else return false;
    }
    protected boolean isUnderscore(char c){
        if(c == 95) return true;
        else return false;
    }

    public boolean get(){
        if(this.result == states.ACCEPT){
            return true;
        }else return false;
    }

}
