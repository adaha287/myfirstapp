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
            if (IPokey(IP1_1, IP1_2, IP1_3, IP1_4) && maskOkey(IP1_mask)) {
                String network = calculateNetwork(IP1_1, IP1_2, IP1_3, IP1_4, IP1_mask);
                System.out.println("IPokey returned true");
                //String ipout = "IP1 is:" + IP1_1.getText().toString() + "." + IP1_2.getText().toString()  + "." + IP1_3.getText().toString()  + "." + IP1_4.getText().toString() ;
                textout.setText(network);
            } else {
                //write error message
                System.out.println("IPokey returned false");
            }
            if (IPokey(IP2_1, IP2_2, IP2_3, IP2_4) && maskOkey(IP2_mask)) {
                TextView textout2 = (TextView) findViewById(R.id.result2);
                String ipout2 = "IP2 is:" + IP2_1.getText().toString()  + "." + IP2_2.getText().toString()  + "." + IP2_3.getText().toString()  + "." + IP2_4.getText().toString() ;
                String network2 = calculateNetwork(IP2_1, IP2_2, IP2_3, IP2_4, IP2_mask);
                textout2.setText(ipout2);
            } else {
                //write error message
            }
        }
        else{
            System.out.println("You got some problem, try again!");
        }
    }

    public String calculateNetwork(EditText IP1, EditText IP2, EditText IP3, EditText IP4, EditText IPmask){
        int mask = Integer.parseInt(IPmask.getText().toString());
        String net = "";
        char[] charArray = new char[32];

        for(int i = 0; i < 8; i++){
            charArray[i] = IP1.getText().charAt(i);
        }
        for(int i = 0; i < 8; i++){
            charArray[8+i] = IP2.getText().charAt(i);
        }
        for(int i = 0; i < 8; i++){
            charArray[16+i] = IP3.getText().charAt(i);
        }
        for(int i = 0; i < 8; i++){
            charArray[24+i] = IP4.getText().charAt(i);
        }
        for(int i = mask; i < 32; i++){
            charArray[i] = 0;
        }
        for(int i = 0; i < 32; i++){
            net.concat(String.valueOf(charArray[i]));
        }
        return net;
    }

    public boolean IPokey(EditText IP1,EditText IP2, EditText IP3, EditText IP4){
       if (Integer.parseInt(IP1.getText().toString()) > 255 ||
                Integer.parseInt(IP2.getText().toString()) > 255 ||
                Integer.parseInt(IP3.getText().toString()) > 255 ||
                Integer.parseInt(IP4.getText().toString()) > 255 ||
                Integer.parseInt(IP1.getText().toString()) < 0 ||
                Integer.parseInt(IP2.getText().toString()) < 0 ||
                Integer.parseInt(IP3.getText().toString()) < 0 ||
                Integer.parseInt(IP4.getText().toString()) < 0){

           AlertDialog.Builder a1 = new AlertDialog.Builder(MainActivity.this);
           // Setting Dialog Title
           a1.setTitle("Unacceptable number entered");

           // Setting Dialog Message
           a1.setMessage("All IP numbers must be from 0 to 255!");

           a1.setPositiveButton("OK",
                   new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog,
                                           int button1) {
                           // if this button is clicked, close
                           // current activity
                           dialog.cancel();
                       }

                   });

           // Showing Alert Message
           AlertDialog alertDialog = a1.create();
           a1.show();

            return false;
        }
        else{
            return true;
        }
    }

    public boolean maskOkey(EditText IPmask){
        if(Integer.parseInt(IPmask.getText().toString()) < 32 && Integer.parseInt(IPmask.getText().toString()) >= 0){

            return true;
        }
        else{
            AlertDialog.Builder a1 = new AlertDialog.Builder(MainActivity.this);
            // Setting Dialog Title
            a1.setTitle("Unacceptable IP mask entered");

            // Setting Dialog Message
            a1.setMessage("The mask must be in the range 1 to 32");

            a1.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int button1) {
                            // if this button is clicked, close
                            // current activity
                            dialog.cancel();
                        }

                    });

            // Showing Alert Message
            AlertDialog alertDialog = a1.create();
            a1.show();
            return false;
        }
    }

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
            AlertDialog.Builder a1 = new AlertDialog.Builder(MainActivity.this);
            System.out.println("error");

            // Setting Dialog Title
            a1.setTitle("Alert Dialog");

            // Setting Dialog Message
            a1.setMessage("You have not entered a number or entered an invalid number!");

            a1.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int button1) {
                            // if this button is clicked, close
                            // current activity
                            dialog.cancel();
                        }

                    });

            // Showing Alert Message
            AlertDialog alertDialog = a1.create();
            a1.show();
            return false;
        }
        return true;
    }
}


