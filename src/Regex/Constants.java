package Regex;

public class Constants {

    public boolean integer(String input) {

        char a[] = input.toCharArray();
        int i = 0;
        boolean output = false;
        while (i < a.length) {
            if (a[i] == '+' || a[i] == '-') {
                if (i != 0) {
                    output = false;
                    break;
                }
            } else {
                if (a[i] >= 48 && a[i] <= 57) {
                    output = true;
                } else {
                    output = false;
                    break;
                }
            }
            i++;
        }
        return output;
    }

    public boolean decimal(String input) {

        char a[] = input.toCharArray();
        int i = 0;
        boolean output = false, pointFlag = false;
        while (i < a.length) {
            if (a[i] == 46 && !pointFlag) {
                pointFlag = true;
            } else if(a[i] == 46 && pointFlag){
                output = false;
                break;
            }
            else if (a[i] >= 48 && a[i] <= 57) {
                output = true;
            } else {
                output = false;
                break;
            }
            i++;
        }

        return output;
    }

    public boolean character(String input) {

        boolean output = false;
        return output;
    }

    public boolean string(String input) {
        return false;
    }

    public boolean bool(String input) {
        char a[] = input.toCharArray();
        int i = 0;
        boolean output = false;

        while(i<a.length){

        }
        return output;
    }

    public boolean isInteger(String input){

        int initialState = 0, finalState = 1;

        return false;
    }
}
