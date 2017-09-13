package com.taboola.candidate.itsadok.calc.grammar;

import com.taboola.candidate.itsadok.calc.ProgramState;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TestSum {
    private void assertSum(int expected, String line) {
        ProgramState state = new ProgramState();
        state.set("i", 5);
        state.set("j", 6);
        Sum sum = new Sum(line, 0, state);
        assertTrue(sum.match);
        assertEquals(line.length(), sum.end);
        assertEquals(expected, sum.value);
    }

    @Test
    public void testSum() {
        assertSum(2, "1+1");
        assertSum(2, "1+++1");
        assertSum(2, "1+--1");
        assertSum(2, "2*3 + 12/-3");
        assertSum(3, "1+2*3-4");
        assertSum(11, " i+j ");
        assertSum(10, "i*j - 120/j");
        assertSum(11, "i+++j++");
        assertSum(1, "6---j*1");
    }
}
