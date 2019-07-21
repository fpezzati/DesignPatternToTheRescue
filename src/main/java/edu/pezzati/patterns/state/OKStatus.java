package edu.pezzati.patterns.state;

import java.util.ArrayList;
import java.util.List;

public class OKStatus implements Status {

	private List<String> log;
	private String status;

	public OKStatus() {
		log = new ArrayList<>();
	}

	public Status applyOperationA() {
		return null;
	}

	public List<String> getLog() {
		return log;
	}

	@Override
	public String getStatus() {
		return status;
	}
}
