package com.molport.impo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Process command line arguments
 * 
 * @author raitis
 */
public class Args {

	private final String[] args;

	public Args(String[] args) {
		this.args = args;
	}

	public String[] getInputFiles() {
		if (args.length == 0) {
			System.out.println("Usage:\n\n" + "inportFile <sdf files directory> [otput file]\n");
			return null;
		}
		return getFileNames(args[0]);
	}

	public PrintStream getOutputStream() throws FileNotFoundException {
		if (args.length == 0 || args.length == 1) {
			return System.out;
		}
		FileOutputStream fos = new FileOutputStream(args[1]);

		PrintStream p_stream = new PrintStream(fos);
		return p_stream;
	}

	private String[] getFileNames(String dirName) {
		String[] files = null;
		if (dirName == null || dirName.isEmpty()) {
			return null;

		}
		File fi = new File(dirName);
		if (fi.isDirectory()) {

			String[] flst = fi.list();
			files = new String[flst.length];
			for (int i = 0; i < flst.length; i++) {
				files[i] = dirName + "/" + flst[i];

			}
		} else {
			files = new String[1];
			files[0] = dirName;
		}
		return files;
	}
}
