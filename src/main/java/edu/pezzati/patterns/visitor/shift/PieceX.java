package edu.pezzati.patterns.visitor.shift;

import java.util.List;

public class PieceX extends Shift {

	private List<AbstractPiece> pieces;

	public List<AbstractPiece> getAbstractPiece() {
		return pieces;
	}

	public void setAbstractPiece(List<AbstractPiece> pieces) {
		this.pieces = pieces;
	}
}
