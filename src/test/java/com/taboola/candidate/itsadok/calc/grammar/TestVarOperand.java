package com.taboola.candidate.itsadok.calc.grammar;

import com.taboola.candidate.itsadok.calc.ProgramState;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TestVarOperand {

    private void assertVarOperand(int expected, String line) {
        ProgramState state = new ProgramState();
        state.set("i", 5);
        VarOperand varOperand = new VarOperand(line, 0, state);
        assertTrue(varOperand.match);
        assertEquals(line.length(), varOperand.end);
        assertEquals(expected, varOperand.value);
    }

    @Test
    public void testVarOperand() {
        assertVarOperand(5, "i");
        assertVarOperand(5, "i++");
        assertVarOperand(6, "++i");
        assertVarOperand(6, "++i++");
        assertVarOperand(-5, "-i");
        assertVarOperand(-6, "-++i");
        assertVarOperand(4, "--i");
        assertVarOperand(5, "++--i");
    }
}
