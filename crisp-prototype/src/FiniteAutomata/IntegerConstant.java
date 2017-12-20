package FiniteAutomata;

public class IntegerConstant extends Constants{

    public IntegerConstant(String input){
        char c [] = input.toCharArray();
        int i = 0;
        STATE currentState = states.START;
        while(i<c.length){
            switch (currentState){
                case START:
                    currentState = this.start(c[i]);
                    System.out.println(c[i]+": "+currentState);
                    break;
                case A:
                    currentState = this.a(c[i]);
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
        if(isSign(c)){
            output = this.states.A;
        }else if(isDigit(c)){
            output = this.states.ACCEPT;
        }else output = this.states.START;
        return output;
    }
    private STATE a(char c){
        STATE output;
        if(isDigit(c)){
            output = this.states.ACCEPT;
        }else if(isSign(c)){
            output = this.states.DEAD;
        }else output = this.states.A;
        return output;
    }
    private STATE accept(char c){
        STATE output;
        if(isDigit(c)){
            output = this.states.ACCEPT;
        }else if (isSign(c)){
            output = this.states.DEAD;
        }else output = this.states.DEAD;
        return output;
    }
    private STATE dead(char c){
        STATE output = this.states.DEAD;
        if(isDigit(c)){
            output = this.states.DEAD;
        }else if(isSign(c)){
            output = this.states.DEAD;
        }
        return output;
    }



    //Get result
}
