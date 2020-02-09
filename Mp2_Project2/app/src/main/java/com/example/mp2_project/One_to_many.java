package com.example.mp2_project;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class One_to_many extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_to_many);

        final String list[] = {"CAD","USD","INR","HKD","CHF","CNY","AUD","GBP"};
        final String[] spin1 = new String[2];
        final String[] val1 = new String[10];
        final String[] val_base = new String[1];
        Spinner mySpinner1 = (Spinner)  findViewById(R.id.spinner99);
        ArrayAdapter<String> myAdapter;
        myAdapter = new ArrayAdapter<String>(One_to_many.this,android.R.layout.simple_list_item_1,list);
        myAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mySpinner1.setAdapter(myAdapter);
        mySpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spin1[0] = list[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        final Date[] c = {Calendar.getInstance().getTime()};
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        final String formattedDate = df.format(c[0]);
        final TextView data = findViewById(R.id.list_of_data);
        Button convert = findViewById(R.id.button99);
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currency_data="https://api.exchangeratesapi.io/"+formattedDate;
                try{
                    String data=new Asyncdata().execute(currency_data).get();
                    JSONObject mainObj = new JSONObject(data);
                    JSONObject currdat=mainObj.getJSONObject("rates");
                    val_base[0] = currdat.getString(spin1[0]);
                    val1[0] = currdat.getString("CAD");
                    val1[1] = currdat.getString("USD");
                    val1[2] = currdat.getString("INR");
                    val1[3] = currdat.getString("HKD");
                    val1[4] = currdat.getString("CHF");
                    val1[5] = currdat.getString("CNY");
                    val1[6] = currdat.getString("AUD");
                    val1[7] = currdat.getString("GBP");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Double value1=Double.parseDouble(val_base[0]);
                StringBuffer sb=new StringBuffer();
                for(int i=0;i<list.length;i++)
                {
                    Double value2 = Double.parseDouble(val1[i]);
                    Double result = (value2/value1);
                    String res = list[i]+" : "+result.toString()+"\n";
                    sb.append(res);
                }
                data.setText(sb.toString());
            }
        });


    }
}
