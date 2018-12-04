package com.molport.impo.parsers.t1;

import com.molport.impo.parsers.DOutput;
import com.molport.impo.parsers.Line;
import com.molport.impo.parsers.Preprocess;
import com.molport.impo.parsers.Rec;
import com.molport.impo.parsers.v1.Parser_T1;

import java.io.IOException;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author raitis
 */
public class Parser_T1IT {
    

    /**
     * Test of parse method, of class Parser_T1.
     */
    @Test
    public void testParse() throws IOException {
       List<Line> lines = new Preprocess().fileLines("src/test/data/input_02.sdf");
        DOutput.outFile("src/test/data/output.txt", lines);
        Parser_T1 parser = new Parser_T1();
        List<Rec> records = parser.parse("TestFile.txt", lines);
        DOutput.outFileRec("src/test/data/recs.txt", records);
        
    }
    
}
