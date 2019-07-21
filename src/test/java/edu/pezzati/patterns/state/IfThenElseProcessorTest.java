package edu.pezzati.patterns.state;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

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
@PrepareForTest(IfThenElseProcessor.class)
public class IfThenElseProcessorTest {

	private Processor bulkyProcessor;

	@BeforeEach
	public void initEach() {
		bulkyProcessor = Mockito.spy(new IfThenElseProcessor());
	}

	@Test
	public void bulkyProcessoProvidesNoResultWithoutInput() {
		Status initialStatus = null;
		bulkyProcessor.setInput(initialStatus);
		Status finalStatus = bulkyProcessor.getResult();
		Assertions.assertNull(finalStatus);
	}

	@Test
	public void ifOperationAGoesWrongProcessorReturnsAKOState() throws Exception {
		Status initialStatus = new InitialStatus();
		bulkyProcessor.setInput(initialStatus);
		Status expected = getKOStatusBecauseOperationAWentWrong();
//		PowerMockito.when(bulkyProcessor, "applyOperationA", Mockito.any(Status.class)).thenThrow(new Exception());
//		PowerMockito.when(bulkyProcessor, "applyOperationA").thenThrow(new Exception());
		PowerMockito.doThrow(new Exception()).when(bulkyProcessor, "applyOperationA", initialStatus);
		
		
		Status actual = bulkyProcessor.getResult();
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	public void ifOperationBGoesWrongProcessorReturnsAKOState() {
		Status initialStatus = new InitialStatus();
		bulkyProcessor.setInput(initialStatus);
		Status expected = getKOStatusBecauseOperationBWentWrongSoOperationDCleanUp();
		Status actual = bulkyProcessor.getResult();
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	public void ifOperationCGoesWrongOnceProcessorReturnsAnOKState() {
		Status initialStatus = new InitialStatus();
		bulkyProcessor.setInput(initialStatus);
		Status expected = getOKStatusEvenOperationCWentWrong();
		Status actual = bulkyProcessor.getResult();
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	public void ifOperationCGoesWrongTwiceProcessorReturnsAKOStateAfterDoingSomeCleanup() {
		Status initialStatus = new InitialStatus();
		bulkyProcessor.setInput(initialStatus);
		Status expected = getKOStatusBecauseOperationCWentWrongTwice();
		Status actual = bulkyProcessor.getResult();
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	public void ifEveryOperationWentFineProcessorReturnsOKState() {
		Status initialStatus = new InitialStatus();
		bulkyProcessor.setInput(initialStatus);
		Status expected = getOKStatus();
		Status actual = bulkyProcessor.getResult();
		Assertions.assertEquals(expected, actual);
	}
	
	private Status getKOStatusBecauseOperationAWentWrong() {
		Status status = new KOStatus();
		status.getLog().add("operation A went wrong.");
		return status;
	}
	
	private Status getKOStatusBecauseOperationBWentWrongSoOperationDCleanUp() {
		Status status = new KOStatus();
		status.getLog().add("operation A went fine.");
		status.getLog().add("operation B went wrong.");
		status.getLog().add("operation D cleaned up.");
		return status;
	}

	private Status getOKStatusEvenOperationCWentWrong() {
		Status status = new OKStatus();
		status.getLog().add("operation A went fine.");
		status.getLog().add("operation B went fine.");
		status.getLog().add("operation C went wrong 1.");
		status.getLog().add("operation C went fine.");
		return status;
	}

	private Status getKOStatusBecauseOperationCWentWrongTwice() {
		Status status = new OKStatus();
		status.getLog().add("operation A went fine.");
		status.getLog().add("operation B went fine.");
		status.getLog().add("operation C went wrong 1.");
		status.getLog().add("operation C went wrong 2.");
		return status;
	}

	private Status getOKStatus() {
		Status status = new OKStatus();
		status.getLog().add("operation A went fine.");
		status.getLog().add("operation B went fine.");
		status.getLog().add("operation C went fine.");
		return status;
	}
}
