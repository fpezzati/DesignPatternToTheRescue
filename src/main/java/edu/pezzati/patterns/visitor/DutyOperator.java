package edu.pezzati.patterns.visitor;

import edu.pezzati.patterns.visitor.shift.AbstractPiece;
import edu.pezzati.patterns.visitor.shift.Duty;
import edu.pezzati.patterns.visitor.shift.PieceA;
import edu.pezzati.patterns.visitor.shift.PieceB;
import edu.pezzati.patterns.visitor.shift.PieceX;
import edu.pezzati.patterns.visitor.shift.Vav;

public interface DutyOperator {

	public void visit(Duty duty);
	
	public void visit(PieceX pieceX);
	
	public void visit(PieceA pieceA);
	
	public void visit(PieceB pieceB);
	
	public void visit(Vav vav);
	
	public void visit(AbstractPiece piece);
}
