package com.example.demo.dictionary;

import com.example.demo.dto.DictionaryApiDTO;

public interface IDictionaryService {
    public DictionaryApiDTO getDictionaryWord(int n);
    public DictionaryApiDTO getDefinition(String word);
}
