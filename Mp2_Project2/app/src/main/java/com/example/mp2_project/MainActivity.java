package com.example.mp2_project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button go;
    TextView trail;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editText);
        final Date[] c = {Calendar.getInstance().getTime()};
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        final String formattedDate = df.format(c[0]);
        go=findViewById(R.id.button);
            go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    trail = findViewById(R.id.Data);
                    Date date1=null,date2 = null;
                    try {
                        date1=new SimpleDateFormat("yyyy-MM-dd").parse(formattedDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        date2=new SimpleDateFormat("yyyy-MM-dd").parse(editText.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if(date2.before(date1) || date1.equals(date2))
                    {
                    String cad = null, usd = null, inr = null, aud = null, chf = null, cny = null, eur = null, gbp = null;
                    String currency_data = "https://api.exchangeratesapi.io/" + editText.getText().toString();//year1+"-"+month1+"-"+day1;
                    try {
                        String data = new Asyncdata().execute(currency_data).get();
                        JSONObject mainObj = new JSONObject(data);
                        JSONObject currdat = mainObj.getJSONObject("rates");
                        eur = currdat.getString("RUB");
                        aud = currdat.getString("AUD");
                        chf = currdat.getString("CHF");
                        cny = currdat.getString("CNY");
                        gbp = currdat.getString("GBP");
                        cad = currdat.getString("CAD");
                        usd = currdat.getString("USD");
                        inr = currdat.getString("INR");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    trail.setText("Base EUR \nEUR : 1\nRUB : " + eur + "\nCAD : " + cad + "\nINR : " + inr + "\nUSD : " + usd + "\nAUD : " + aud + "\nCHF : " + chf + "\nCNY : " + cny + "\nGBP : " + gbp);
                }
                    else {
                        Toast.makeText(getApplicationContext(),"Please enter valid date",Toast.LENGTH_LONG).show();
                    }
            }
            });

        }

}



