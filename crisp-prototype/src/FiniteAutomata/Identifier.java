package FiniteAutomata;

public class Identifier extends Constants{

    public Identifier(String input) {
        char c[] = input.toCharArray();
        int i = 0;
        STATE currentState = states.START;
        while (i < c.length) {
            switch (currentState) {
                case START:
                    currentState = this.start(c[i]);
                    System.out.println(c[i]+": "+currentState);
                    break;
                case DEAD:
                    currentState = this.dead(c[i]);
                    System.out.println(c[i]+": "+currentState);
                    break;
                case ACCEPT:

                    currentState = this.accept(c[i]);
                    System.out.println(c[i]+": "+currentState);
                    break;
            }
            this.result = currentState;
            i++;
        }
    }
        //STATES
        private STATE start(char c){
            STATE output;

            if(isChar(c) || isUnderscore(c)){
                System.out.println("check");
                output = states.ACCEPT;
            }else if(isDigit(c)) output = states.DEAD;
            else output = states.START;
            return output;
        }
        private STATE accept(char c){
            STATE output;
            if(isUnderscore(c) || isChar(c) || isDigit(c)){
                output =    states.ACCEPT;
            }else output = states.DEAD;
            return output;
        }
        private STATE dead(char c){
            STATE output ;
            if(isUnderscore(c) || isChar(c) || isDigit(c)){
                output =    states.DEAD;
            }else output = states.DEAD;
            return output;
        }

    }
