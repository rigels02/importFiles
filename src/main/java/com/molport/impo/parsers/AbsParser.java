package com.molport.impo.parsers;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author raitis
 */
public abstract class AbsParser {
    private final IPreprocess prProc;
    private final String fileName;
    private List<Line> lines;
    
    public AbsParser(String fileName, IPreprocess prProc) {
        this.prProc = prProc;
        this.fileName = fileName;
      
    }
    
    
    protected void preprocess() throws IOException{
      lines = prProc.fileLines(fileName);
    }
    protected abstract void concreteParse();
    
    public void parse() throws IOException{
        preprocess();
        concreteParse();
    }

    protected List<Line> getLines() {
        return lines;
    }

    public String getFileName() {
        return fileName;
    }
    
}
