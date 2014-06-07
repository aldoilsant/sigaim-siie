package org.sigaim.siie.archetypes;

import java.io.InputStream;

import org.openehr.am.archetype.Archetype;

import se.acode.openehr.parser.ADLParser;

public class FileArchetypeManager {
	private String adlBasePackage="/org/sigaim/siie/data/adl/";
	
	public FileArchetypeManager() {
		System.out.println("Using file archetype manager with package: "+adlBasePackage);
	}
	public String getFilePathByArchetypeId(String archetypeId) {
		String file=adlBasePackage+archetypeId+".adl";
		return file;
	}
	public InputStream getStreamByArchetypeId(String archetypeId) {
		try {
			return this.getClass().getResourceAsStream(this.getFilePathByArchetypeId(archetypeId));
		} catch(Exception e) {
			return null;
		}
	}
	public boolean archetypeExists(String archetypeId) {
		return this.getStreamByArchetypeId(archetypeId)!=null;
	}
	public Archetype getArquetypeById(String archetypeId) {
		ADLParser parser=new ADLParser(this.getStreamByArchetypeId(archetypeId));
		try {
			Archetype arq=parser.parse();
			return arq;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
