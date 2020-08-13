package org.ad.request.handler;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpRequestHandlerImpl implements RequestHandler {

    private final static int UNKNOWN_CITY_NAME_CODE = 404;
    private final static OkHttpClient httpClient = new OkHttpClient();

    public String getResponse(String requestStr) throws IOException {
        requestStr = requestStr.replace(" ", "%20");
        Request request = new Request.Builder()
                .url(requestStr)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) {
                if(response.code() == UNKNOWN_CITY_NAME_CODE) {
                    throw new PageNotFoundException(String.format("Request %s finished with code %d",
                            requestStr, UNKNOWN_CITY_NAME_CODE));
                } else {
                    throw new IOException("Unexpected response code " + response);
                }
            }

            return response.body().string();
        }

    }
}