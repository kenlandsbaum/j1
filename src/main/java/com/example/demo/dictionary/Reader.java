package com.example.demo.dictionary;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

@Service
public class Reader implements IReader {
    public BufferedReader getBufferedReader(String filePath) throws FileNotFoundException {
        FileReader fileReader = new FileReader(filePath);
        return new BufferedReader(fileReader);
    }
}
