package edu.pezzati.patterns.state;

import edu.pezzati.patterns.state.status.StatusKO;
import edu.pezzati.patterns.state.status.StatusOK;
import edu.pezzati.patterns.state.util.OurSqlConnection;

public class PatternProcessor implements Processor {

	private OurSqlConnection connection;

	@Override
	public Status process(Status status) {
		if(status == null) return null;
		Status statusToReturn = null;
		if(status instanceof StatusKO || status instanceof StatusOK) {
			return status;
		} else {
			status.setConnection(connection);
			statusToReturn = process(status.next());
		}
		return statusToReturn;
	}

	@Override
	public void setSqlConnection(OurSqlConnection connection) {
		this.connection = connection;
	}
}
