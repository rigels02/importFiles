package com.molport.impo.parsers;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author raitis
 */
public class Parser_v1IT {

    
    /**
     * Test of concreteParse method, of class Parser_v1.
     */
    @Test
    public void testConcreteParse() {
        Parser_v1 parser = new Parser_v1("src/test/data/input_02.sdf",new Preprocess());
        try {
            parser.parse();
        } catch (IOException ex) {
            Logger.getLogger(Parser_v1IT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //List<Rec> records = parser.parse("TestFile.txt", lines);
        //DOutput.outFileRec("src/test/data/recs.txt", records);
        
    }
    
}
