package edu.pezzati.patterns.visitor.shift;

import edu.pezzati.patterns.visitor.DutyOperator;

public class PieceB extends AbstractPiece implements Visitable {

	private boolean isBusy;

	public boolean isBusy() {
		return isBusy;
	}

	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isBusy ? 1231 : 1237);
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
		PieceB other = (PieceB) obj;
		if (isBusy != other.isBusy)
			return false;
		return true;
	}

	@Override
	public void accept(DutyOperator operator) {
		operator.visit(this);
	}
}
