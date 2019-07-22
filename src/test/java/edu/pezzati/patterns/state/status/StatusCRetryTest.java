package edu.pezzati.patterns.state.status;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsInstanceOf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import edu.pezzati.patterns.state.Status;
import edu.pezzati.patterns.state.util.OurSqlConnection;

/**
 * If C goes wrong on the second try, then end in KO state. Otherwise end in OK state.
 * @author PEFR
 *
 */
public class StatusCRetryTest {

	private Status status;
	private OurSqlConnection connection;

	@BeforeEach
	public void initEach() {
		connection = Mockito.mock(OurSqlConnection.class);
		status = new StatusCRetry();
		status.setConnection(connection);
	}
	
	@Test
	public void ifSomethingGoesWrongStatusCRetryEvolvesInStatusKO() throws Exception {
		Mockito.doThrow(new Exception()).when(connection).doSomethingThatCanGoWrong();
		Status actual = status.next();
		MatcherAssert.assertThat(actual, IsInstanceOf.instanceOf(StatusKO.class));
	}
	
	@Test
	public void ifEverythingGoesFineStatusCRetryEvolvesInStatusOK() throws Exception {
		Mockito.doNothing().when(connection).doSomethingThatCanGoWrong();
		Status actual = status.next();
		MatcherAssert.assertThat(actual, IsInstanceOf.instanceOf(StatusOK.class));
	}
}
