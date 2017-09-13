package com.taboola.candidate.itsadok.calc.grammar;

import com.taboola.candidate.itsadok.calc.ProgramState;

/**
 * Implicit grammar rule:
 * Operand -> (NumberOperand|VarOperand)
 */
class Operand {
    boolean match;
    String error;
    int value;
    int end;

    Operand(String line, int start, ProgramState state) {
        NumberOperand numberOperand = new NumberOperand(line, start);
        if (numberOperand.match) {
            match = true;
            value = numberOperand.value;
            end = numberOperand.end;
            return;
        }

        VarOperand varOperand = new VarOperand(line, start, state);
        if (varOperand.match) {
            match = true;
            value = varOperand.value;
            end = varOperand.end;
        }

        error = "Invalid input in pos " + start;
    }
}
