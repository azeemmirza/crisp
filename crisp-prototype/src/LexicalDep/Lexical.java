package LexicalDep;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Lexical {
    String sourceCode;
    DataTypes dt;
    public ArrayList<Token> TokenList = new ArrayList<>();

    public Lexical(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    private void tokenizr(String sourceCodeLine, int lineNo) {
        int i = 0;
        String tester = "INT";
        String spiltter[] =
                sourceCodeLine.split(" ");

        while (i < spiltter.length) {

            //If starts commenting
            if (spiltter[i].contains("//")) {
                return;
            }



            try {

                dt = DataTypes.valueOf(spiltter[i]);
                TokenList.add(new Token(spiltter[i], "DATA-TYPE", lineNo));

            } catch (IllegalArgumentException e) {
                if (spiltter[i].equals("=")) {
                    TokenList.add(new Token(spiltter[i], "ASSIGNMENT OP", lineNo));
                } else if (spiltter[i].equals(";")) {
                    TokenList.add(new Token(spiltter[i], "TERMINATOR", lineNo));
                } else if (spiltter[i].equals("+") || spiltter[i].equals("-") || spiltter[i].equals("*") || spiltter[i].equals("/")) {
                    TokenList.add(new Token(spiltter[i], "ARTHIMETIC OP", lineNo));
                } else if(spiltter[i].startsWith("_") || isChar(spiltter[i].charAt(0))){
                    TokenList.add(new Token(spiltter[i], "IDENTIFIER", lineNo));
                }
            }
            i++;
        }

    }
    private boolean isChar(char c){
     if((c >= 65 && c<=90) || (c>=97 && c<=122)){
        return true;
     }else return false;
    }
    public void reader(String filePath) {
        int lineNo = 1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = "";

            while ((line = reader.readLine()) != null) {

                tokenizr(line, lineNo);
                lineNo++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Not found");

        } catch (IOException e) {
            System.out.println("IO Exception");
        }


    }

    public void tokenListIterator() {


        int sizeOfTokenList = TokenList.size(), i = 0;
        System.out.println("Number of the Tokens: "+ sizeOfTokenList);
        while (i<sizeOfTokenList){
            Token obj = this.TokenList.get(i);

            System.out.print(i+": [ " + obj.lexeme + " : "+ obj.type + "- lineNo: " + obj.lineNo+" ]");
            System.out.println();
            i++;
        }




        /*Token obj = TokenList.get(2);
        System.out.println(obj.lexeme);
        System.out.println(obj.lineNo);
        System.out.println(obj.type);*/
    }

}
