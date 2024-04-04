package com.example.demo.dictionary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;

public interface IReader {
    BufferedReader getBufferedReader(String filePath) throws FileNotFoundException;
}
