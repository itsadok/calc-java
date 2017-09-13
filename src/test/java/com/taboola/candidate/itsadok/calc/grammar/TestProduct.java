package com.taboola.candidate.itsadok.calc.grammar;

import com.taboola.candidate.itsadok.calc.ProgramState;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TestProduct {
    private void assertProduct(int expected, String line) {
        ProgramState state = new ProgramState();
        state.set("i", 5);
        state.set("j", 6);
        Product product = new Product(line, 0, state);
        assertTrue(product.match);
        assertEquals(line.length(), product.end);
        assertEquals(expected, product.value);
    }

    @Test
    public void testProduct() {
        assertProduct(15, "3*5");
        assertProduct(15, "-3*---5");
        assertProduct(24, "1*2*3*4");
        assertProduct(4, "12/3");
        assertProduct(1, "12/3/4");

        assertProduct(10, "i*2");
        assertProduct(10, "i++*2");
        assertProduct(12, "++i*2");
        assertProduct(30, "i*j");
    }
}
