package com.testproject.vkinfo.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NetworkUtils {
    private static final String VK_API_BASE_URL = "https://api.vk.com";
    private static final String VK_USERS_GET = "/method/users.get";
    private static final String PARAM_USER_ID = "user_ids";
    private static final String PARAM_VERSION = "v";

    private static final String ACCESS_TOKEN = "access_token";


    public static URL generateURL(String userIDs) throws MalformedURLException {


        Uri builtUri = Uri.parse(VK_API_BASE_URL + VK_USERS_GET).buildUpon().appendQueryParameter(PARAM_USER_ID, userIDs)
                .appendQueryParameter(PARAM_VERSION, "5.89")
                .appendQueryParameter(ACCESS_TOKEN, "9816b4849816b4849816b4845a9b056201998169816b484fc5956dc6555259d20f3f12e")
                .build();

//        URL url = null;
        URL url = new URL(builtUri.toString());

        return url;
    }

    public static String getResponseFromURL(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } catch(UnknownHostException e) {
            return null;
        } finally {
            urlConnection.disconnect();
        }
    }
}
