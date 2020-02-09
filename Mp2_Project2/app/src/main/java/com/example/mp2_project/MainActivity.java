package com.example.mp2_project;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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


    TextView displaydate;
    Button go;
    TextView trail;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displaydate=findViewById(R.id.textView4);
        displaydate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month +=1;
                String date= year+"-"+month+"-"+dayOfMonth;
                displaydate.setText(date);

            }
        };

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
                        date2=new SimpleDateFormat("yyyy-MM-dd").parse(displaydate.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if(date2.before(date1) || date1.equals(date2))
                    {
                    String cad = null, usd = null, inr = null, aud = null, chf = null, cny = null, eur = null, gbp = null;
                    String currency_data = "https://api.exchangeratesapi.io/" + displaydate.getText();//year1+"-"+month1+"-"+day1;
                    try {
                        String data = new Asyncdata().execute(currency_data).get();
                        JSONObject mainObj = new JSONObject(data);
                        JSONObject currdat = mainObj.getJSONObject("rates");
                        eur = currdat.getString("HKD");
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
                    trail.setText("Base EUR \nEUR : 1\nHKD : " + eur + "\nCAD : " + cad + "\nINR : " + inr + "\nUSD : " + usd + "\nAUD : " + aud + "\nCHF : " + chf + "\nCNY : " + cny + "\nGBP : " + gbp);
                }
                    else {
                        Toast.makeText(getApplicationContext(),"Please enter valid date",Toast.LENGTH_LONG).show();
                    }
            }
            });

        }

}



