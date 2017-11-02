package Lexical;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Lexical {
    String sourceCode;
    public Lexical(String sourceCode){
        this.sourceCode = sourceCode;
    }

    private void tokenizr(String sourceCodeLine){
        int i = 0;
        while(i<sourceCodeLine.length()){
            if(sourceCodeLine.charAt(i) == 47){
                int j = i+1;
                if(sourceCodeLine.charAt(j) == 47){
                    break;
                }

            }else{
                System.out.print(sourceCodeLine.charAt(i));
            }
        }
        System.out.println();
    }

    public void Ana(String filePath){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line ="";

            while((line = reader.readLine()) != null){

                tokenizr(line);
            }
        }catch(FileNotFoundException e){

        }catch(IOException e){

        }


    }


}
