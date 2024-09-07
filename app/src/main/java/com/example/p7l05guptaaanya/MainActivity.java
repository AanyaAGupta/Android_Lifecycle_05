package com.example.p7l05guptaaanya;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

    Button incrementButton;
    TextView greetingDisplay;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        incrementButton = findViewById(R.id.increment_button);
        greetingDisplay = findViewById(R.id.greeting_textview);
        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("incrementing: " + ++count);
                Log.i("incrementing", String.valueOf(count));
                greetingDisplay.setText(String.valueOf(count));
                }
            });




        //load SharedPrefences
        sharedPreferences = getSharedPreferences(TAG, MODE_PRIVATE);
        //instantiate editor
        editor = sharedPreferences.edit();
//        editor.clear();
//        editor.apply();
        //instantiate classes
        currentRun = new LifecycleData();
        currentRun.duration = "Current Run";
        //get LifecycleData from sharedPrefrences as String
        String lifecycleDataAsString = sharedPreferences.getString("lifetime", "");
        //Instantiate new LifecycleData if empty string
        //else convert JSON to LifecycleData object
        if (lifecycleDataAsString.equals("")) {
            lifeTime = new LifecycleData();
            lifeTime.duration = "Lifetime Data";
        } else {
            lifeTime = LifecycleData.parseJSON(lifecycleDataAsString);
        }
        //instantiate TextViews
        currentRunTV = findViewById(R.id.current);
        lifeTimeTV = findViewById(R.id.lifetime);
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);
    }
        //display data on TextViews
        public void displayData(){
            currentRunTV.setText(currentRun.toString());
            lifeTimeTV.setText(lifeTime.toString());
        }
        //convert lifetime to String and store in SharedPreferences
        public void storeData(){
            editor.putString("lifetime",lifeTime.toJSON()).apply();
        }

    public void updateCount(String currentEnclosingMethod){
        //pass name to LifecycleData to update count
        lifeTime.updateEvent(currentEnclosingMethod);
        currentRun.updateEvent(currentEnclosingMethod);
        displayData();
        storeData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);
    }
    @Override
    protected void onResume(){
        super.onResume();
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);
    }
    @Override
    protected void onPause(){
        super.onPause();
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);
    }
    @Override
    protected void onStop(){
        super.onStop();
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);
    }


    public void decrement(View view) {
        System.out.println("decrementing: " + --count);
        greetingDisplay.setText(String.valueOf(count));
    }



}