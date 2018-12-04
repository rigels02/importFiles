package com.molport.impo.parsers;

/**
 *
 * @author raitis
 */
public class Line {
    
    private long num;
    private String line;

    public Line(long num, String line) {
        this.num = num;
        this.line = line;
    }

    public long getNum() {
        return num;
    }

    public String getLine() {
        return line;
    }

    @Override
    public String toString() {
        return "[" + num + "]\t\t" + line;
    }

    
    
    
}
