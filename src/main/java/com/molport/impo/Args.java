package com.molport.impo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author raitis
 */
public class Args {
    
    private final String[] args;

    public Args(String[] args) {
        this.args = args;
    }
    
    public String[] getInputFiles(){
        if(args.length == 0){
            System.out.println("Usage:\n\n"+
                    "inportFile <sdf files directory> [otput file]\n");
        return null;
        }            
       return getFileNames(args[0]);             
    }

    public PrintStream getOutputStream() throws FileNotFoundException{
        if(args.length == 0 || args.length == 1){
          return System.out;
        }
        FileOutputStream fos = new FileOutputStream(args[1]);
       
        PrintStream p_stream = new PrintStream(fos);
        return p_stream;
    }
    
    private String[] getFileNames(String dirName) {
        if(dirName == null || dirName.isEmpty() 
                || !Files.isDirectory(Paths.get(dirName)))
            return null;
        return new File(dirName).list();
    }
}
