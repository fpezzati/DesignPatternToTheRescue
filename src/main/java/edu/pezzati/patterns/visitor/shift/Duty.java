package edu.pezzati.patterns.visitor.shift;

import java.util.List;

public class Duty extends Shift {

	private List<PieceX> pieceX;

	public List<PieceX> getPieceX() {
		return pieceX;
	}

	public void setPieceX(List<PieceX> pieceX) {
		this.pieceX = pieceX;
	}
}
