package org.ababup1192.webapibasic;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class WebAPILoader extends AsyncTaskLoader<String> {
    private String url;

    public WebAPILoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        HttpClient httpClient = new DefaultHttpClient();


        try {
            // curl http://ip.jsontest.com -I
            String responseBody = httpClient.execute(new HttpGet(url), // （1）

                    // UTF-8でデコードするためhandleResponseをオーバーライドする （2）
                    new ResponseHandler<String>() {

                        @Override
                        public String handleResponse(HttpResponse response)
                                throws ClientProtocolException, IOException {

                            // レスポンスコードが、
                            // HttpStatus.SC_OK（HTTP 200）の場合のみ、結果を返す
                            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                                return EntityUtils.toString(response.getEntity(), "UTF-8"); // （3）
                            }
                            return null;
                        }
                    });

            return responseBody;
        } catch (Exception e) {
            Log.e(this.getClass().getSimpleName(), e.getMessage());
        } finally {
            // 通信終了時は、接続を閉じる （4）
            httpClient.getConnectionManager().shutdown();
        }

        return null;
    }


}
