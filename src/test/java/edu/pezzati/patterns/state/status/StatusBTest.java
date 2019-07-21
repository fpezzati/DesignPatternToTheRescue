package edu.pezzati.patterns.state.status;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsInstanceOf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import edu.pezzati.patterns.state.Status;
import edu.pezzati.patterns.state.util.OurSqlConnection;

/**
 * If B goes well then apply operation C. If B goes wrong then do operation D.
 * @author fpezzati
 *
 */
public class StatusBTest {

	private Status status;
	private OurSqlConnection connection;

	@BeforeEach
	public void initEach() {
		connection = Mockito.mock(OurSqlConnection.class);
		status = new StatusB();
		status.setConnection(connection);
	}
	
	@Test
	public void ifSomethingGoesWrongStatusBEvolvesInStatusD() throws Exception {
		Mockito.doThrow(new Exception()).when(connection).doSomethingThatCanGoWrong();
		Status actual = status.next();
		MatcherAssert.assertThat(actual, IsInstanceOf.instanceOf(StatusD.class));
	}
	
	@Test
	public void ifEverythingGoesFineStatusBEvolvesInStatusC() throws Exception {
		Status expected = new StatusC();
		expected.setConnection(connection);
		Mockito.doNothing().when(connection).doSomethingThatCanGoWrong();
		Status actual = status.next();
		Assertions.assertEquals(expected, actual);
	}
}
