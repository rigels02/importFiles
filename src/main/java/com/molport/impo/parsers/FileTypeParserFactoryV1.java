package com.molport.impo.parsers;

import com.molport.impo.parsers.v1.Parser_T1;
/**
 *
 * @author raitis
 */
public class FileTypeParserFactoryV1 {
    
    private FileTypeParserFactoryV1(){
    }

    public static IParser_v1T get(FileTypes type) {
        switch (type){
            case Type_1:
                return  new Parser_T1();
                
            case Type_2:
                
                break;
            case Type_3:
                break;
            case Type_4:
                break;
            case Type_5:
                break;
            default:
                throw new IllegalArgumentException("Unknown File Type!");
                
        }
        return null;
    }
 
}
