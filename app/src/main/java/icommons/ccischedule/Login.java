package icommons.ccischedule;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

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
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

}
