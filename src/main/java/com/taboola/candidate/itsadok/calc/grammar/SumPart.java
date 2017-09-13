package com.taboola.candidate.itsadok.calc.grammar;

import com.taboola.candidate.itsadok.calc.ProgramState;

class SumPart {
    boolean match;
    String error;
    char op;
    int value;
    int end;

    SumPart(String line, int start, ProgramState state) {
        int i = start;
        while (true) {
            if (i == line.length()) return;
            if (line.charAt(i) != ' ') break;
            i++;
        }

        op = line.charAt(i);
        if (op != '+' && op != '-') return;
        i++;

        Product product = new Product(line, i, state);

        if (product.match) {
            match = true;
            value = product.value;
            end = product.end;
        }
    }
}
