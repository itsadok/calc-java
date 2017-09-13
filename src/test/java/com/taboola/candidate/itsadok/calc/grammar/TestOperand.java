package com.taboola.candidate.itsadok.calc.grammar;

import com.taboola.candidate.itsadok.calc.ProgramState;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TestOperand {
    private void assertOperand(int expected, String line) {
        ProgramState state = new ProgramState();
        state.set("i", 5);
        Operand operand = new Operand(line, 0, state);
        assertTrue(operand.match);
        assertEquals(line.length(), operand.end);
        assertEquals(expected, operand.value);
    }

    @Test
    public void testOperand() {
        assertOperand(1, "1");
        assertOperand(123, "123");
        assertOperand(-1, "-1");
        assertOperand(3, "--3");
        assertOperand(4, "+4");
        assertOperand(5, "+ - - 5");

        assertOperand(5, "i");
        assertOperand(5, "i++");
        assertOperand(6, "++i");
        assertOperand(6, "++i++");
        assertOperand(-5, "-i");
        assertOperand(-6, "-++i");
    }

}
