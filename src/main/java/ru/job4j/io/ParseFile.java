package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String getContent(Predicate<Character> filter) throws IOException {
        StringBuilder output = new StringBuilder();
        try (InputStream input = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            char charData;
            while ((data = input.read()) > 0) {
                charData = (char) data;
                if (filter.test(charData)) {
                    output.append(charData);
                }
            }
        }
        return output.toString();
    }
}
