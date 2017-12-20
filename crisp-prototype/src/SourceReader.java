import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SourceReader {
    public class Source{
        public String fileContent;
        public String fileName;
        public String filePath;
        public boolean status;
        public String code;
    }

    Source source = new Source();
    private SourceReader(String file){
        StringBuilder builder = new StringBuilder();
        this.source.fileName = file;
        this.source.filePath = file;
        try{
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = " ";
            while((line = bufferedReader.readLine()) != null){
                builder.append(line);
            }
            this.source.fileContent = builder.toString();
            this.source.code = "x00";
            this.source.status = true;

        }catch(FileNotFoundException e){
            this.source.code = "x01";
            this.source.status = false;

        }catch (IOException e){
            this.source.code = "x02";
            this.source.status = false;
        }
    }

    public Source get(){
        return this.source;
    }
}
