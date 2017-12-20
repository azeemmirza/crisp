import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.io.*;
public class FileHandler {

    FileHandler(String fileName){
        StringBuilder codeBuilder = new StringBuilder();
        try{
            FileReader fileReader = new FileReader(fileName);
            if(validateExtension(fileName)){
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line = "";
                while((line = bufferedReader.readLine()) != null){
                    System.out.println(line);
                    line+="\\x000";
                    codeBuilder.append(line);
                }
                System.out.println(codeBuilder.toString());
            }

        }catch(FileNotFoundException e) {
            System.out.println("File Not Found");
        }catch (IOException e){
            System.out.println("Buffer Exception");
        }
    }

    private boolean validateExtension(String fileName){
        final String EXTENSION = "crsp";
        if(EXTENSION.matches(this.getExtension(fileName))){
            return true;}
        else return false;
    }

    private String getExtension(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

        if (i > p) {
            extension = fileName.substring(i+1);
        }
       /* char extArray [] = extension.toCharArray();
        System.out.println(Arrays.toString(extArray));*/
        return extension;
    }

}
