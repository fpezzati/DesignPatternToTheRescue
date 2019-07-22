package edu.pezzati.patterns.visitor.shift;

import java.util.ArrayList;
import java.util.List;

public class PieceX extends Shift {

	private List<AbstractPiece> pieces;
	
	public PieceX() {
		pieces = new ArrayList<>();
	}

	public List<AbstractPiece> getAbstractPiece() {
		return pieces;
	}

	public void setAbstractPiece(List<AbstractPiece> pieces) {
		this.pieces = pieces;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pieces == null) ? 0 : pieces.hashCode());
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
		PieceX other = (PieceX) obj;
		if (pieces == null) {
			if (other.pieces != null)
				return false;
		} else if (!pieces.equals(other.pieces))
			return false;
		return true;
	}
}
