package org.ad.request.handler;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpRequestHandlerImpl implements RequestHandler {

    private final static OkHttpClient httpClient = new OkHttpClient();

    public String getResponse(String requestStr) throws IOException {
        Request request = new Request.Builder()
                .url(requestStr)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            return response.body().string();
        }

    }
}