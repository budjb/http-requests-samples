package com.budjb.httprequests.sample.jdk;

import com.budjb.httprequests.HttpClientFactory;
import com.budjb.httprequests.converter.EntityConverterManager;
import com.budjb.httprequests.filter.jackson.JacksonMapReader;
import com.budjb.httprequests.httpcomponents.client.HttpComponentsClientFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JdkApplication {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpClientFactory httpClientFactory = configureFactory(objectMapper);

        try {
            Map result = httpClientFactory.createHttpClient().get("https://reqres.in/api/users").getEntity(Map.class);
            System.out.println("HTTP response is:\n" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
        }
        catch (Exception e) {
            System.out.println("unexpected exception occurred");
            e.printStackTrace();
        }
    }

    private static HttpClientFactory configureFactory(ObjectMapper objectMapper) {
        EntityConverterManager entityConverterManager = new EntityConverterManager();
        entityConverterManager.add(new JacksonMapReader(objectMapper));
        return new HttpComponentsClientFactory(entityConverterManager);
    }
}
