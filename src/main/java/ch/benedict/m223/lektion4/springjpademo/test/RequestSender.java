package ch.benedict.m223.lektion4.springjpademo.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;

public class RequestSender {

    static OkHttpClient client;
    static ObjectMapper objectMapper;

    public RequestSender(){
        objectMapper = new ObjectMapper();
        client = new OkHttpClient.Builder()
                .build();
    }

    public String sendRequest(StudentRequestBody studentComp, String endpoint) throws IOException {
        client = new OkHttpClient.Builder()
                .build();

        // (1) create url
        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8080").newBuilder();
        urlBuilder.addPathSegment(endpoint);
        String url = urlBuilder.build().toString();

        // (2) create body
        String jacksonData = objectMapper.writeValueAsString(studentComp);
        System.out.println("jacksonData: " + jacksonData);
        RequestBody requestBody = RequestBody.create(jacksonData, MediaType.get("application/json"));


        // (3) build request
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        // (4) send request
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println("sent: " + request);

        // (5) check response
        return response.body().string();
    }

}
