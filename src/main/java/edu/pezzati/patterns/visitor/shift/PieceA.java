package edu.pezzati.patterns.visitor.shift;

import edu.pezzati.patterns.visitor.DutyOperator;

public class PieceA extends AbstractPiece implements Visitable {

	private boolean isOk;

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isOk ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PieceA other = (PieceA) obj;
		if (isOk != other.isOk)
			return false;
		return true;
	}

	@Override
	public void accept(DutyOperator operator) {
		operator.visit(this);
	}
}
