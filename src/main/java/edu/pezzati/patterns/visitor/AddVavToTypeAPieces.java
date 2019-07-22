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
		dutyToReturn.setName(duty.getName());
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
				if(pieces.isEmpty() || !(pieces.get(pieces.size()-1) instanceof Vav)) {
					pieces.add(new Vav());
					pieces.add(piece);
					pieces.add(new Vav());
				} else {
					pieces.add(piece);
					pieces.add(new Vav());
				}
			}
		}
		piecex.getAbstractPiece().clear();
		piecex.getAbstractPiece().addAll(pieces);
		dutyToReturn.getPieceX().add(piecex);
	}
}
