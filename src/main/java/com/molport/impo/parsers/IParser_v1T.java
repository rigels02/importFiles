package com.molport.impo.parsers;

import java.util.List;

/**
 *
 * @author raitis
 */
public interface IParser_v1T {

    List<Rec> parse(String fileName, List<Line> lines);
    
}
