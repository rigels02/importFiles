package com.molport.impo.parsers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author raitis
 */
public interface IPreprocess {

    List<Line> fileLines(String fileName) throws FileNotFoundException, IOException;
    
}
