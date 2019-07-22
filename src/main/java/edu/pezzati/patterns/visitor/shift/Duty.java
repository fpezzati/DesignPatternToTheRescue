package edu.pezzati.patterns.visitor.shift;

import java.util.ArrayList;
import java.util.List;

import edu.pezzati.patterns.visitor.DutyOperator;

public class Duty extends Shift implements Visitable {

	private List<PieceX> pieceX;
	
	public Duty() {
		pieceX = new ArrayList<>();
	}

	public List<PieceX> getPieceX() {
		return pieceX;
	}

	public void setPieceX(List<PieceX> pieceX) {
		this.pieceX = pieceX;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pieceX == null) ? 0 : pieceX.hashCode());
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
		Duty other = (Duty) obj;
		if (pieceX == null) {
			if (other.pieceX != null)
				return false;
		} else if (!pieceX.equals(other.pieceX))
			return false;
		return true;
	}

	@Override
	public void accept(DutyOperator operator) {
		operator.visit(this);
	}
}
