
import FiniteAutomata.*;
import Lexical.*;

import javax.swing.plaf.synth.SynthTextAreaUI;

public class Crisp {

    public static void main (String args []){



        SourceReader reader = new SourceReader("file.txt");
        SourceReader.Source getResult = reader.new Source();
        getResult = reader.get();


        if(getResult.status){

            //System.out.println(getResult.fileContent);


        }else System.out.print(getResult.code);

       // Identifier DFAobj = new Identifier(getResult.fileContent);

        Lexical Obj2 = new Lexical(getResult.fileContent);
        Obj2.Ana("file.txt");

        Obj2.tokenListIterator();

    }
}
