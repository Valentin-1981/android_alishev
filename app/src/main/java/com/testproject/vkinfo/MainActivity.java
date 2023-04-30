package com.testproject.vkinfo;

import static com.testproject.vkinfo.utils.NetworkUtils.generateURL;
//import static com.testproject.vkinfo.utils.NetworkUtils;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText searchField;
    private Button searchButton;
    private TextView result;

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
                URL generatedURL;
                try {
                    generatedURL = generateURL(searchField.getText().toString());
                    result.setText(generatedURL.toString());
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }



            }

        };
        searchButton.setOnClickListener(onClickListener);
    }
}