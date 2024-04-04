package com.example.demo.dictionary;

import com.example.demo.dto.DictionaryApiDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations="test.properties")
class DictionaryServiceTest {

    private static class TestRest implements IRestClient {
        private final boolean shouldFail;
        TestRest(boolean shouldFail) {
            this.shouldFail = shouldFail;
        }

        public String get(String url) throws IOException {
            String mockGoodResponse = "[{\"word\":\"thing\",\"meanings\":[{\"partOfSpeech\":\"noun\",\"definitions\":[{\"definition\":\"A thing.\"}]}]}]";
            if (this.shouldFail) {
                return "{\"message\":\"bad\"}";
            }
            return mockGoodResponse;
        }
    }
    private static class TestReader implements IReader {
        private final boolean shouldFail;
        public TestReader(boolean shouldFail) {
            this.shouldFail = shouldFail;
        }
        public BufferedReader getBufferedReader(String filePath) throws FileNotFoundException {
            StringReader r = new StringReader("thing\n");
            return new BufferedReader(r);
        }
    }


    @Test
    void getDictionaryWord() {
        IReader tr = new TestReader(false);
        IRestClient tc = new TestRest(false);
        DictionaryService dictionaryService = new DictionaryService(tr, tc);
        DictionaryApiDTO actual = dictionaryService.getDictionaryWord(0);
        assertEquals("thing", actual.word);
        assertEquals("A thing.", actual.meanings.getFirst().definitions.getFirst().definition);
    }

    @Test
    void getDefinitionSuccess() {
        IReader tr = new TestReader(false);
        IRestClient tc = new TestRest(false);
        DictionaryService dictionaryService = new DictionaryService(tr, tc);
        DictionaryApiDTO actual = dictionaryService.getDefinition("test");
        assertEquals("thing", actual.word);
        assertEquals(1, actual.meanings.size());
        assertEquals("A thing.", actual.meanings.getFirst().definitions.getFirst().definition);
    }
    @Test
    void getDefinitionFailure() {
        IReader tr = new TestReader(false);
        IRestClient tc = new TestRest(true);
        DictionaryService dictionaryService = new DictionaryService(tr, tc);
        DictionaryApiDTO actual = dictionaryService.getDefinition("test");
        assertNull(actual);
    }
}