package com.example.demo.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DictionaryApiDTO {
    public String word;

    public List<MeaningDTO> meanings;

}
