package org.ababup1192.webapibasic;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity implements LoaderManager.LoaderCallbacks<String> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Loader の起動
        getSupportLoaderManager().initLoader(0, null, this);
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

    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle) {
        return new WebAPILoader(this, "http://date.jsontest.com");
    }

    @Override
    public void onLoadFinished(Loader<String> longLoader, String data) {
        TextView resultText = (TextView) findViewById(R.id.text_result);
        try {
            JSONObject json = new JSONObject(data);
            String date = json.getString("date");
            String time = json.getString("time");
            resultText.setText(date + ":" + time);
        } catch (JSONException e) {
            resultText.setText("Dateの取得に失敗しました。");
            e.printStackTrace();
        }

    }

    @Override
    public void onLoaderReset(Loader<String> longLoader) {
    }

}
