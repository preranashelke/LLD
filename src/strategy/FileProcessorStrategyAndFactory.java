package strategy;

import java.util.HashMap;
import java.util.Map;

/***
 * Based on file type, the strategy is selected at runtime.
 * This avoids switch-case on file extensions and makes adding new formats easy
 */
interface FileProcessor {
    String getType();
    void processFiles(String filePath);
}

class CsvFileProcessor implements FileProcessor {
    @Override
    public String getType() {
        return "CSV";
    }

    @Override
    public void processFiles(String filePath){
        System.out.println("processing csv files");
    }
}

class XmlFileProcessor implements FileProcessor {
    @Override
    public String getType() {
        return "XML";
    }

    @Override
    public void processFiles(String filePath){
        System.out.println("processing xml files");
    }
}

class JsonFileProcessor implements FileProcessor {
    @Override
    public String getType() {
        return "JSON";
    }

    @Override
    public void processFiles(String filePath){
        System.out.println("processing json files");
    }
}

class FileStrategyService {
    private FileProcessor fileProcessor;

    public FileStrategyService(FileProcessor fileProcessor){
        this.fileProcessor = fileProcessor;
    }

    public void processFiles(String filePath){
        fileProcessor.processFiles(filePath);
    }
}

class FileProcessFactory{
    private static final Map<String, FileProcessor> mp = new HashMap<>();

    static {
        mp.put("CSV", new CsvFileProcessor());
        mp.put("XML", new XmlFileProcessor());
        mp.put("JSON", new JsonFileProcessor());
    }

    public  static FileProcessor getProcessor(String type){
        return mp.get(type);
    }

}

public class FileProcessorStrategyAndFactory {
    public static void main(String[] args){

        //factory
        FileProcessor fileProcessor = FileProcessFactory.getProcessor("JSON");

        //strategy code
        FileStrategyService fileService = new FileStrategyService(fileProcessor);
        fileService.processFiles("json");

    }
}
