package com.testproject.vkinfo.utils;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

public class NetworkUtils {
    private static final String VK_API_BASE_URL = "https://api.vk.com";
    private static final String VK_USERS_GET = "/method/users.get";
    private static final String PARAM_USER_ID = "user_ids";
    private static final String PARAM_VERSION = "v";

    public static URL generateURL(String userID) throws MalformedURLException {


        Uri builtUri = Uri.parse(VK_API_BASE_URL + VK_USERS_GET).buildUpon().appendQueryParameter(PARAM_USER_ID, userID)
                .appendQueryParameter(PARAM_VERSION, "5.8").build();

//        URL url = null;
        URL url = new URL(builtUri.toString());

        return url;
    }
}
