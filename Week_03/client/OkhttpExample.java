package client;

import okhttp3.*;

import java.io.IOException;

public class OkhttpExample {
    private final OkHttpClient httpClient = new OkHttpClient();

    public static void main(String[] args) {
        OkhttpExample obj = new OkhttpExample();
        obj.sendGet();
    }

    private void sendGet() {
        Request request = new Request.Builder()
                .url("http://localhost:8808/")
                .addHeader("1", "1")
                .build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    Headers requestHeads = response.request().headers();
                    for (int i = 0; i < requestHeads.size(); i++) {
                        System.out.println(requestHeads.name(i) + ": "+ requestHeads.value(i));
                    }
                    // Get response headers
                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    // Get response body
                    System.out.println(responseBody.string());
                }
            }
        });
    }
}
