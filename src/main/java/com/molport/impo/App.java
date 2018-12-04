package com.molport.impo;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;

import com.molport.impo.out.OutputFormater;
import com.molport.impo.parsers.Parser_v2;
import com.molport.impo.parsers.Rec;
//import chemaxon.formats.MolImporter

/**
 *
 * @author raitis
 */
public class App {

	// private static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {

		
		PropertyConfigurator.configure("log4j.properties");

		Args argsCls = new Args(args);
		String[] inputFiles = argsCls.getInputFiles();
		PrintStream output = null;
		if (inputFiles == null) {
			return;
		}
		try {
			output = argsCls.getOutputStream();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			return;
		}

		Parser_v2 parser = new Parser_v2();
		OutputFormater oFmt = new OutputFormater();
		PrintStream ps = new PrintStream(output);

		for (String filePath : inputFiles) {
			List<Rec> result = parser.doParse(filePath);

			oFmt.outPrint(ps, result);

		}
		ps.close();
	}

}
