package com.example.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText IP1 = (EditText) findViewById(R.id.IP1_1);
        EditText IP2 = (EditText) findViewById(R.id.IP1_2);
        String message1 = IP1.getText().toString();
        //String message2 = IP2.getText().toString();
        //Boolean smaller = message1;
        intent.putExtra(EXTRA_MESSAGE, message1);

        startActivity(intent);
    }

    public void calculateNet(View view){
        EditText firstByteIP1 = (EditText) findViewById(R.id.IP1_1);
        EditText result = (EditText) findViewById(R.id.result);
        result.setText(firstByteIP1 + "AA");
    }
}
