package edu.pezzati.patterns.state;

import java.util.ArrayList;
import java.util.List;

public class InitialStatus implements Status {
	
	@Override
	public List<String> getLog() {
		return new ArrayList<String>();
	}

	@Override
	public String getStatus() {
		return "idle";
	}
}
