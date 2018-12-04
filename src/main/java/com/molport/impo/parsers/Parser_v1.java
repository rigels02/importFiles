package com.molport.impo.parsers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

/**
 * Main Parser class what calls other parsers depending on filetype.
 * 
 * @author raitis
 */
public class Parser_v1 extends AbsParser {

    private static final Logger logger = LoggerFactory.getLogger(AbsParser.class);
    private static final Map<String, FileTypes> CATALOG_FIELDS;

    static {
        CATALOG_FIELDS = new HashMap<>();
        CATALOG_FIELDS.put("Catalog Number", FileTypes.Type_1);
        CATALOG_FIELDS.put("id", FileTypes.Type_2);
        CATALOG_FIELDS.put("Catalog#", FileTypes.Type_3);
        CATALOG_FIELDS.put("Catalogue_Number", FileTypes.Type_4);
        CATALOG_FIELDS.put("ID", FileTypes.Type_5);

    }

    /**
     * Input file parser constructor
     *
     * @param fileName input sdf file to parse
     * @param prProc preprocessor to use
     */
    public Parser_v1(String fileName, IPreprocess prProc) {
        super(fileName, prProc);

    }

    
    /**
     * Parsing entry point 
     */
    @Override
    protected void concreteParse() {
        List<Line> plines = getLines();
        //Parser lines
        //With 1st line detect file type : enum FileTypes
        //use factory to get parser for filetype
        
        String token = getToken(plines.get(0).getLine());
       
        
        if (!CATALOG_FIELDS.containsKey(token)) {

            logger.error("Unknown File type. Line number: {},Line: {}",
                    plines.get(0).getNum(), plines.get(0).getLine());
            return;
        }
        
        /***
        try {
            //TODO
            DOutput.outFile("debug.txt", getLines());
        } catch (IOException ex) {
            //Logger.getLogger(Parser_v1.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("{}", ex.getMessage());
        }
        ***/
        
        FileTypes key = CATALOG_FIELDS.get(token);   
        
        IParser_v1T parser = FileTypeParserFactoryV1.get(key);
        List<Rec> records = parser.parse(getFileName(), plines);
        
        try {
            DOutput.outFileRec("recs.txt", records);
        } catch (IOException ex) {
                  logger.error("{}", ex.getMessage());
        }

    }

     private String getToken(String line) {
        int id1 = line.indexOf('<');
        int id2 = line.lastIndexOf('>');
        return line.substring(id1 + 1, id2);
    }
}
