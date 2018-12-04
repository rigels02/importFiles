package com.molport.impo.out;

import com.molport.impo.parsers.Rec;
import java.io.PrintStream;
import java.util.List;

/**
 *
 * @author raitis
 */
public class OutputFormater {

    private final String head = 
        "FILE_NAME\tCATALOG_NUMBER\tPACKAGING_UNIT\tQUANTITY_MEASURE\tPRICE\tCURRENCY\tPRICE_GROUP\tCAS_NUMBER\tERROR_TXT"; 
   
    public void outPrint(PrintStream ps, List<Rec> records){
        
        
        ps.println(head);
        for (Rec rec : records) {
            
            if( !(rec.getPriceGroup()==null || rec.getPriceGroup().isEmpty())){
                String oline = lineForPriceGroup(rec);
                ps.println(oline);
                continue;
            }
            String oline = lineForPackInfo(rec);
            ps.println(oline);
        }
    }

    private String lineForPriceGroup(Rec rec) {
        StringBuilder sb = new StringBuilder();
        sb.append(rec.getFileName()).append("\t").append(rec.getCatNum())
          .append("\t\t\t\t").append(rec.getPriceGroup()).append(rec.getCasNum());
        buildErrorString(sb, rec);
        return sb.toString();
    }

    private String lineForPackInfo(Rec rec) {
        StringBuilder sb = new StringBuilder();
        sb.append(rec.getFileName()).append("\t").append(rec.getCatNum());
        for (int i =0; i< rec.getPackUnitList().size(); i++) {
            
            sb.append(rec.getPackUnitList().get(i))
              .append(rec.getQtyMeasureList().get(i))
              .append(rec.getPriceList().get(i))
              .append(rec.getCurrList().get(i))
              .append(rec.getCasNum());
            buildErrorString(sb, rec);
            sb.append("\n");
        }
        return sb.toString();
    }

    private void buildErrorString(StringBuilder sb, Rec rec){
        if(!rec.getErrors().isEmpty()){
            for (String error : rec.getErrors()) {
                sb.append(error).append(";");
            }
        }
    }
    
}
