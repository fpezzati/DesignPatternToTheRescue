package edu.pezzati.patterns.state;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IfThenElseStatus {

	private List<String> log;
	private String status;
	
	public IfThenElseStatus() {
		status = "initialstatus";
		log = new ArrayList<>();
	}

	public List<String> getLog() {
		return log;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(log, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IfThenElseStatus other = (IfThenElseStatus) obj;
		if (log == null) {
			if (other.log != null)
				return false;
		} else if (!log.equals(other.log))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
}
