package edu.pezzati.patterns.visitor;

import java.util.ArrayList;
import java.util.List;

import edu.pezzati.patterns.visitor.shift.AbstractPiece;
import edu.pezzati.patterns.visitor.shift.Duty;
import edu.pezzati.patterns.visitor.shift.PieceA;
import edu.pezzati.patterns.visitor.shift.PieceB;
import edu.pezzati.patterns.visitor.shift.PieceX;
import edu.pezzati.patterns.visitor.shift.Vav;

public class AddVavToTypeAPieces {

	public Duty addVavs(Duty duty) {
		if(duty == null) return null;
		Duty dutyToReturn = new Duty();
		for(PieceX piecex : duty.getPieceX()) {
			handlePieceX(piecex, dutyToReturn);
		}
		return dutyToReturn;
	}

	private void handlePieceX(PieceX piecex, Duty dutyToReturn) {
		List<AbstractPiece> pieces = new ArrayList<>();
		for(AbstractPiece piece : piecex.getAbstractPiece()) {
			if(piece instanceof Vav) {
				pieces.add(piece);
			} else if (piece instanceof PieceB) {
				pieces.add(piece);
			} else if (piece instanceof PieceA) {
//				if(canAddPieceA(piece, pieces)) {
//					pieces.add(new Vav())
//				}
			}
		}
	}
}
