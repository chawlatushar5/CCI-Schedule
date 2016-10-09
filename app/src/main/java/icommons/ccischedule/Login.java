package icommons.ccischedule;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.util.concurrent.ExecutionException;

/**
 * Created by Tushar on 8/5/16.
 */
public class Login extends Activity{

    private static final String TAG = "Login Activity";
    private static String token;
    private EditText email;
    private EditText password;
    private TextView text;
    Button login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        email = (EditText) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.input_password);
        login = (Button) findViewById(R.id.btn_login);
        text = (TextView) findViewById(R.id.link_signup);





        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!email.getText().toString().equals("") && !password.getText().toString().equals("")){

                    String bla = null;
                    try {
                        bla = new getshifts().execute("https://api.wheniwork.com/2/login").get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    JSONObject parentOBJ = null;
                    try {
                        parentOBJ=new JSONObject(bla);

                        token = parentOBJ.getString("token");
                        text.setText(token);


                        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        //prefs.edit().putString("Token",token).commit();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.putExtra("token", token);
                    Log.d(TAG, "Token"+ token );
                    if (text.getText().length()>35) {
                        startActivity(intent);
                    }




                    //startActivity(new Intent(Login.this, MainActivity.class));

                }
                else {
                    text.setText("Please fill the empty fields.");
                }



            }
        });




    }

    public String getusername(){
        return email.getText().toString();
    }
    public String password(){
        return password.getText().toString();
    }
    public class getshifts extends AsyncTask<String, String, String>{



        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader bufferedReader = null;
            String Token=null;

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
                connection.connect();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("username", getusername());
                jsonObject.put("password", password());

                StringBuffer sb =new StringBuffer();

                OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                out.write(jsonObject.toString());
                out.close();

                int HttpResult= connection.getResponseCode();
                if (HttpResult==HttpURLConnection.HTTP_OK){
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
                    String line=null;
                    while ((line=br.readLine())!=null){
                        sb.append(line);

                    }
                    br.close();
                    System.out.print(""+sb.toString());
                    Log.d(TAG, ""+sb.toString() );
                    Token= sb.toString();
                }
                else{
                    System.out.print(connection.getResponseMessage());
                    Log.d(TAG, ""+connection.getResponseMessage());
                    Token= "Wrong Username/Password!";

                }




            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return Token;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            text.setText(s);

            JSONObject parentOBJ = null;
            try {
                parentOBJ=new JSONObject(s);

                token = parentOBJ.getString("token");
                text.setText(token);

                //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                //prefs.edit().putString("Token",token).commit();


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
    public static String getToken(){
        return token;
    }

}
