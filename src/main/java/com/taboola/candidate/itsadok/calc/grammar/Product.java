package com.taboola.candidate.itsadok.calc.grammar;

import com.taboola.candidate.itsadok.calc.ProgramState;

/**
 * Implicit grammar rule:
 * Product -> Operand ProductPart*
 * ProductPart -> (*|/) Operand
 */
class Product {
    boolean match;
    String error;
    int value;
    int end;

    Product(String line, int start, ProgramState state) {
        Operand operand = new Operand(line, start, state);
        if (!operand.match) {
            error = operand.error;
            return;
        }
        int product = operand.value;
        int i = operand.end;

        while (true) {
            ProductPart p = new ProductPart(line, i, state);
            if (!p.match) {
                match = true;
                value = product;
                end = i;
                return;
            }
            if (p.op == '*') {
                product *= p.value;
            } else {
                product /= p.value;
            }
            i = p.end;
        }
    }
}
