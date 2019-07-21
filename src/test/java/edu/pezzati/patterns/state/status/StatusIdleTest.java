package edu.pezzati.patterns.state.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import edu.pezzati.patterns.state.Status;
import edu.pezzati.patterns.state.status.StatusIdle;
import edu.pezzati.patterns.state.util.OurSqlConnection;

public class StatusIdleTest {

	private Status status;
	private OurSqlConnection connection;

	@BeforeEach
	public void initEach() {
		connection = Mockito.mock(OurSqlConnection.class);
		status = new StatusIdle();
		status.setConnection(connection);
	}
	
	@Test
	public void statusIdleEvolvesIntoStatusA() {
		Status expected = new StatusA();
		Status actual = status.next();
		Assertions.assertEquals(expected, actual);
	}
}
