package com.taboola.candidate.itsadok.calc.grammar;

import com.taboola.candidate.itsadok.calc.UnknownVariableError;
import com.taboola.candidate.itsadok.calc.ProgramState;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Statement {
    public boolean match;
    public String error;
    public int value;

    public Statement(String line, ProgramState state) {
        Matcher statement = Pattern.compile("\\s*([a-zA-Z_]\\w*)\\s*(\\+=|-=|=)\\s*").matcher(line);
        if (!statement.lookingAt()) {
            return;
        }

        String varName = statement.group(1);

        char op = line.charAt(statement.start(2));
        if (op == '+' || op == '-') {
            if (!state.varExists(varName)) {
                throw new UnknownVariableError("Unknown variable " + varName + " referenced in: " + line);
            }
        }

        Sum sum = new Sum(line, statement.end(), state);
        if (!sum.match) {
            error = sum.error;
            return;
        }
        if (sum.end != line.length()) {
            error = "Leftover characters after statement at pos " + sum.end + ": " + line.substring(sum.end);
            return;
        }
        int result = sum.value;

        switch (op) {
            case '=':
                state.set(varName, result);
                break;
            case '+':
                state.set(varName, state.get(varName) + result);
                break;
            case '-':
                state.set(varName, state.get(varName) - result);
                break;
        }

        match = true;
        value = state.get(varName);
    }
}
