package com.example.demo.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MeaningDTO {
    public String partOfSpeech;
    public List<DefinitionDTO> definitions;
}
