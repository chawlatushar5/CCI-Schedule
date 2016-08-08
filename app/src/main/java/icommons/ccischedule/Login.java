package icommons.ccischedule;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Tushar on 8/5/16.
 */
public class Login extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);







    }
    public class getshifts extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader bufferedReader = null;

            try {
                URL qurl= new URL(params[0]);
                connection = (HttpURLConnection) qurl.openConnection();
                connection.setRequestProperty("W-Key", getString(R.string.api_key));
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                connection.setUseCaches(false);
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

}
/*
 protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("W-Token", "a3330564b51821a44d4ea41019222eb52e0c9769");
                urlConnection.connect();
                InputStream stream = urlConnection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line="";
                while ((line=reader.readLine())!=null){
                    buffer.append(line);
                }

                String finalJson= buffer.toString();





                return finalJson;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } //catch (JSONException e) {
            //e.printStackTrace()}
            finally {
                if (urlConnection!=null) {
                    urlConnection.disconnect();
                }
                try {
                    if (reader!=null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
 */