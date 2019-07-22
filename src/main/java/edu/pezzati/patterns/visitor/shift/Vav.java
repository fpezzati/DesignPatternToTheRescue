package edu.pezzati.patterns.visitor.shift;

public class Vav extends AbstractPiece {
	
	private boolean isLazy;

	public boolean isLazy() {
		return isLazy;
	}

	public void setLazy(boolean isLazy) {
		this.isLazy = isLazy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isLazy ? 1231 : 1237);
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
		Vav other = (Vav) obj;
		if (isLazy != other.isLazy)
			return false;
		return true;
	}
}
