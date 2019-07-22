package edu.pezzati.patterns.visitor.shift;

import edu.pezzati.patterns.visitor.DutyOperator;

public interface Visitable {

	public void accept(DutyOperator operator);
}
