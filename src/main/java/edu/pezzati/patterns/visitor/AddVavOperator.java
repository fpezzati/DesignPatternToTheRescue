package edu.pezzati.patterns.visitor;

import java.util.ArrayList;
import java.util.List;

import edu.pezzati.patterns.visitor.shift.AbstractPiece;
import edu.pezzati.patterns.visitor.shift.Duty;
import edu.pezzati.patterns.visitor.shift.PieceA;
import edu.pezzati.patterns.visitor.shift.PieceB;
import edu.pezzati.patterns.visitor.shift.PieceX;
import edu.pezzati.patterns.visitor.shift.Vav;

public class AddVavOperator implements DutyOperator {
	
	private List<AbstractPiece> pieces;

	@Override
	public void visit(Duty duty) {
		if(duty == null) return;
		for(PieceX piece : duty.getPieceX()) {
			visit(piece);
		}
	}

	@Override
	public void visit(PieceX pieceX) {
		if(pieceX == null) return;
		pieces = new ArrayList<>();
		for(AbstractPiece piece : pieceX.getAbstractPiece()) {
			visit(piece);
		}
		pieceX.getAbstractPiece().clear();
		pieceX.getAbstractPiece().addAll(pieces);
	}

	@Override
	public void visit(PieceA pieceA) {
		if(pieceA == null) return;
		if(pieces.isEmpty() || !(pieces.get(pieces.size()-1) instanceof Vav)) {
			pieces.add(new Vav());
			pieces.add(pieceA);
			pieces.add(new Vav());
		} else {
			pieces.add(pieceA);
			pieces.add(new Vav());
		}
	}

	@Override
	public void visit(PieceB pieceB) {
		if(pieceB == null) return;
		pieces.add(pieceB);
	}

	@Override
	public void visit(Vav vav) {
		if(vav == null) return;
		pieces.add(vav);
	}

	@Override
	public void visit(AbstractPiece piece) {
		if(piece == null) return;
		piece.accept(this);
	}
}
