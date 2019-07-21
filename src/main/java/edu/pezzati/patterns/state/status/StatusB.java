package edu.pezzati.patterns.state.status;

import java.util.Objects;

import edu.pezzati.patterns.state.Status;
import edu.pezzati.patterns.state.util.OurSqlConnection;

public class StatusB implements Status {
	
	private OurSqlConnection connection;

	@Override
	public Status next() {
		try {
			connection.doSomethingThatCanGoWrong();
			Status status = new StatusC();
			status.setConnection(connection);
			return status;
		} catch (Exception e) {
			Status status = new StatusD();
			status.setConnection(connection);
			return status;
		}
	}

	@Override
	public OurSqlConnection getConnection() {
		return connection;
	}

	@Override
	public void setConnection(OurSqlConnection connection) {
		this.connection = connection;
	}

	@Override
	public int hashCode() {
		return Objects.hash(connection);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatusB other = (StatusB) obj;
		if (connection == null) {
			if (other.connection != null)
				return false;
		} else if (!connection.equals(other.connection))
			return false;
		return true;
	}
}
