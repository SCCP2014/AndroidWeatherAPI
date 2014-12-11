package org.ababup1192.webapibasic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Weather {
    JSONObject json;

    public Weather(String jsonText) throws JSONException {
        this.json = new JSONObject(jsonText);
    }

    public String getWeather() throws JSONException {
        String weatherText = "";
        JSONArray weathers = json.getJSONArray("weather");
        for (int i = 0; i < weathers.length(); i++) {
            if (i == weathers.length() - 1) {
                weatherText += ((JSONObject) weathers.get(i)).getString("main");
            } else {
                weatherText += ((JSONObject) weathers.get(i)).getString("main") + " のち ";
            }
        }
        return weatherText;
    }

    public Double getTemp() {
        try {
            JSONObject mainInfo = json.getJSONObject("main");
            Double kelvin = mainInfo.getDouble("temp");
            // subtract 273.15 to convert to Celsius
            return kelvin - 273.15;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

}
