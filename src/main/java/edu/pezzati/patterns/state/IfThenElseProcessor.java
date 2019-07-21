package edu.pezzati.patterns.state;

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
public class IfThenElseProcessor {

	private OurSqlConnection connection;
	private int operationCFails = 0;

	public IfThenElseStatus process(IfThenElseStatus status) {
		if(status == null) return null;
		switch(status.getStatus()) {
			case "initialstatus":
				process(applyOperationA(status));
				break;
			case "A":
				process(applyOperationB(status));
				break;
			case "B":
				process(applyOperationC(status));
				break;
			case "C":
				process(applyOperationC(status));
				break;
			case "D":
				process(applyOperationD(status));
				break;
			default:
				break;
		}
		return status;
	}

	private IfThenElseStatus applyOperationA(IfThenElseStatus status) {
		try {
			connection.doSomethingThatCanGoWrong();
			status.getLog().add("operation A went fine.");
			status.setStatus("A");
		} catch (Exception e) {
			status.getLog().add("operation A went wrong.");
			status.setStatus("KO");
		}
		return status;
	}
	
	private IfThenElseStatus applyOperationB(IfThenElseStatus status) {
		try {
			connection.doSomethingThatCanGoWrong();
			status.getLog().add("operation B went fine.");
			status.setStatus("C");
		} catch (Exception e) {
			status.getLog().add("operation B went wrong.");
			status.setStatus("D");
		}
		return status;
	}
	
	private IfThenElseStatus applyOperationC(IfThenElseStatus status) {
		try {
			connection.doSomethingThatCanGoWrong();
			status.getLog().add("operation C went fine.");
			status.setStatus("OK");
		} catch (Exception e) {
			operationCFails++;
			if(operationCFails > 1) {
				status.getLog().add("operation C went wrong " + operationCFails + ".");
				status.setStatus("KO");
			} else {
				status.getLog().add("operation C went wrong " + operationCFails + ".");
				status.setStatus("C");
			}
		}
		return status;
	}
	
	private IfThenElseStatus applyOperationD(IfThenElseStatus status) {
		try {
			connection.doSomethingThatCanGoWrong();
			status.getLog().add("operation D cleaned up.");
			status.setStatus("KO");
		} catch (Exception e) {
			status.getLog().add("operation D went wrong.");
			status.setStatus("KO");
		}
		return status;
	}
	
	public void setSqlConnection(OurSqlConnection connection) {
		this.connection = connection;
	}
}
