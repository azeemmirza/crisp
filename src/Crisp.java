
import FiniteAutomata.*;
import Lexical.Lexical;

public class Crisp {

    public static void main (String args []){

        SourceReader reader = new SourceReader("file.txt");
        SourceReader.Source getResult = reader.new Source();
        getResult = reader.get();


        if(getResult.status){

            //System.out.println(getResult.fileContent);


        }else System.out.print(getResult.code);

        Lexical Obj2 = new Lexical(getResult.fileContent);
        Obj2.Ana("file.txt");
    }
}
