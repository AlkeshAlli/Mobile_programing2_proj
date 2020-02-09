package com.example.mp2_project;

import android.os.AsyncTask;

public  class Asyncdata extends AsyncTask<String,Void,String>{


    protected String doInBackground(String... strings) {
        String jsonurl=strings[0];
        Httphandler hp=new Httphandler();
        String json=hp.makeServiceCall(jsonurl);
        return json;
    }
}
