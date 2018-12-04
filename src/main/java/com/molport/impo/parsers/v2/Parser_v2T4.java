package com.molport.impo.parsers.v2;


import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.molport.impo.parsers.IParser_v2T;
import com.molport.impo.parsers.PropRec;
import com.molport.impo.parsers.Rec;
import com.molport.impo.parsers.Utils;

/**
 * Parser T4 for file_4
 *
 * @author raitis
 */
public class Parser_v2T4 implements IParser_v2T {

    private static final Logger logger = LoggerFactory.getLogger(Parser_v2T4.class);

    private static final String CAT_FIELD= "Catalogue_Number";
    private static final String CAS_FIELD= "Cas";
    private static final String PRICE_FIELD= "Price";
    private static final String PACKING_FIELD= "Packing";
    private static final String PACKMEASURE_FIELD= "Packing_Measure";
    
   
    
     List<Rec> records = new LinkedList<>();
    private Rec rec;
   
    

    @Override
    public List<Rec> parse(String fileName, List<PropRec> lines) {
        
        for(int i=0; i< lines.size(); i++) {
        	rec = new Rec();
        	rec.setFileName(fileName);
        	getCatalog_Val(lines.get(i));
        	getCasNum_Val(lines.get(i));
        	getPrice(lines.get(i));
        	getPacking(lines.get(i));
        	getPackMeasure(lines.get(i));
        	
        	records.add(rec);
        }

        return records;
    }

    
    
    private void getPackMeasure(PropRec propRec) {
    	int idx = propRec.getPropLst().indexOf(PACKMEASURE_FIELD);
		String packMeasure = propRec.getValLst().get(idx);
		rec.getQtyMeasureList().add(packMeasure);
		
	}



	private void getPacking(PropRec propRec) {
		int idx = propRec.getPropLst().indexOf(PACKING_FIELD);
		String packVal = propRec.getValLst().get(idx);
		rec.getPackUnitList().add(Float.valueOf(packVal));
		
	}



	private void getPrice(PropRec propRec) {
		int idx = propRec.getPropLst().indexOf(PRICE_FIELD);
		String price = propRec.getValLst().get(idx);
		rec.getPriceList().add(Float.valueOf(price));
		rec.getCurrList().add("USD");
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
