package edu.pezzati.patterns.state;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
public class IfThenElseProcessorTest {

	private IfThenElseProcessor bulkyProcessor;
	private OurSqlConnection ourSqlConnection;

	@BeforeEach
	public void initEach() {
		bulkyProcessor = new IfThenElseProcessor();
		ourSqlConnection = Mockito.mock(OurSqlConnection.class);
		bulkyProcessor.setSqlConnection(ourSqlConnection);
	}

	@Test
	public void bulkyProcessoProvidesNoResultWithoutInput() {
		IfThenElseStatus initialStatus = null;
		IfThenElseStatus finalStatus = bulkyProcessor.process( initialStatus);
		Assertions.assertNull(finalStatus);
	}

	@Test
	public void ifOperationAGoesWrongProcessorReturnsAKOState() throws Exception {
		IfThenElseStatus initialStatus = new IfThenElseStatus();
		Mockito.doThrow(new Exception()).when(ourSqlConnection).doSomethingThatCanGoWrong();
		IfThenElseStatus expected = getKOStatusBecauseOperationAWentWrong();
		IfThenElseStatus actual = bulkyProcessor.process(initialStatus);
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	public void ifOperationBGoesWrongProcessorReturnsAKOState() throws Exception {
		IfThenElseStatus initialStatus = new IfThenElseStatus();
		Mockito.doNothing().doThrow(new Exception()).doNothing().when(ourSqlConnection).doSomethingThatCanGoWrong();
		IfThenElseStatus expected = getKOStatusBecauseOperationBWentWrongSoOperationDCleanUp();
		IfThenElseStatus actual = bulkyProcessor.process(initialStatus);
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	public void ifOperationCGoesWrongOnceProcessorReturnsAnOKState() throws Exception {
		IfThenElseStatus initialStatus = new IfThenElseStatus();
		Mockito.doNothing().doNothing().doThrow(new Exception()).doNothing().when(ourSqlConnection).doSomethingThatCanGoWrong();
		IfThenElseStatus expected = getOKStatusEvenOperationCWentWrong();
		IfThenElseStatus actual = bulkyProcessor.process(initialStatus);
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	public void ifOperationCGoesWrongTwiceProcessorReturnsAKOStateAfterDoingSomeCleanup() throws Exception {
		IfThenElseStatus initialStatus = new IfThenElseStatus();
		Mockito.doNothing().doNothing().doThrow(new Exception()).doThrow(new Exception()).doNothing().when(ourSqlConnection).doSomethingThatCanGoWrong();
		IfThenElseStatus expected = getKOStatusBecauseOperationCWentWrongTwice();
		IfThenElseStatus actual = bulkyProcessor.process(initialStatus);
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	public void ifEveryOperationWentFineProcessorReturnsOKState() {
		IfThenElseStatus initialStatus = new IfThenElseStatus();
		IfThenElseStatus expected = getOKStatus();
		IfThenElseStatus actual = bulkyProcessor.process(initialStatus);
		Assertions.assertEquals(expected, actual);
	}
	
	private IfThenElseStatus getKOStatusBecauseOperationAWentWrong() {
		IfThenElseStatus status = new IfThenElseStatus();
		status.setStatus("KO");
		status.getLog().add("operation A went wrong.");
		return status;
	}
	
	private IfThenElseStatus getKOStatusBecauseOperationBWentWrongSoOperationDCleanUp() {
		IfThenElseStatus status = new IfThenElseStatus();
		status.setStatus("KO");
		status.getLog().add("operation A went fine.");
		status.getLog().add("operation B went wrong.");
		status.getLog().add("operation D cleaned up.");
		return status;
	}

	private IfThenElseStatus getOKStatusEvenOperationCWentWrong() {
		IfThenElseStatus status = new IfThenElseStatus();
		status.setStatus("OK");
		status.getLog().add("operation A went fine.");
		status.getLog().add("operation B went fine.");
		status.getLog().add("operation C went wrong 1.");
		status.getLog().add("operation C went fine.");
		return status;
	}

	private IfThenElseStatus getKOStatusBecauseOperationCWentWrongTwice() {
		IfThenElseStatus status = new IfThenElseStatus();
		status.setStatus("KO");
		status.getLog().add("operation A went fine.");
		status.getLog().add("operation B went fine.");
		status.getLog().add("operation C went wrong 1.");
		status.getLog().add("operation C went wrong 2.");
		return status;
	}

	private IfThenElseStatus getOKStatus() {
		IfThenElseStatus status = new IfThenElseStatus();
		status.setStatus("OK");
		status.getLog().add("operation A went fine.");
		status.getLog().add("operation B went fine.");
		status.getLog().add("operation C went fine.");
		return status;
	}
}
