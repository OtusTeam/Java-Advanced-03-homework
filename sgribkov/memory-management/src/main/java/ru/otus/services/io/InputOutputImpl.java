package ru.otus.services.io;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@AllArgsConstructor
@Getter
@Setter
public class InputOutputImpl implements InputOutput {
    private final PrintStream out;
    private final Scanner in;

    public InputOutputImpl(PrintStream out, InputStream in) {
        this.out = out;
        this.in = new Scanner(in);
    }

    @Override
    public void out(String message) {
        out.println(message);
    }

    @Override
    public String readLn(String prompt) {
        out(prompt);
        return in.next();
    }

    @Override
    public void close(String prompt) {
        out(prompt);
        in.close();
    }
}
