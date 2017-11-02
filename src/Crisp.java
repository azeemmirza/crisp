
import FiniteAutomata.*;
public class Crisp {

    public static void main (String args []){

        SourceReader reader = new SourceReader("file.txt");
        SourceReader.Source getResult = reader.new Source();
        getResult = reader.get();


        if(getResult.status){

            System.out.println(getResult.fileContent);
            StringConstant Obj = new StringConstant(getResult.fileContent);

        }else System.out.print(getResult.code);
    }
}
