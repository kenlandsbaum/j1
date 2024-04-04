package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class DemoApplication {
	@Bean
	public HttpClient httpClient() {
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30 * 1000).build();
		return HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
	}
	public static void main(String[] args) {SpringApplication.run(DemoApplication.class, args);}
}




