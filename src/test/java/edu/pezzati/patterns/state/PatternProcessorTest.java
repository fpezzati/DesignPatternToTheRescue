package edu.pezzati.patterns.state;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatternProcessorTest {

    @Test
    public void bulkyProcessoProvidesNoResultWithoutInput() {
	Processor bulkyProcessor = new PatternProcessor();
	Status initialStatus = null;
	bulkyProcessor.setInput(initialStatus);
	Status finalStatus = bulkyProcessor.getResult();
	Assertions.assertNull(finalStatus);
    }
}
