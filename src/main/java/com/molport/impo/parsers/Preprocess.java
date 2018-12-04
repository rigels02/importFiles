package com.molport.impo.parsers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author raitis
 */
public class Preprocess implements IPreprocess {

    
    public Preprocess() {
    }
    
    @Override
    public  List<Line> fileLines(String fileName) throws FileNotFoundException, IOException{
        //InputStreamReader reader = new InputStreamReader (new FileInputStream(fileName));
        List<Line> inputLines = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        String line;
        long counter = 0;
        LineType type = LineType.Skip;
        while((line = reader.readLine()) != null){
            String tline = line.trim();
            counter++;
            if(tline.isEmpty())
                continue;
            if( type == LineType.Skip && tline.charAt(0) == '>'){
                inputLines.add(new Line(counter, line.trim()));
                type = LineType.Value;
                continue;
            }
            if(type == LineType.Value ){
                inputLines.add(new Line(counter, line.trim()));
                type = LineType.Skip;
                continue;
            }
            if(type == LineType.Skip && tline.substring(0, 4).equals("$$$$")){
              inputLines.add(new Line(counter, "$$$$"));
            }
        }
        return inputLines;
        }
        
    }
}
