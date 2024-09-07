package com.example.p7l05guptaaanya;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    String TAG = "com.example.p7l05guptaaanya.sharedpreferences";
    LifecycleData currentRun, lifeTime;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView currentRunTV, lifeTimeTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //load SharedPrefences
        sharedPreferences = getSharedPreferences(TAG, MODE_PRIVATE);
        //instantiate editor
        editor = sharedPreferences.edit();
        //instantiate classes
        currentRun = new LifecycleData();
        currentRun.duration="Current Run";
        //get LifecycleData from sharedPrefrences as String
        String lifecycleDataAsString = sharedPreferences.getString("lifetime", "");
        //Instantiate new LifecycleData if empty string
        //else convert JSON to LifecycleData object
        if (lifecycleDataAsString.equals("")){
            lifeTime = new LifecycleData();
            lifeTime.duration = "Lifetime Data";
        } else {
            lifeTime = LifecycleData.parseJSON(lifecycleDataAsString);
        }
        //instantiate TextViews
        currentRunTV = findViewById(R.id.current);
        lifeTimeTV = findViewById(R.id.lifetime);
        //display data on TextViews
        currentRunTV.setText(currentRun.toString());
        lifeTimeTV.setText(lifeTime.toString());

    }
}