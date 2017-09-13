package com.taboola.candidate.itsadok.calc;

import com.taboola.candidate.itsadok.calc.grammar.Statement;
import com.taboola.candidate.itsadok.calc.grammar.Sum;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ProgramState {
    private Map<String, Integer> vars = new TreeMap<>();

    public int get(String varName) {
        return vars.get(varName);
    }

    public void set(String varName, int value) {
        vars.put(varName, value);
    }

    public boolean varExists(String varName) {
        return vars.containsKey(varName);
    }

    public void executeLine(String line) {
        new Statement(line, this);
    }

    @Override
    public String toString() {
        return "(" +
                String.join(
                        ",",
                        vars.entrySet()
                                .stream()
                                .map(entry -> entry.getKey() + "=" + entry.getValue())
                                .collect(Collectors.toList())
                ) +
                ")";
    }
}
