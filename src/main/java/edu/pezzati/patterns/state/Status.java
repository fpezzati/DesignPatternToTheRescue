package edu.pezzati.patterns.state;

import java.util.List;

public interface Status {

	List<String> getLog();
	
	String getStatus();
}
