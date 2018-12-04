package com.molport.impo.parsers;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.molport.impo.out.OutputFormater;

import chemaxon.formats.MolFormatException;
import chemaxon.formats.MolImporter;
import chemaxon.struc.Molecule;

/**
 * Using molport Chemaxon jchem
 * 
 * @author raitis
 *
 */
public class Parser_v2 {

	private static final Logger logger = LoggerFactory.getLogger(AbsParser.class);

	private static final Map<String, FileTypes> CATALOG_FIELDS;

	static {
		CATALOG_FIELDS = new HashMap<>();
		CATALOG_FIELDS.put("Catalog Number", FileTypes.Type_1);
		CATALOG_FIELDS.put("id", FileTypes.Type_2);
		CATALOG_FIELDS.put("Catalog#", FileTypes.Type_3);
		CATALOG_FIELDS.put("Catalogue_Number", FileTypes.Type_4);
		CATALOG_FIELDS.put("ID", FileTypes.Type_5);

	}

	public List<Rec> doParse(String fileName) {

		List<PropRec> recs = null;
		try {
			recs = getPropeties(fileName);
		} catch (IOException e) {
			logger.error("Error process file: {}\n{}", fileName, e.getMessage());
			return null;
		}
		String token = recs.get(0).getPropLst().get(0);

		if (!CATALOG_FIELDS.containsKey(token)) {

			logger.error("Unknown File type. File: {}", fileName);
			return null;
		}

		FileTypes key = CATALOG_FIELDS.get(token);

		IParser_v2T parser = FileTypeParserFactoryV2.get(key);
		if(parser == null) {
			logger.error("Unknown File type. File: {}", fileName);
			return null;
		}
		List<Rec> records = parser.parse(fileName, recs);
		return records;

	}

	private List<PropRec> getPropeties(String fileName) throws MolFormatException, IOException {

		List<PropRec> recs = new ArrayList<>();

		try (MolImporter mimpo1 = new MolImporter(fileName)) {

			Stream<Molecule> mols = mimpo1.getMolStream();
			mols.forEach(mo -> {
				String fName = Paths.get(mimpo1.getFileName()).getFileName().toString();
				// int recNum = mimpo1.getRecordCount();

				int propNum = mo.getPropertyCount();

				PropRec props = new PropRec();
				for (int i = 0; i < propNum; i++) {

					String key = mo.getPropertyKey(i);
					props.setFileName(fName);
					props.getPropLst().add(key);
					props.getValLst().add((String) mo.getPropertyObject(key));

				}
				recs.add(props);
			});

		}

		return recs;
	}
}