package edu.pezzati.patterns.state.status;

import edu.pezzati.patterns.state.Status;
import edu.pezzati.patterns.state.util.OurSqlConnection;

public class StatusD implements Status {

	private OurSqlConnection connection;

	@Override
	public Status next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OurSqlConnection getConnection() {
		return connection;
	}

	@Override
	public void setConnection(OurSqlConnection connection) {
		this.connection = connection;
	}

}
