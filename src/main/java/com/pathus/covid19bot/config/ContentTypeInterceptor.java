package com.pathus.covid19bot.config;

import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class ContentTypeInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept (HttpRequest request, byte[] body, ClientHttpRequestExecution execution )
            throws IOException {
        request.getHeaders().setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return execution.execute(request, body);
    }
}