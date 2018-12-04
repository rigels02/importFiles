package com.molport.impo.parsers;


import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author raitis
 */
public class PreprocessIT {
    

    /**
     * Test of fileLines method, of class Preprocess.
     */
    @Test
    public void testFileLines() throws Exception {
        List<Line> lines = new Preprocess().fileLines("src/test/data/input_01.sdf");
        lines.forEach(el -> {System.out.println(el);});
        lines = new Preprocess().fileLines("src/test/data/input_02.sdf");
        DOutput.outFile("src/test/data/output.txt", lines);
    }
    
}
