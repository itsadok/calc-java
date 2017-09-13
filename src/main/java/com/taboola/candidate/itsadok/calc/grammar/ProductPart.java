package com.taboola.candidate.itsadok.calc.grammar;

import com.taboola.candidate.itsadok.calc.ProgramState;

class ProductPart {
    boolean match;
    String error;
    char op;
    int value;
    int end;

    ProductPart(String line, int start, ProgramState state) {
        int i = start;
        while (true) {
            if (i == line.length()) return;
            if (line.charAt(i) != ' ') break;
            i++;
        }

        op = line.charAt(i);
        if (op != '*' && op != '/') return;
        i++;

        Operand operand = new Operand(line, i, state);
        if (operand.match) {
            value = operand.value;
            end = operand.end;
            match = true;
        }
    }
}
