package com.example.android.quakereport;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by NeLk on 11/02/2017.
 */

public class HttpUtils {

    public static final String LOG_TAG = HttpUtils.class.getSimpleName();

    public static String makeHttpRequest(String requestURL) throws IOException {
        String jsonResponse = "";

        if (TextUtils.isEmpty(requestURL)) {
            return jsonResponse;
        }

        //TODO : Handle MalformedURLException
        URL url = createURL(requestURL);

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Response was a : " + urlConnection.getResponseCode());
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
                Log.e(LOG_TAG, "More info: " + jsonResponse);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem with IOException" + e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    @NonNull
    private static URL createURL(String requestURL) throws MalformedURLException {
        URL url = null;
        try {
            url = new URL(requestURL);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building URL "+ e);
        }
        return url;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder response = new StringBuilder();

        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                response.append(line);
                line = reader.readLine();
            }
        }

        return response.toString();
    }
}
