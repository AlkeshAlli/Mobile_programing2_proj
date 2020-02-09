package com.example.mp2_project;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class Converter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        final String list[] = {"CAD","USD","INR","EUR","CNF","GBP","HKD","CNY"};
        final String[] spin1 = new String[2];
        final String[] val1 = new String[2];
        Spinner mySpinner1 = (Spinner)  findViewById(R.id.spinner1);
        Spinner mySpinner2 = (Spinner)  findViewById(R.id.spinner2);
        ArrayAdapter<String> myAdapter;
        myAdapter = new ArrayAdapter<String>(Converter.this,android.R.layout.simple_list_item_1,list);
        myAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mySpinner1.setAdapter(myAdapter);
        ArrayAdapter<String> myAdapter2;
        myAdapter2 = new ArrayAdapter<String>(Converter.this,android.R.layout.simple_expandable_list_item_1, list);
        myAdapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mySpinner2.setAdapter(myAdapter2);
        mySpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spin1[0] = list[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mySpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spin1[1] = list[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        final Date[] c = {Calendar.getInstance().getTime()};
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        final String formattedDate = df.format(c[0]);
        final TextView data = findViewById(R.id.textView5);
        Button convert = findViewById(R.id.button2);
        final EditText value_curency = findViewById(R.id.editText2);
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currency_data="https://api.exchangeratesapi.io/"+formattedDate;
                try{
                    String data=new Asyncdata().execute(currency_data).get();
                    JSONObject mainObj = new JSONObject(data);
                    JSONObject currdat=mainObj.getJSONObject("rates");
                    val1[0] = currdat.getString(spin1[0]);
                    val1[1] = currdat.getString(spin1[1]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Double value_cur= Double.parseDouble(value_curency.getText().toString());
                Double value1=Double.parseDouble(val1[0]);
                Double value2=Double.parseDouble(val1[1]);
                Double result = value_cur*(value2/value1);
                String res = result.toString();
                data.setText(res);
            }
        });
    }
}
