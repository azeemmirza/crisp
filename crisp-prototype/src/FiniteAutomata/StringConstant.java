package FiniteAutomata;

public class StringConstant extends Constants {

    public StringConstant(String input) {
        char c[] = input.toCharArray();
        int i = 0;
        Constants.STATE currentState = states.START;
        while (i < c.length) {
            switch (currentState) {
                case START:
                    currentState = this.start(c[i]);
                    System.out.println(c[i] + ": " + currentState);
                    break;
                case A:
                    currentState = this.a(c[i]);
                    System.out.println(c[i] + ": " + currentState);
                    break;
                case B:
                    currentState = this.b(c[i]);
                    System.out.println(c[i] + ": " + currentState);
                    break;
                case C:
                    currentState = this.c(c[i]);
                    break;
                case DEAD:
                    currentState = this.dead(c[i]);
                    System.out.println(c[i] + ": " + currentState);
                    break;
                case ACCEPT:
                    currentState = this.accept(c[i]);
                    System.out.println(c[i] + ": " + currentState);
                    break;
            }
            this.result = currentState;
            i++;
        }
    }

    private STATE start(char c) {
        STATE output;
        if (isDoubleQuote(c)){
            output = this.states.A;
        } else output = this.states.DEAD;
        return output;
    }

    private STATE a(char c) {
        STATE output;
        if (isCharDigitsSS(c)) {
            output = this.states.B;
        } else if(isDoubleQuote(c)){
            output = this.states.ACCEPT;
        }else if (isBS(c)) {
            output = this.states.C;
        } else output = this.states.DEAD;
        return output;
    }

    private STATE b(char c) {
        STATE output;
        if (isDoubleQuote(c)) {
            output = this.states.ACCEPT;
        } else if (isBS(c)) {
            output = this.states.C;
        }else output = this.states.B;
        return output;
    }

    private STATE c(char c) {
        STATE output;
        if (isEscapseSequence(c) || isStringSpecialEscapeSequence(c)) {
            output = this.states.B;
        } else if (isCharDigitsSS(c)) {
            output = this.states.DEAD;
        } else output = this.states.C;
        return output;
    }

    private STATE accept(char c) {
        STATE output;
        if (isDoubleQuote(c) || isCharDigitsSS(c) || isBS(c) || isEscapseSequence(c)) {
            output = this.states.DEAD;
        } else output = this.states.DEAD;
        return output;
    }

    private STATE dead(char c) {
        STATE output;
        if (isDoubleQuote(c) || isBS(c) || isCharDigitsSS(c) || isEscapseSequence(c)) {
            output = this.states.DEAD;
        } else output = this.states.DEAD;
        return output;
    }

}

