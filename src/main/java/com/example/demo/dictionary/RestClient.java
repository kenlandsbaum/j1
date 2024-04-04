package com.example.demo.dictionary;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RestClient implements IRestClient {
    @Autowired HttpClient client;

    public String get(String url) throws IOException {
        HttpResponse response = client.execute(new HttpGet(url));
        HttpEntity entity = response.getEntity();
        return EntityUtils.toString(entity);
    }
}
