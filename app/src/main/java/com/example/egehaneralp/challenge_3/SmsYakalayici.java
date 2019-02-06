package com.example.egehaneralp.challenge_3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsYakalayici extends BroadcastReceiver {

    SmsManager sms=SmsManager.getDefault();


    @Override
    public void onReceive(Context context, Intent intent) { //SMS GELİNCE ÇALIŞIR

        Bundle b= intent.getExtras();

        Object [] pdusObj = (Object []) b.get("pdus");

        for(int i =0; i<pdusObj.length; i++){

            SmsMessage guncelMesaj = SmsMessage.createFromPdu((byte [])pdusObj[i]);

            String telNo = guncelMesaj.getDisplayOriginatingAddress();
            String mesaj = guncelMesaj.getDisplayMessageBody();

            Intent smsIntent = new Intent(context, MainActivity.class);
            smsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            smsIntent.putExtra("Gonderen", telNo);
            smsIntent.putExtra("Mesaj", mesaj);
            context.startActivity(smsIntent);


            Toast.makeText(context, "Tel No : "+ telNo + "\n"+"Mesaj : "+ mesaj, Toast.LENGTH_SHORT).show();

        }

    }
}
