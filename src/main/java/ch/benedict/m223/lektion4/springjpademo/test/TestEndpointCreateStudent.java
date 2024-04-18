package ch.benedict.m223.lektion4.springjpademo.test;

import java.io.IOException;

public class TestEndpointCreateStudent {

    public static void main(String[] args) throws IOException {
        RequestSender requestSender = new RequestSender();
        StudentRequestBody body = new StudentRequestBody("John", "Doe", "2000-02-22");
        String response = requestSender.sendRequest(body, "create_student");
        System.out.println("Response: " + response);
    }
}
