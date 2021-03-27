package cli;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpHandler {
    private final static String base = "http://localhost:4567/";
    private final static OkHttpClient client = new OkHttpClient();
    private final Moshi moshi = new Moshi.Builder().build();

    public static Response getRequest(String url) throws IOException {
        return client.newCall(buildRequest(url)).execute();
    }

    private static Request buildRequest(String url) {
        return new Request.Builder()
                .url(base + url)
                .build();
    }
}
