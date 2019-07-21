package edu.pezzati.patterns.state;

public class IfThenElseProcessor implements Processor {

	private Status status;

	public Status getResult() {
		if(status == null) return null;
		Status statusToReturn = null;
		try {
			statusToReturn = applyOperationA(status);
		} catch (Exception e) {
			statusToReturn = new KOStatus();
			statusToReturn.getLog().add("operation A went wrong.");
			return statusToReturn;
		}
		try {
			statusToReturn = applyOperationB(statusToReturn);
		} catch (Exception e) {
			statusToReturn = new KOStatus();
			statusToReturn.getLog().add("operation B went wrong.");
			return statusToReturn;
		}
		return statusToReturn;
	}

	private Status applyOperationA(Status status) throws Exception {
		Status ok = new OKStatus();
		ok.getLog().add("operation A went fine.");
		return ok;
	}
	
	private Status applyOperationB(Status status) throws Exception {
		Status ok = new OKStatus();
		ok.getLog().add("operation B went fine.");
		return ok;
	}

	public void setInput(Status initialStatus) {
		status = initialStatus;
	}
}
