package com.example.demo.dictionary;

import java.io.IOException;

public interface IRestClient {
    public String get(String url) throws IOException;
}
