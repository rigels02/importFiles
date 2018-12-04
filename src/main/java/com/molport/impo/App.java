package com.molport.impo;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import chemaxon.formats.MolImporter


/**
 *
 * @author raitis
 */
public class App {

    private static Logger logger = LoggerFactory.getLogger(App.class);
    
    public static void main(String[] args) {
        
        System.out.println("args= "+args.length);
        
        
        PropertyConfigurator.configure("log4j.properties");

        logger.info("Hello from logger {}....{}","Raitis",23);
        
    }
    
}
