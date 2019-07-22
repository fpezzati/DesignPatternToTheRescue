package edu.pezzati.patterns.state.status;

import edu.pezzati.patterns.state.Status;
import edu.pezzati.patterns.state.util.OurSqlConnection;

public class StatusOK implements Status {

	@Override
	public Status next() {
		return this;
	}

	@Override
	public OurSqlConnection getConnection() {
		return null;
	}

	@Override
	public void setConnection(OurSqlConnection connection) {
		// DO NOTHING.
	}
}
