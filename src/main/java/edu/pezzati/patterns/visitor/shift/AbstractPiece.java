package edu.pezzati.patterns.visitor.shift;

import edu.pezzati.patterns.visitor.DutyOperator;

public class AbstractPiece implements Visitable {

	@Override
	public void accept(DutyOperator operator) {
		operator.visit(this);
	}
}
