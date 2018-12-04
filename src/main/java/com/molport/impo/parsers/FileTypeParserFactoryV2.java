package com.molport.impo.parsers;

import com.molport.impo.parsers.v2.Parser_v2T1;
import com.molport.impo.parsers.v2.Parser_v2T2;
import com.molport.impo.parsers.v2.Parser_v2T3;
import com.molport.impo.parsers.v2.Parser_v2T4;
import com.molport.impo.parsers.v2.Parser_v2T5;

/**
 *
 * @author raitis
 */
public class FileTypeParserFactoryV2 {
    
    private FileTypeParserFactoryV2(){
    }

    public static IParser_v2T get(FileTypes type) {
        switch (type){
            case Type_1:
                return  new Parser_v2T1();
                
            case Type_2:
            	return  new Parser_v2T2();
                
            case Type_3:
            	return  new Parser_v2T3();
                
            case Type_4:
            	return  new Parser_v2T4();
                
            case Type_5:
            	return  new Parser_v2T5();
		default:
			break;
                
           // default:
           //     throw new IllegalArgumentException("Unknown File Type!");
                
        }
        return null;
        
    }
 
}
