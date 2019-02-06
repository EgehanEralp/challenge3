package com.example.egehaneralp.challenge_3;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    String gonderen,mesaj;
    TextView textGonderen,textMesaj,textInfo,textKelime;

    SharedPreferences spre;
    SharedPreferences.Editor editor;
    String s,s2;
    String icerik;

    int maks=0;
    Kullanici [] kullanicilar = new Kullanici[100];
    static int index=0;
    Kelime kelimeler[]=new Kelime[100];
    static int index2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* textGonderen=findViewById(R.id.adres);
        textMesaj=findViewById(R.id.mesaj);  */

        spre=getPreferences(MODE_PRIVATE);
        editor=spre.edit();

        for(int i=0;i<100;i++){
            Kullanici k = new Kullanici("");
            kullanicilar[i]=k;
        }

        textKelime=findViewById(R.id.textKelime);
        textInfo=findViewById(R.id.textInfo);

        icerik =spre.getString("icerik","");

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            gonderen = extras.getString("Gonderen");
            mesaj = extras.getString("Mesaj");

            String splitKelimeler[]= mesaj.split(" ");

            for(int i=0;i<splitKelimeler.length;i++){

                Kelime k=new Kelime(splitKelimeler[i]);
                kelimeler[index2]=k;
                index2++;

                for(int j=1;j<splitKelimeler.length;j++){

                    if((k.ad).equals(splitKelimeler[j])){
                        k.count++;
                    }

                }


            }

            maks=kelimeler[0].count;
            for(int i=1;i<splitKelimeler.length;i++){

                if(kelimeler[i].count>maks){
                    maks=kelimeler[i].count;
                }

            }

            for(int i=0;i<splitKelimeler.length;i++){
                if(kelimeler[i].count == maks){
                    s2=kelimeler[i].ad;
                }
            }

            textKelime.setText("EN ÇOK ALINAN KELİME : "+s2 +" ("+maks+" Defa Tekrar Etmiş)");


            Kullanici k =new Kullanici(gonderen);
            kullanicilar[index]=k;
            for(int i=0;i<100;i++){
                if((kullanicilar[i].ad).equals(gonderen)){
                    kullanicilar[i].count++;
                }
            }
            //index++;
            s =textInfo.getText()+"Ad :"+ kullanicilar[index].ad +"\t"+ "Atılan mesaj :"+kullanicilar[index].count+"\n";
            index++;
            icerik += s;
            textInfo.setText(icerik);


            textGonderen=findViewById(R.id.adres);
            textMesaj=findViewById(R.id.mesaj);

            textGonderen.setText("Gonderen : "+ gonderen);
            textMesaj.setText("Mesaj : "+mesaj);
        }


        //editor.putString("icerik",icerik);
        editor.commit();
    }


}
