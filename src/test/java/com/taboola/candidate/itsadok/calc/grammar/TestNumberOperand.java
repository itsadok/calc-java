package com.taboola.candidate.itsadok.calc.grammar;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestNumberOperand {

    private void assertNumberOperand(int expected, String line) {
        NumberOperand numberOperand = new NumberOperand(line, 0);
        assertTrue(numberOperand.match);
        assertTrue(
                "Extra characters not consumed: " + line.substring(numberOperand.end),
                line.length() == numberOperand.end
        );
        assertEquals("Wrong value.", expected, numberOperand.value);
    }

    @Test
    public void testNumberOperand() {
        assertNumberOperand(1, "1");
        assertNumberOperand(123, "123");
        assertNumberOperand(-1, "-1");
        assertNumberOperand(3, "--3");
        assertNumberOperand(4, "+4");
        assertNumberOperand(5, "+ - - 5");
    }

}
