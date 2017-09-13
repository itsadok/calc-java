package com.taboola.candidate.itsadok.calc.grammar;

import com.taboola.candidate.itsadok.calc.ProgramState;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TestStatement {
    private void assertStatement(int expected, String line) {
        ProgramState state = new ProgramState();
        state.set("i", 5);
        state.set("j", 6);
        Statement statement = new Statement(line, state);
        assertTrue(statement.match);
        assertEquals(expected, statement.value);
    }

    @Test
    public void testStatement() {
        assertStatement(5, "k=5");
        assertStatement(6, "i+=1");
        assertStatement(1, "j-=i");
        assertStatement(0, "k = i*j - 20 - i++ - --j");
    }

    @Test
    public void testWholeProgram() {
        ProgramState state = new ProgramState();
        state.executeLine("i = 0");
        state.executeLine("j = ++i");
        state.executeLine("x = i++ + 5");
        state.executeLine("y = 5 + 3 * 10");
        state.executeLine("i += y");

        assertEquals("(i=37,j=1,x=6,y=35)", state.toString());
    }
}
