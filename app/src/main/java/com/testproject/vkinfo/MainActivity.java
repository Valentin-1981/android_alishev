package com.testproject.vkinfo;

import static com.testproject.vkinfo.utils.NetworkUtils.generateURL;
import static com.testproject.vkinfo.utils.NetworkUtils.getResponseFromURL;
//import static com.testproject.vkinfo.utils.NetworkUtils;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText searchField;
    private Button searchButton;
    private TextView result;

    class VKQueryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            String response = null;
            try {
//                    generatedURL = generateURL(searchField.getText().toString());
                response = getResponseFromURL(urls[0]);
//                    result.setText(generatedURL.toString());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String response){
            String firstName = null;
            String lastName = null;

            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                JSONObject userInfo = jsonArray.getJSONObject(0);

                firstName = userInfo.getString("first_name");
                lastName = userInfo.getString("last_name");
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            String resultingString = "Имя: " + firstName + "\n" + "Фамилия: " + lastName;
            result.setText(resultingString);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchField = findViewById(R.id.et_search_field);
        searchButton = findViewById(R.id.b_search_vk);
        result = findViewById(R.id.tv_result);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL generatedURL = null;
                try {
                    generatedURL = generateURL(searchField.getText().toString());
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                new VKQueryTask().execute(generatedURL);
            }
        };
        searchButton.setOnClickListener(onClickListener);
    }
}

















