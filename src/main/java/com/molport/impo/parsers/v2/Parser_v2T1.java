package com.molport.impo.parsers.v2;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.molport.impo.parsers.IParser_v2T;
import com.molport.impo.parsers.PropRec;
import com.molport.impo.parsers.Rec;
import com.molport.impo.parsers.Utils;

/**
 * Parser T1 for file_1
 *
 * @author raitis
 */
public class Parser_v2T1 implements IParser_v2T {

    private static final Logger logger = LoggerFactory.getLogger(Parser_v2T1.class);

    private static final String CAT_FIELD= "Catalog Number";
    private static final String CAS_FIELD= "cas";
    private static final String PRICE_FIELD= "Price";
   
    
     List<Rec> records = new LinkedList<>();
    private Rec rec;
   
    

    @Override
    public List<Rec> parse(String fileName, List<PropRec> lines) {
        
        for(int i=0; i< lines.size(); i++) {
        	rec = new Rec();
        	rec.setFileName(fileName);
        	getCatalog_Val(lines.get(i));
        	getCasNum_Val(lines.get(i));
        	getPrices(lines.get(i));
        	records.add(rec);
        }

        return records;
    }

    private void getMeasureUnitAndAmount(String priceG_token) {
    	
        int id0 = priceG_token.indexOf('e');
        
        String price1G = priceG_token.substring(id0+1, priceG_token.length());
        String digitPart = price1G.replaceAll("[^0-9]", "").trim();
        String unit = price1G.substring(price1G.indexOf(digitPart)+digitPart.length(),price1G.length());
        rec.getPackUnitList().add(Float.valueOf(digitPart));
       
        rec.getQtyMeasureList().add(unit);
       	
    }
    
    private void getPrices(PropRec propRec) {
    	List<Integer> idx = new ArrayList<>();
    	
		for (int i=0; i< propRec.getPropLst().size(); i++) {
			String key = propRec.getPropLst().get(i);
			if(key.contains(PRICE_FIELD)) {
				idx.add(i);
				getMeasureUnitAndAmount(key);
			}
		}
		for (Integer integer : idx) {
			rec.getPriceList().add(Float.valueOf(propRec.getValLst().get(integer)));
			rec.getCurrList().add("USD");
		}
		
		
	}

	private void getCasNum_Val(PropRec propRec) {
		int idx = propRec.getPropLst().indexOf(CAS_FIELD);
		String cas = propRec.getValLst().get(idx);
		if(Utils.casOk(cas)){
			
			rec.setCasNum(cas);
		}else {
			logger.error("\"Invalid cas number \"{}\"",cas);
            rec.getErrors().add("Invalid cas number \""+cas+"\"");
			
		}
		
	}

	private void getCatalog_Val(PropRec propRec) {
		int idx = propRec.getPropLst().indexOf(CAT_FIELD);
		rec.setCatNum(propRec.getValLst().get(idx));
		
	}



}
