package com.example.crimetracker;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import com.example.crimetracker.databinding.ActivityNewBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NewActivity extends AppCompatActivity {

    String News_Url;
    private ActivityNewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        News_Url = "https://newsapi.org/v2/top-headlines?sources=news-24&apiKey=0930eb567607412f8605463cf5ead22f";

        new MainActivity.AsyncHttpTask().execute(News_Url);
    }

    public class AsyncHttpTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection= null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                String response = streamToString(urlConnection.getInputStream());
                parseResult(response);
                return result;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    String streamToString(InputStream stream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        String data;
        String result = "";

        while((data = bufferedReader.readLine()) != null){
            result += data;
        }
        if(null != stream){
            stream.close();
        }

        return result;
    }

    private void parseResult(String result){
        JSONObject response = null;
        try {
            response = new JSONObject(result);
            JSONArray articles = response.optJSONArray("articles");

            for(int i = 0; i < articles.length(); i++){
                JSONObject article = articles.optJSONObject(i);
                String title = article.optString("title");
                Log.i("Titles", title);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}