package com.example.demo.dictionary;

import com.example.demo.dto.DictionaryApiDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.List;

@Service
public class DictionaryService implements IDictionaryService {

    private final IRestClient client;
    private final IReader reader;
    Logger logger = LoggerFactory.getLogger(DictionaryService.class);
    @Value("${dictionary.api}")
    private String dictionaryApi;
    @Value("${dictionary.source}")
    private String dictionarySource;

    public DictionaryService(@Autowired IReader reader, @Autowired IRestClient client) {
        this.reader = reader;
        this.client = client;
    }
    public DictionaryApiDTO getDictionaryWord(int randomNumber) {
        String word = this.getWord(randomNumber);
        DictionaryApiDTO def = this.getDefinition(word);
        while (def == null) {
            randomNumber += 1;
            word = this.getWord(randomNumber);
            def = this.getDefinition(word);
        }
        return def;
    }

    private String getWord(int randomNumber) {
        try {
            int i = 0;
            String line;
            BufferedReader reader = this.reader.getBufferedReader(dictionarySource);
            while((line = reader.readLine()) != null) {
                i += 1;
                if (i == randomNumber) {
                    return line;
                }
            }
            logger.warn("no word found");
            return "";
        } catch (IOException ex) {
            logger.warn("epic fail:" + ex.getMessage());
            return "";
        }
    }

    public DictionaryApiDTO getDefinition(String word) {
        try {
            String uri = dictionaryApi + word;
            System.out.println("uri in test: " + uri);
            String content = this.client.get(uri);
            return this.parseResponse(content);
        } catch (Exception ex) {
            logger.warn("could not parse from api because api sucks:" + ex.getMessage());
            return null;
        }
    }
    private DictionaryApiDTO parseResponse(String responseBodyString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<DictionaryApiDTO> results = mapper
                .readValue(responseBodyString, new TypeReference<List<DictionaryApiDTO>>() {});
        return results.getFirst();
    }
}
