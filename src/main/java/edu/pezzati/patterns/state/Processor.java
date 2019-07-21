package edu.pezzati.patterns.state;

/**
 * Processor flow: <br>
 * - apply operation A. If A goes wrong process will end in KO state, <br>
 * - if A went well then apply operation B. If B goes wrong then do operation D,
 * <br>
 * - if B went well then apply operation C. If C goes wrong try it again and if
 * goes wrong, then end in KO state, <br>
 * - D doesn't do too much, it clean up something and end the process in a KO
 * state. <br>
 * - if C went well the whole process ends successfully: OK state.
 * 
 * @author fpezzati
 *
 */
public interface Processor {

	Status getResult();

	void setInput(Status initialStatus);
}
