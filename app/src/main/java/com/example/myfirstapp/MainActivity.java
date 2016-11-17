package com.example.myfirstapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculateNet(View view) {
        EditText IP1_1 = (EditText) findViewById(R.id.IP1_1);
        EditText IP1_2 = (EditText) findViewById(R.id.IP1_2);
        EditText IP1_3 = (EditText) findViewById(R.id.IP1_3);
        EditText IP1_4 = (EditText) findViewById(R.id.IP1_4);
        EditText IP1_mask = (EditText) findViewById(R.id.IP1mask);
        EditText IP2_1 = (EditText) findViewById(R.id.IP2_1);
        EditText IP2_2 = (EditText) findViewById(R.id.IP2_2);
        EditText IP2_3 = (EditText) findViewById(R.id.IP2_3);
        EditText IP2_4 = (EditText) findViewById(R.id.IP2_4);
        EditText IP2_mask = (EditText) findViewById(R.id.IP2mask);

        if (inputOkey()) {
            TextView textout = (TextView) findViewById(R.id.result1);
            TextView textout2 = (TextView) findViewById(R.id.result2);
            TextView result = (TextView) findViewById(R.id.result3);
            String network;
            String network2;

            if (IPokey(IP1_1, IP1_2, IP1_3, IP1_4) && maskOkey(IP1_mask) && IPokey(IP2_1, IP2_2, IP2_3, IP2_4) && maskOkey(IP2_mask)) {

                network = calculateNetwork(IP1_1, IP1_2, IP1_3, IP1_4, IP1_mask);
                network2 = calculateNetwork(IP2_1, IP2_2, IP2_3, IP2_4, IP2_mask);

                network = binToDec(network);
                network2 = binToDec(network2);

                textout.setText(network);
                textout2.setText(network2);

                if(network.equals(network2)){
                    result.setText("IP1 and IP2 are in the same network.");
                }
                else{
                    result.setText("IP1 and IP2 are NOT in the same network.");
                }
            }
            else {
                showDialog("Error", "Your input is not correct, try again");
            }
        }
        else{
            showDialog("Error", "Your input is not correct, try again");
        }
}

    //Calculate what network the IP address belongs to.
    public String calculateNetwork(EditText IP1, EditText IP2, EditText IP3, EditText IP4, EditText IPmask){

        //Convert each byte to binary, add leading zeros to get 8 digits.
        String firstByte = String.format("%8s", Integer.toBinaryString(Integer.parseInt(IP1.getText().toString()))).replace(" ", "0");
        String secondByte = String.format("%8s", Integer.toBinaryString(Integer.parseInt(IP2.getText().toString()))).replace(" ", "0");
        String thirdByte = String.format("%8s", Integer.toBinaryString(Integer.parseInt(IP3.getText().toString()))).replace(" ", "0");
        String fourthByte = String.format("%8s", Integer.toBinaryString(Integer.parseInt(IP4.getText().toString()))).replace(" ", "0");

        int mask = Integer.parseInt(IPmask.getText().toString());
        String netBinary = new String();
        char[] charArray = new char[32];


        for(int i = 0; i < 8; i++){
            charArray[i] = firstByte.charAt(i);
        }

        for(int i = 0; i < 8; i++){
            charArray[8+i] = secondByte.charAt(i);
        }

        for(int i = 0; i < 8; i++){
            charArray[16+i] = thirdByte.charAt(i);
        }

        for(int i = 0; i < 8; i++){
            charArray[24+i] = fourthByte.charAt(i);
        }

        //Put zeros in the host part of the address
        for(int i = mask; i < 32; i++){
            charArray[i] = '0';
        }
        for(int i = 0; i < 32; i++){
            netBinary += charArray[i];
        }
        return netBinary;
    }


    //Check so that the IP-bytes are in range
    public boolean IPokey(EditText IP1,EditText IP2, EditText IP3, EditText IP4){
        if (Integer.parseInt(IP1.getText().toString()) > 255 ||
                Integer.parseInt(IP2.getText().toString()) > 255 ||
                Integer.parseInt(IP3.getText().toString()) > 255 ||
                Integer.parseInt(IP4.getText().toString()) > 255 ||
                Integer.parseInt(IP1.getText().toString()) < 0 ||
                Integer.parseInt(IP2.getText().toString()) < 0 ||
                Integer.parseInt(IP3.getText().toString()) < 0 ||
                Integer.parseInt(IP4.getText().toString()) < 0){

            showDialog("Unacceptable number entered", "All IP numbers must be from 0 to 255!");
            return false;
        }
        else{
            return true;
        }
    }

    //Showing an alertbox with title and message
    public void showDialog(String title, String message){
        AlertDialog.Builder a1 = new AlertDialog.Builder(MainActivity.this);
        // Setting Dialog Title
        a1.setTitle(title);

        // Setting Dialog Message
        a1.setMessage(message);

        a1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int button1) {
                        // if this button is clicked, close
                        // current activity
                        dialog.cancel();
                    }
                });
        // Showing Alert Message
        AlertDialog alertDialog = a1.create();
        a1.show();
    }

    //Check if the mask is in range
    public boolean maskOkey(EditText IPmask){
        if(Integer.parseInt(IPmask.getText().toString()) < 32 && Integer.parseInt(IPmask.getText().toString()) >= 0){
            return true;
        }
        else{
            showDialog("Unacceptable IP mask entered","The mask must be in the range 1 to 32");
            return false;
        }
    }

    //Check that all input is ints
    public boolean inputOkey(){
        EditText IP1_1 = (EditText) findViewById(R.id.IP1_1);
        EditText IP1_2 = (EditText) findViewById(R.id.IP1_2);
        EditText IP1_3 = (EditText) findViewById(R.id.IP1_3);
        EditText IP1_4 = (EditText) findViewById(R.id.IP1_4);
        EditText IP1_mask = (EditText) findViewById(R.id.IP1mask);
        EditText IP2_1 = (EditText) findViewById(R.id.IP2_1);
        EditText IP2_2 = (EditText) findViewById(R.id.IP2_2);
        EditText IP2_3 = (EditText) findViewById(R.id.IP2_3);
        EditText IP2_4 = (EditText) findViewById(R.id.IP2_4);
        EditText IP2_mask = (EditText) findViewById(R.id.IP2mask);

        try{
            Integer.parseInt(IP1_1.getText().toString());
            Integer.parseInt(IP1_2.getText().toString());
            Integer.parseInt(IP1_3.getText().toString());
            Integer.parseInt(IP1_4.getText().toString());
            Integer.parseInt(IP1_mask.getText().toString());
            Integer.parseInt(IP2_1.getText().toString());
            Integer.parseInt(IP2_2.getText().toString());
            Integer.parseInt(IP2_3.getText().toString());
            Integer.parseInt(IP2_4.getText().toString());
            Integer.parseInt(IP2_mask.getText().toString());
            InputMethodManager imm = (InputMethodManager)getSystemService(
                    Context.INPUT_METHOD_SERVICE);
        }
        catch(NumberFormatException e)
        {
            showDialog("Input error", "Number missing or invalid number!");
            return false;
        }
        return true;
    }

    public String binToDec(String network){
        int b1 = Integer.parseInt(network.substring(0,8), 2);
        int b2 = Integer.parseInt(network.substring(8,16), 2);
        int b3 = Integer.parseInt(network.substring(16,24), 2);
        int b4 = Integer.parseInt(network.substring(24,32), 2);
        String newNet = String.valueOf(b1) + "." + String.valueOf(b2) + "." + String.valueOf(b3) +
                "." + String.valueOf(b4);
        return newNet;
    }
}


