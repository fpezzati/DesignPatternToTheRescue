package edu.pezzati.patterns.state;

import java.util.ArrayList;
import java.util.List;

public class KOStatus implements Status {
	
	private List<String> log;
	private String status;

	public KOStatus() {
		log = new ArrayList<>();
	}

	public List<String> getLog() {
		return log;
	}
	
	@Override
	public String getStatus() {
		return status;
	}
}
