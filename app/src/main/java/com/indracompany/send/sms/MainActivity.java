package com.indracompany.send.sms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonSend;
    private TextView textNumber, textMessage;
    String message, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS,  Manifest.permission.READ_SMS},  PackageManager.PERMISSION_GRANTED);
        //
        buttonSend = findViewById(R.id.button_send);
        textNumber = findViewById(R.id.textNumber);
        textMessage = findViewById(R.id.textMessage);
        //
        message = "Seu código para a assinatura digital é: 123456";
        number = "(650)5551212";
        textNumber.setText(number);
        textMessage.setText(message);
        //
        buttonSend.setOnClickListener(view -> {
            if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                sendMessage();
            } else {
                Toast.makeText(getApplicationContext(), "Sem Permissão", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void sendMessage() {
        try {

            //String number = phone.getText().toString().trim();
            if(!number.isEmpty()) {
                SmsManager mySmsManager = SmsManager.getDefault();
                mySmsManager.sendTextMessage(number,null ,message, null, null);
                Toast.makeText(getApplicationContext(), "SMS enviado!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Escreva o número", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Algo deu errado!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS,  Manifest.permission.READ_SMS},  PackageManager.PERMISSION_GRANTED);
    }
}