package com.molport.impo.parsers.v2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.molport.impo.parsers.IParser_v2T;
import com.molport.impo.parsers.PropRec;
import com.molport.impo.parsers.Rec;
import com.molport.impo.parsers.Utils;

/**
 * Parser T3 for file_3
 *
 * @author raitis
 */
public class Parser_v2T3 implements IParser_v2T {

	private static final Logger logger = LoggerFactory.getLogger(Parser_v2T3.class);

	private static final String CAT_FIELD = "Catalog#";
	private static final String CAS_FIELD = "CAS";
	private static final String PRICE_FIELD = "Price_";
	private static final String SIZE_FIELD = "Size_";

	private List<Rec> records = new LinkedList<>();
	private Map<Integer, String> sizeFields = new HashMap<>();
	private Map<Integer, String> priceFields = new HashMap<>();

	private Rec rec;

	@Override
	public List<Rec> parse(String fileName, List<PropRec> lines) {

		for (int i = 0; i < lines.size(); i++) {
			rec = new Rec();
			rec.setFileName(fileName);
			getCatalog_Val(lines.get(i));
			getCasNum_Val(lines.get(i));
			mapPrices(lines.get(i));
			mapSizes(lines.get(i));
			getSizesAndPrices(lines.get(i));

			records.add(rec);
		}

		return records;
	}

	private void mapSizes(PropRec propRec) {
		for (int i = 0; i < propRec.getPropLst().size(); i++) {
			String key = propRec.getPropLst().get(i);
			if (key.contains(SIZE_FIELD)) {

				int idx = exctractIdx(key);
				sizeFields.put(idx, propRec.getValLst().get(i));

			}
		}

	}

	private int exctractIdx(String sizeToken) {
		String sidx = sizeToken.replaceAll("[^0-9]", "").trim();
		return Integer.valueOf(sidx);
	}

	private void mapPrices(PropRec propRec) {
		for (int i = 0; i < propRec.getPropLst().size(); i++) {
			String key = propRec.getPropLst().get(i);
			if (key.contains(PRICE_FIELD)) {

				int idx = exctractIdx(key);
				priceFields.put(idx, propRec.getValLst().get(i));

			}
		}

	}

	private void getSizesAndPrices(PropRec propRec) {
		for (Integer key_id : sizeFields.keySet()) {
			String unit_g = sizeFields.get(key_id);
			String digit_part = unit_g.replaceAll("[^0-9]", "").trim();
			String unit = unit_g.substring(unit_g.indexOf(digit_part) + digit_part.length(), unit_g.length());

			rec.getPackUnitList().add(Float.valueOf(digit_part));
			rec.getQtyMeasureList().add(unit);
			rec.getPriceList().add(Float.valueOf(priceFields.get(key_id)));
			rec.getCurrList().add("USD");
		}

	}

	private void getCasNum_Val(PropRec propRec) {
		int idx = propRec.getPropLst().indexOf(CAS_FIELD);
		String cas = propRec.getValLst().get(idx);
		String cas1 = cas.substring(1, cas.length());
		if (Utils.casOk(cas1)) {

			rec.setCasNum(cas1);
		} else {
			logger.error("\"Invalid cas number \"{}\"", cas);
			rec.getErrors().add("Invalid cas number \"" + cas + "\"");

		}

	}

	private void getCatalog_Val(PropRec propRec) {
		int idx = propRec.getPropLst().indexOf(CAT_FIELD);
		rec.setCatNum(propRec.getValLst().get(idx));

	}

}