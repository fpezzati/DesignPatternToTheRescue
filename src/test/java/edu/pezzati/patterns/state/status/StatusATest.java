package edu.pezzati.patterns.state.status;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsInstanceOf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import edu.pezzati.patterns.state.Status;
import edu.pezzati.patterns.state.util.OurSqlConnection;

public class StatusATest {

	private Status status;
	private OurSqlConnection connection;

	@BeforeEach
	public void initEach() {
		connection = Mockito.mock(OurSqlConnection.class);
		status = new StatusA();
		status.setConnection(connection);
	}
	
	@Test
	public void ifSomethingGoesWrongStatusAEvolvesInStatusKO() throws Exception {
		Mockito.doThrow(new Exception()).when(connection).doSomethingThatCanGoWrong();
		Status actual = status.next();
		MatcherAssert.assertThat(actual, IsInstanceOf.instanceOf(StatusKO.class));
	}
	
	@Test
	public void ifEverythingGoesFineStatusAEvolvesInStatusB() throws Exception {
		Status expected = new StatusB();
		expected.setConnection(connection);
		Mockito.doNothing().when(connection).doSomethingThatCanGoWrong();
		Status actual = status.next();
		Assertions.assertEquals(expected, actual);
	}
}
