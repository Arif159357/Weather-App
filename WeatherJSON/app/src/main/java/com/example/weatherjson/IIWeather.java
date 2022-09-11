package com.example.weatherjson;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class IIWeather extends AsyncTask<String,Void,String> {


    @Override
    protected String doInBackground(String... urls) {
        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int data = reader.read();
            while (data != -1) {
                char current = (char) data;
                result += current;
                data = reader.read();
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {


            JSONObject jsonObject = new JSONObject(s);
            String weatherInfo = jsonObject.getString("weather");

            JSONArray arr = new JSONArray(weatherInfo);

            String message = "";

            for (int i = 0; i < arr.length(); i++) {
                JSONObject jsonPart = arr.getJSONObject(i);
                String main = jsonPart.getString("main");

                String description = jsonPart.getString("description");
                if (!main.equals("") && !description.equals("")) {
                    message += main + ": " + description + "\n";
                }

            }
            if (!message.equals("")) {
                MainActivity.textView6.setText(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}