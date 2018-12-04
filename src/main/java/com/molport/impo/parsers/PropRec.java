package com.molport.impo.parsers;

import java.util.ArrayList;
import java.util.List;

public class PropRec {

	private String FileName;
	private List<String> propLst = new ArrayList<>();
	private List<String> valLst = new ArrayList<>();

	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}

	public List<String> getPropLst() {
		return propLst;
	}

	public void setPropLst(List<String> propLst) {
		this.propLst = propLst;
	}

	public List<String> getValLst() {
		return valLst;
	}

	public void setValLst(List<String> valLst) {
		this.valLst = valLst;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<").append(FileName).append(">");
		sb.append("\npropLst:\n");
		for(int i=0; i< propLst.size(); i++) {
			sb.append("P: ").append(propLst.get(i)).append(" : ").append(valLst.get(i)).append("\n");
			
		}
		return sb.toString();
	}

}
