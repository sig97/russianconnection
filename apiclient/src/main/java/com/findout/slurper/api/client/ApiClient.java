package com.findout.slurper.api.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.findout.slurper.api.client.domain.OrderData;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class ApiClient {

    private final String url;
    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;

    public ApiClient(String serverUrl) {
        this.url = serverUrl + "/api";
        httpClient = HttpClientBuilder.create().build();

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public String ping() {
        HttpGet httpGet = new HttpGet(url + "/ping");

        try(CloseableHttpResponse response = httpClient.execute(httpGet)) {
            String responseString = convertStreamToString(response.getEntity().getContent());
            return responseString;
        } catch (IOException e) {
            throw new RuntimeException("API call to " + this.url + "failed. ", e);
        }
    }

    public void sendOrder(OrderData order) {
        HttpPost httpPost = new HttpPost(url + "/order");
        HttpEntity entity = EntityBuilder.create()
                .setContentType(ContentType.APPLICATION_JSON)
                .setBinary(toBytes(order))
                .build();
        httpPost.setEntity(entity);

        try(CloseableHttpResponse response = httpClient.execute(httpPost)) {
            int status = response.getStatusLine().getStatusCode();
            if(status < 200 || status > 299) {
                throw new RuntimeException("Could not send order: HTTP " + status);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not send order to " + this.url + "failed. ", e);
        }
    }

    private byte[] toBytes(OrderData order) {
        try {
            return objectMapper.writeValueAsBytes(order);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to write order to slurper API: " + order, e);
        }
    }

    private static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
