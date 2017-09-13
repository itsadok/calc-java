package com.taboola.candidate.itsadok.calc.grammar;

class NumberOperand {
    boolean match;
    int value;
    int end;

    NumberOperand(String line, int start) {
        int i = start;
        int sign = 1;
        for (; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '-') {
                sign *= -1;
            } else if (c != '+' && c != ' ') {
                break;
            }
        }

        int num = 0;
        for (; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c >= '0' && c <= '9') {
                match = true;
                num *= 10;
                num += c - '0';
            } else {
                break;
            }
        }
        value = sign * num;
        end = i;
    }
}
