package com.example.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

    EditText IP1_1;
    EditText IP1_2;
    EditText IP1_3;
    EditText IP1_4;
    EditText IP1_mask;
    EditText IP2_1;
    EditText IP2_2;
    EditText IP2_3;
    EditText IP2_4;
    EditText IP2_mask;

    public void calculateNet(View view){
        IP1_1 = (EditText) findViewById(R.id.IP1_1);
        IP1_2 = (EditText) findViewById(R.id.IP1_2);
        IP1_3 = (EditText) findViewById(R.id.IP1_3);
        IP1_4 = (EditText) findViewById(R.id.IP1_4);
        IP1_mask = (EditText) findViewById(R.id.IP1mask);
        IP2_1 = (EditText) findViewById(R.id.IP2_1);
        IP2_2 = (EditText) findViewById(R.id.IP2_2);
        IP2_3 = (EditText) findViewById(R.id.IP2_3);
        IP2_4 = (EditText) findViewById(R.id.IP2_4);
        IP2_mask = (EditText) findViewById(R.id.IP2mask);

        TextView textout = (TextView) findViewById(R.id.result1);
        
        String ipout = "IP1 is:" + IP1_1.getText() + "." + IP1_2.getText() + "." + IP1_3.getText() + "." + IP1_4.getText();
        textout.setText(ipout);
        TextView textout2 = (TextView) findViewById(R.id.result2);
        String ipout2 = "IP2 is:" + IP2_1.getText() + "." + IP2_2.getText() + "." + IP2_3.getText() + "." + IP2_4.getText();
        textout2.setText(ipout2);
    }
}
