package com.example.demo.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefinitionDTOTest {

    @Test
    public void smokeTest() {
        DefinitionDTO def = new DefinitionDTO();
        def.definition = "definition";
        assertEquals("definition", def.definition);
    }
}