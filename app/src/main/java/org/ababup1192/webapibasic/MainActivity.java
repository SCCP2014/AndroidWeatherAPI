package org.ababup1192.webapibasic;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.ababup1192.webapibasic.weather.WeatherInfo;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends ActionBarActivity {

    private TextView weatherText;
    private TextView tempText;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "http://api.openweathermap.org/data/2.5/weather?q=AizuWakamatsu,jp";

        weatherText = (TextView) findViewById(R.id.text_weather);
        tempText = (TextView) findViewById(R.id.text_temp);

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Json取得処理
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            WeatherInfo weatherInfo = mapper.readValue(response.toString(), WeatherInfo.class);
                            weatherText.setText("天気:" + weatherInfo.getWeather());
                            tempText.setText("気温:" + weatherInfo.getTemp());
                        } catch (IOException e) {
                            weatherText.setText("天気データの取得に失敗しました。");
                            Log.e("MainActivity", e.getMessage());
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // エラー表示など
                        weatherText.setText("天気データの取得に失敗しました。");
                    }
                }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
