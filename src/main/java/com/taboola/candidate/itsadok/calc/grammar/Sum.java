package com.taboola.candidate.itsadok.calc.grammar;

import com.taboola.candidate.itsadok.calc.ProgramState;

/**
 * Implicit grammar rules:
 * Sum -> Product SumPart*
 * SumPart -> (+|-) Product
 */
public class Sum {
    public boolean match;
    String error;
    public int value;
    int end;

    public Sum(String line, int start, ProgramState state) {
        Product product = new Product(line, start, state);
        if (!product.match) {
            error = product.error;
            return;
        }
        int sum = product.value;
        int i = product.end;

        while (true) {
            SumPart sp = new SumPart(line, i, state);
            if (!sp.match) {
                match = true;
                value = sum;
                end = i;
                return;
            }
            if (sp.op == '+') {
                sum += sp.value;
            } else {
                sum -= sp.value;
            }
            i = sp.end;
        }
    }
}
