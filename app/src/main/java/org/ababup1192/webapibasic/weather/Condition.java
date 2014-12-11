package org.ababup1192.webapibasic.weather;

public class Condition {
    public int humidity;
    public int pressure;
    public double temp;
    public double temp_max;
    public double temp_min;

    public double getTemp(){
        // subtract 273.15 to convert to Celsius
        return temp  - 273.15;
    }
}
