package edu.pezzati.patterns.state.status;

import java.util.Objects;

import edu.pezzati.patterns.state.Status;
import edu.pezzati.patterns.state.util.OurSqlConnection;

public class StatusCRetry implements Status {

	private OurSqlConnection connection;

	@Override
	public Status next() {
		try {
			connection.doSomethingThatCanGoWrong();
			return new StatusOK();
		} catch (Exception e) {
			return new StatusKO();
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
		StatusCRetry other = (StatusCRetry) obj;
		if (connection == null) {
			if (other.connection != null)
				return false;
		} else if (!connection.equals(other.connection))
			return false;
		return true;
	}
}
