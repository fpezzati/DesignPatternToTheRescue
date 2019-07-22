package edu.pezzati.patterns.visitor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.pezzati.patterns.visitor.shift.Duty;
import edu.pezzati.patterns.visitor.shift.PieceA;
import edu.pezzati.patterns.visitor.shift.PieceB;
import edu.pezzati.patterns.visitor.shift.PieceX;
import edu.pezzati.patterns.visitor.shift.Vav;

/**
 * Add Vav before and after each type A piece into a given duty.
 * @author PEFR
 *
 */
public class AddVavToTypeAPiecesTest {

	private AddVavToTypeAPieces addVavToTypeAPieces;

	@BeforeEach
	public void initEach() {
		addVavToTypeAPieces = new AddVavToTypeAPieces();
	}
	
	@Test
	public void ifShiftIsNullItDoesNothing() {
		Duty duty = null;
		Duty actual = addVavToTypeAPieces.addVavs(duty);
		Assertions.assertNull(actual);
	}
	
	@Test
	public void ifShiftDoesntContainAnyTypeAPiecesNewShiftIsEqualToOldOne() {
		Duty expected = getSomeShiftsWithoutPieceAObjects();
		Duty actual = addVavToTypeAPieces.addVavs(expected);
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	public void ifShiftContainsThreeAdiacentTypeAPiecesThenOperatorAddFourVavObjects() {
		Duty expected = getExpectedDutyWithFourVav();
		Duty actual = addVavToTypeAPieces.addVavs(getSomeShiftsWithThreeAdiacentTypeAPieces());
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	public void ifShifContainsThreeNotAdiacentTypeAPiecesThenOperatorAddSixVavObjects() {
		Duty expected = getExpectedDutyWithSixVav();
		Duty actual = addVavToTypeAPieces.addVavs(getSomeShiftsWithThreeNotAdiacentTypeAPieces());
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	public void ifShifContainsTwoAdiacentTypeAPiecesAndOneNotThenOperatorAddFiveVavObjects() {
		Duty expected = getExpectedDutyWithFiveVav();
		Duty actual = addVavToTypeAPieces.addVavs(getSomeShiftsWithTwoAdiacentAndOneNotTypeAPieces());
		Assertions.assertEquals(expected, actual);
	}
	
	private Duty getSomeShiftsWithoutPieceAObjects() {
		Duty duty = new Duty();
		duty.setName("duty");
		duty.getPieceX().add(new PieceX());
		duty.getPieceX().add(new PieceX());
		duty.getPieceX().add(new PieceX());
		duty.getPieceX().add(new PieceX());
		duty.getPieceX().get(1).getAbstractPiece().add(new PieceB());
		duty.getPieceX().get(1).getAbstractPiece().add(new PieceB());
		duty.getPieceX().get(2).getAbstractPiece().add(new PieceB());
		return duty;
	}
	
	private Duty getSomeShiftsWithThreeAdiacentTypeAPieces() {
		Duty duty = getSomeShiftsWithoutPieceAObjects();
		duty.getPieceX().get(2).getAbstractPiece().add(new PieceA());
		duty.getPieceX().get(2).getAbstractPiece().add(new PieceA());
		duty.getPieceX().get(2).getAbstractPiece().add(new PieceA());
		return duty;
	}
	
	private Duty getSomeShiftsWithTwoAdiacentAndOneNotTypeAPieces() {
		Duty duty = getSomeShiftsWithoutPieceAObjects();
		duty.getPieceX().get(1).getAbstractPiece().add(new PieceA());
		duty.getPieceX().get(1).getAbstractPiece().add(new PieceA());
		duty.getPieceX().get(2).getAbstractPiece().add(new PieceA());
		return duty;
	}
	
	private Duty getSomeShiftsWithThreeNotAdiacentTypeAPieces() {
		Duty duty = getSomeShiftsWithoutPieceAObjects();
		duty.getPieceX().get(0).getAbstractPiece().add(new PieceA());
		duty.getPieceX().get(1).getAbstractPiece().add(new PieceA());
		duty.getPieceX().get(2).getAbstractPiece().add(new PieceA());
		return duty;
	}
	
	private Duty getExpectedDutyWithFourVav() {
		Duty duty = new Duty();
		duty.setName("duty");
		duty.getPieceX().add(new PieceX());
		duty.getPieceX().add(new PieceX());
		duty.getPieceX().add(new PieceX());
		duty.getPieceX().add(new PieceX());
		duty.getPieceX().get(1).getAbstractPiece().add(new PieceB());
		duty.getPieceX().get(1).getAbstractPiece().add(new PieceB());
		duty.getPieceX().get(2).getAbstractPiece().add(new PieceB());
		duty.getPieceX().get(2).getAbstractPiece().add(new Vav());
		duty.getPieceX().get(2).getAbstractPiece().add(new PieceA());
		duty.getPieceX().get(2).getAbstractPiece().add(new Vav());
		duty.getPieceX().get(2).getAbstractPiece().add(new PieceA());
		duty.getPieceX().get(2).getAbstractPiece().add(new Vav());
		duty.getPieceX().get(2).getAbstractPiece().add(new PieceA());
		duty.getPieceX().get(2).getAbstractPiece().add(new Vav());
		return duty;
	}
	
	private Duty getExpectedDutyWithFiveVav() {
		Duty duty = new Duty();
		duty.setName("duty");
		duty.getPieceX().add(new PieceX());
		duty.getPieceX().add(new PieceX());
		duty.getPieceX().add(new PieceX());
		duty.getPieceX().add(new PieceX());
		duty.getPieceX().get(1).getAbstractPiece().add(new PieceB());
		duty.getPieceX().get(1).getAbstractPiece().add(new PieceB());
		duty.getPieceX().get(2).getAbstractPiece().add(new PieceB());
		duty.getPieceX().get(1).getAbstractPiece().add(new Vav());
		duty.getPieceX().get(1).getAbstractPiece().add(new PieceA());
		duty.getPieceX().get(1).getAbstractPiece().add(new Vav());
		duty.getPieceX().get(1).getAbstractPiece().add(new PieceA());
		duty.getPieceX().get(1).getAbstractPiece().add(new Vav());
		duty.getPieceX().get(2).getAbstractPiece().add(new Vav());
		duty.getPieceX().get(2).getAbstractPiece().add(new PieceA());
		duty.getPieceX().get(2).getAbstractPiece().add(new Vav());
		return duty;
	}
	
	private Duty getExpectedDutyWithSixVav() {
		Duty duty = new Duty();
		duty.setName("duty");
		duty.getPieceX().add(new PieceX());
		duty.getPieceX().add(new PieceX());
		duty.getPieceX().add(new PieceX());
		duty.getPieceX().add(new PieceX());
		duty.getPieceX().get(1).getAbstractPiece().add(new PieceB());
		duty.getPieceX().get(1).getAbstractPiece().add(new PieceB());
		duty.getPieceX().get(2).getAbstractPiece().add(new PieceB());
		duty.getPieceX().get(0).getAbstractPiece().add(new Vav());
		duty.getPieceX().get(0).getAbstractPiece().add(new PieceA());
		duty.getPieceX().get(0).getAbstractPiece().add(new Vav());
		duty.getPieceX().get(1).getAbstractPiece().add(new Vav());
		duty.getPieceX().get(1).getAbstractPiece().add(new PieceA());
		duty.getPieceX().get(1).getAbstractPiece().add(new Vav());
		duty.getPieceX().get(2).getAbstractPiece().add(new Vav());
		duty.getPieceX().get(2).getAbstractPiece().add(new PieceA());
		duty.getPieceX().get(2).getAbstractPiece().add(new Vav());
		return duty;
	}
}
