package edu.pezzati.patterns.state;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsInstanceOf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import edu.pezzati.patterns.state.status.StatusIdle;
import edu.pezzati.patterns.state.status.StatusKO;
import edu.pezzati.patterns.state.status.StatusOK;
import edu.pezzati.patterns.state.util.OurSqlConnection;

/**
 * Processor flow: <br>
 * - apply operation A. If A goes wrong process will end in KO state, <br>
 * - if A went well then apply operation B. If B goes wrong then do operation D,
 * <br>
 * - if B went well then apply operation C. If C goes wrong try it again and if
 * goes wrong, then end in KO state, <br>
 * - D doesn't do too much, it clean up something and end the process in a KO
 * state. <br>
 * - if C went well the whole process ends successfully: OK state.
 * 
 * @author fpezzati
 *
 */
public class PatternProcessorTest {

	private Processor bulkyProcessor;
	private OurSqlConnection ourSqlConnection;
	private Status initialStatus;
	
	@BeforeEach
	public void initEach() {
		bulkyProcessor = new PatternProcessor();
		ourSqlConnection = Mockito.mock(OurSqlConnection.class);
		bulkyProcessor.setSqlConnection(ourSqlConnection);
		initialStatus = new StatusIdle();
	}
	
	@Test
	public void bulkyProcessoProvidesNoResultWithoutInput() {
		Status initialStatus = null;
		Status finalStatus = bulkyProcessor.process(initialStatus);
		Assertions.assertNull(finalStatus);
	}

	@Test
	public void ifOperationAGoesWrongProcessorReturnsAKOState() throws Exception {
		Mockito.doThrow(new Exception()).when(ourSqlConnection).doSomethingThatCanGoWrong();
		Status actual = bulkyProcessor.process(initialStatus);
		MatcherAssert.assertThat(actual, IsInstanceOf.instanceOf(StatusKO.class));
	}
	
	@Test
	public void ifOperationBGoesWrongProcessorReturnsAKOState() throws Exception {
		Mockito.doNothing().doThrow(new Exception()).doNothing().when(ourSqlConnection).doSomethingThatCanGoWrong();
		Status actual = bulkyProcessor.process(initialStatus);
		MatcherAssert.assertThat(actual, IsInstanceOf.instanceOf(StatusKO.class));
	}
	
	@Test
	public void ifOperationCGoesWrongOnceProcessorReturnsAnOKState() throws Exception {
		Mockito.doNothing().doNothing().doThrow(new Exception()).doNothing().when(ourSqlConnection).doSomethingThatCanGoWrong();
		Status actual = bulkyProcessor.process(initialStatus);
		MatcherAssert.assertThat(actual, IsInstanceOf.instanceOf(StatusOK.class));
	}
	
	@Test
	public void ifOperationCGoesWrongTwiceProcessorReturnsAKOStateAfterDoingSomeCleanup() throws Exception {
		Mockito.doNothing().doNothing().doThrow(new Exception()).doThrow(new Exception()).doNothing().when(ourSqlConnection).doSomethingThatCanGoWrong();
		Status actual = bulkyProcessor.process(initialStatus);
		MatcherAssert.assertThat(actual, IsInstanceOf.instanceOf(StatusKO.class));
	}
	
	@Test
	public void ifEveryOperationWentFineProcessorReturnsOKState() {
		Status actual = bulkyProcessor.process(initialStatus);
		MatcherAssert.assertThat(actual, IsInstanceOf.instanceOf(StatusOK.class));
	}
}
