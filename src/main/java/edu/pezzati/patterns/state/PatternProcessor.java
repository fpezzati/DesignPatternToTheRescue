package edu.pezzati.patterns.state;

import edu.pezzati.patterns.state.util.OurSqlConnection;

public class PatternProcessor implements Processor {

	@Override
	public Status process(Status status) {
		return null;
	}

	@Override
	public void setSqlConnection(OurSqlConnection connection) {
		// TODO Auto-generated method stub
		
	}
}
