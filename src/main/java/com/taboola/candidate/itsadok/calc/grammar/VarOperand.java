package com.taboola.candidate.itsadok.calc.grammar;

import com.taboola.candidate.itsadok.calc.UnknownVariableError;
import com.taboola.candidate.itsadok.calc.ProgramState;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An operand containing a variable can have several preincrement and postincrement operators.
 * Care should be taken to not greedily consume an addition or subtraction operator as part of a postincrement operator.
 * e.g. the expression i++2 should be interpreted as i + (+2), and not as i++ followed by a dangling 2.
 * This is implemented here using regular expression lookahead, for code simplicity.
 * The lookahead ensures that after we consumed all the post-increment operators, we wither reach the end of the string
 * or another operator.
 */
class VarOperand {
    boolean match;
    int value;
    int end;

    VarOperand(String line, int start, ProgramState state) {
        Matcher m = Pattern.compile("([-+\\s]*?)((?:\\+\\+|--|\\s)*)([_a-zA-Z]\\w*)((?:\\+\\+|--|\\s)*)(?=$|[-+*/])").matcher(line);
        if (!m.region(start, line.length()).lookingAt()) {
            return;
        }
        match = true;
        end = m.end();

        String varName = m.group(3);
        if (!state.varExists(varName)) {
            throw new UnknownVariableError("Unknown variable " + varName);
        }
        int varValue = state.get(varName);
        // Since the regex ensures a specific structure to the pre-increment section, we can be somewhat loose
        // when decoding the sequence of preinc operators. The same thing is done below for the postinc operators.
        for (int i = m.start(2); i < m.end(2); ) {
            char c = line.charAt(i);
            if (c == '+') {
                varValue++;
                i += 2;
            } else if (c == '-') {
                varValue--;
                i += 2;
            } else {
                i += 1;
            }
        }
        this.value = varValue;

        for (int i = m.start(4); i < m.end(4); ) {
            char c = line.charAt(i);
            if (c == '+') {
                varValue++;
                i += 2;
            } else if (c == '-') {
                varValue--;
                i += 2;
            } else {
                i += 1;
            }
        }
        state.set(varName, varValue);

        int sign = 1;
        for (int i = m.start(1); i < m.end(1); i++) {
            if (line.charAt(i) == '-') {
                sign *= -1;
            }
        }
        this.value *= sign;
    }
}
