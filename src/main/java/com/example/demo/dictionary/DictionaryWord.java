package com.example.demo.dictionary;

import java.util.List;

public class DictionaryWord {
    private String word;
    private List<String> definitions;

    public DictionaryWord() {}

    public DictionaryWord(String word) {
        this.word = word;
    }

    public DictionaryWord(String word, List<String> definitions) {
        this.word = word;
        this.definitions = definitions;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<String> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<String> definitions) {
        this.definitions = definitions;
    }
}
