package com.molport.impo.parsers.v1;

import com.molport.impo.parsers.IParser_v1T;
import com.molport.impo.parsers.Line;
import com.molport.impo.parsers.Rec;
import com.molport.impo.parsers.Utils;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parser T1 for file_1
 *
 * @author raitis
 */
public class Parser_T1 implements IParser_v1T {

    private static final Logger logger = LoggerFactory.getLogger(Parser_T1.class);

    private static final String CAT_FIELD= "<Catalog Number>";
    private static final String CAS_FIELD= "<cas>";
    private static final String PRICE_FIELD= "<Price";
    private static final String END= "$$$$";
    
     List<Rec> records = new LinkedList<>();
    private Rec rec;
    private boolean newRecord = true;
    private  int i = 0;
    
    

    @Override
    public List<Rec> parse(String fileName, List<Line> lines) {
        
        //Rec rec;
        while (i < lines.size()) {
            if (newRecord) {
                rec = new Rec();
                rec.setFileName(fileName);
                rec.setLineNum(lines.get(i).getNum());
                newRecord = false;
            }
            if_Cat_Num(lines);
            if_Cas_Num(lines);
            if_Price(lines);
            if_end(lines);
            i= i+2; //move cursor on 2 lines
            
        }

        return records;
    }

    private void if_Cat_Num(List<Line> lines) {
        String tline = lines.get(i).getLine();
        if(tline.contains(CAT_FIELD)){
           
            rec.setCatNum(lines.get(i+1).getLine());
        }
    }

    private void if_Cas_Num(List<Line> lines) {
        String tline = lines.get(i).getLine();
        if(tline.contains(CAS_FIELD)){
            String cas = lines.get(i+1).getLine();
            if(Utils.casOk(cas)){
                rec.setCasNum(cas);
            }else{
             logger.error("\"Invalid cas number \"{}\"",cas);
             rec.getErrors().add("Invalid cas number \""+cas+"\"");
             }
        }
    }

    private void if_Price(List<Line> lines) {
        String tline = lines.get(i).getLine();
        if(tline.contains(PRICE_FIELD)){
            String priceG_token = lines.get(i).getLine();
            int id0 = priceG_token.indexOf('e');
            int id1 = priceG_token.lastIndexOf('>');
            String price1G = priceG_token.substring(id0+1, id1);
            String digitPart = price1G.replaceAll("[^0-9]", "").trim();
            String unit = price1G.substring(price1G.indexOf(digitPart)+digitPart.length(),price1G.length());
            rec.getPackUnitList().add(Float.valueOf(digitPart));
           
            rec.getQtyMeasureList().add(unit);
            //-price-//
            String price = lines.get(i+1).getLine();
            float v= Float.valueOf(price);
            rec.getPriceList().add(v);
            rec.getCurrList().add("USD");
            
        }
    }

    private void if_end(List<Line> lines) {
     String tline = lines.get(i).getLine();
        if(tline.contains(END) ){
            i--;
            newRecord = true;
            records.add(rec);
        }
    }

   
    

}
