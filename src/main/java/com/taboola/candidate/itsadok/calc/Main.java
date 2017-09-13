package com.taboola.candidate.itsadok.calc;

import com.sun.org.apache.bcel.internal.classfile.Unknown;
import com.taboola.candidate.itsadok.calc.grammar.Statement;
import com.taboola.candidate.itsadok.calc.grammar.Sum;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Main {

    private static void runInteractive(Console console) {
        ProgramState state = new ProgramState();
        while(true) {
            String line = console.readLine(">>> ");
            if (line == null) {
                console.printf("Goodbye!\n");
                return;
            }

            if (line.trim().isEmpty()) continue;

            try {
                Statement statement = new Statement(line, state);
                if (!statement.match) {
                    Sum sum = new Sum(line, 0, state);
                    if (sum.match) {
                        console.printf("%d\n", sum.value);
                    } else {
                        console.printf("%s\n", statement.error);
                    }
                }
                console.printf("%s\n", state);
            } catch (UnknownVariableError e) {
                console.printf("UnknownVariableError: %s\n", e.getMessage());
            }
        }
    }

    private static void runNonInteractive() throws IOException {
        ProgramState state = new ProgramState();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        while(reader.ready()) {
            String line = reader.readLine();
            try {
                Statement statement = new Statement(line, state);
                if (!statement.match) {
                    System.err.println(statement.error);
                }
            } catch (UnknownVariableError e) {
                System.err.println("UnknownVariableErorr: " + e.getMessage());
            }
        }
        System.out.println(state);
    }

    public static void main(String[] args) throws IOException {
        Console console = System.console();
        if (console == null) {
            runNonInteractive();
        } else {
            runInteractive(console);
        }
    }
}