package edu.pezzati.patterns.state;

public class PatternProcessor implements Processor {

	private Status status;

	public Status getResult() {
		return status;
	}

	public void setInput(Status initialStatus) {
		status = initialStatus;
	}
}
