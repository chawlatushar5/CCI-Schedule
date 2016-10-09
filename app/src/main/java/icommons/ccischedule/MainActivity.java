package icommons.ccischedule;
// By: Tushar Chawla
// Date : July 26, 2016

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
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
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    ArrayList<Users> java_users_array = new ArrayList<Users>();


    ArrayList<Shiftdata> java_shifts_array = new ArrayList<Shiftdata>();

    TextView c50 ;
    TextView c51 ;
    TextView c52 ;
    TextView c53 ;
    TextView c54 ;
    TextView c55 ;
    TextView c56 ;
    TextView c60 ;
    TextView c61 ;
    TextView c62 ;
    TextView c63 ;
    TextView c64 ;
    TextView c65 ;
    TextView c66 ;
    TextView c70;
    TextView c71 ;
    TextView c72 ;
    TextView c73 ;
    TextView c74 ;
    TextView c75 ;
    TextView c76 ;
    TextView c80 ;
    TextView c81 ;
    TextView c82 ;
    TextView c83 ;
    TextView c84 ;
    TextView c85 ;
    TextView c86 ;
    TextView c90 ;
    TextView c91 ;
    TextView c92 ;
    TextView c93 ;
    TextView c94 ;
    TextView c95 ;
    TextView c96 ;

    EditText Monday;
    EditText Tuesday;
    EditText Wednesday;
    EditText Thursday;
    EditText Friday;
    EditText Saturday;
    EditText Sunday;



    TextView c120 ;
    TextView c121 ;
    TextView c122 ;
    TextView c123 ;
    TextView c124 ;
    TextView c125 ;
    TextView c126 ;
    TextView c130 ;
    TextView c131 ;
    TextView c132 ;
    TextView c133 ;
    TextView c134 ;
    TextView c135 ;
    TextView c136 ;
    TextView c140 ;
    TextView c141 ;
    TextView c142 ;
    TextView c143 ;
    TextView c144 ;
    TextView c145 ;
    TextView c146 ;
    TextView c150 ;
    TextView c151 ;
    TextView c152 ;
    TextView c153 ;
    TextView c154 ;
    TextView c155 ;
    TextView c156 ;
    TextView c160 ;
    TextView c161 ;
    TextView c162 ;
    TextView c163 ;
    TextView c164 ;
    TextView c165 ;
    TextView c166 ;
    TextView c170 ;
    TextView c171 ;
    TextView c172 ;
    TextView c173 ;
    TextView c174 ;
    TextView c175 ;
    TextView c176 ;
    TextView c190 ;
    TextView c191 ;
    TextView c192 ;
    TextView c193 ;
    TextView c194 ;
    TextView c195 ;
    TextView c196 ;
    TextView c200 ;
    TextView c201 ;
    TextView c202 ;
    TextView c203 ;
    TextView c204 ;
    TextView c205 ;
    TextView c206 ;
    TextView c210 ;
    TextView c211 ;
    TextView c212 ;
    TextView c213 ;
    TextView c214 ;
    TextView c216 ;
    TextView c220 ;
    TextView c221 ;
    TextView c222 ;
    TextView c223 ;
    TextView c224 ;
    TextView c225 ;
    TextView c226 ;
    TextView c230 ;
    TextView c231 ;
    TextView c232 ;
    TextView c233 ;
    TextView c234 ;
    TextView c235 ;
    TextView c240 ;
    TextView c241 ;
    TextView c242 ;
    TextView c243 ;
    TextView c244 ;
    TextView c245 ;
    TextView c246 ;

   // Intent intent = getIntent();
   String mytoken ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.content_main);

        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                mytoken= null;
            } else {
                mytoken= extras.getString("token");
            }
        } else {
            mytoken= (String) savedInstanceState.getSerializable("token");
        }

        Log.d(TAG, "Token" + mytoken);




       final FloatingActionButton actionButton = (FloatingActionButton) findViewById(R.id.fab);
        Monday = (EditText) findViewById(R.id.Mon);
        Tuesday = (EditText) findViewById(R.id.Tue);
        Wednesday = (EditText) findViewById(R.id.Wed);
        Thursday = (EditText) findViewById(R.id.Thu);
        Friday = (EditText) findViewById(R.id.Fri);
        Saturday = (EditText) findViewById(R.id.Sat);
        Sunday = (EditText) findViewById(R.id.Sun);

        SharedPreferences sharedPreferences = getSharedPreferences("Wheniwork data", Context.MODE_PRIVATE);
        Monday.setText(sharedPreferences.getString("Monday", ""));
        Tuesday.setText(sharedPreferences.getString("Tuesday",""));
        Wednesday.setText(sharedPreferences.getString("Wednesday",""));
        Thursday.setText(sharedPreferences.getString("Thursday",""));
        Friday.setText(sharedPreferences.getString("Friday", ""));
        Saturday.setText(sharedPreferences.getString("Saturday", ""));
        Sunday.setText(sharedPreferences.getString("Sunday", ""));

        Log.d(TAG, "The text on Monday was: " + sharedPreferences.getString("Monday", ""));




        try {
            new JSONTask().execute("https://api.wheniwork.com/2/schedule");
            //Log.d(TAG, "Exception " + e);
        } catch (Exception e){
            Log.d(TAG, "Exception " + e);
        }
        populate();

       // final Button btn = (Button)findViewById(R.id.submit);
        // data = (TextView)findViewById(R.id.jsondata);





        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED ){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.INTERNET
                }, 10);
            }
            return;
        }
        else {
            actionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new JSONTask().execute("https://api.wheniwork.com/2/schedule");
                    populate();




                }
            });

        }
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            actionButton.callOnClick();
                        } catch (Exception e) {
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 60*30000);







    }

    private void populate() {
        c50 = (TextView) findViewById(R.id.c50);
        c51 = (TextView) findViewById(R.id.c51);
        c52 = (TextView) findViewById(R.id.c52);
        c53 = (TextView) findViewById(R.id.c53);
        c54 = (TextView) findViewById(R.id.c54);
        c55 = (TextView) findViewById(R.id.c55);
        c56 = (TextView) findViewById(R.id.c56);
        c60 = (TextView) findViewById(R.id.c60);
        c61 = (TextView) findViewById(R.id.c61);
        c62 = (TextView) findViewById(R.id.c62);
        c63 = (TextView) findViewById(R.id.c63);
        c64 = (TextView) findViewById(R.id.c64);
        c65 = (TextView) findViewById(R.id.c65);
        c66 = (TextView) findViewById(R.id.c66);
        c70 = (TextView) findViewById(R.id.c70);
        c71 = (TextView) findViewById(R.id.c71);
        c72 = (TextView) findViewById(R.id.c72);
        c73 = (TextView) findViewById(R.id.c73);
        c74 = (TextView) findViewById(R.id.c74);
        c75 = (TextView) findViewById(R.id.c75);
        c76 = (TextView) findViewById(R.id.c76);
        c80 = (TextView) findViewById(R.id.c80);
        c81 = (TextView) findViewById(R.id.c81);
        c82 = (TextView) findViewById(R.id.c82);
        c83 = (TextView) findViewById(R.id.c83);
        c84 = (TextView) findViewById(R.id.c84);
        c85 = (TextView) findViewById(R.id.c85);
        c86 = (TextView) findViewById(R.id.c86);
        c90 = (TextView) findViewById(R.id.c90);
        c91 = (TextView) findViewById(R.id.c91);
        c92 = (TextView) findViewById(R.id.c92);
        c93 = (TextView) findViewById(R.id.c93);
        c94 = (TextView) findViewById(R.id.c94);
        c95 = (TextView) findViewById(R.id.c95);
        c96 = (TextView) findViewById(R.id.c96);
        c120 = (TextView) findViewById(R.id.c120);
        c121 = (TextView) findViewById(R.id.c121);
        c122 = (TextView) findViewById(R.id.c122);
        c123 = (TextView) findViewById(R.id.c123);
        c124 = (TextView) findViewById(R.id.c124);
        c125 = (TextView) findViewById(R.id.c125);
        c126 = (TextView) findViewById(R.id.c126);
        c130 = (TextView) findViewById(R.id.c130);
        c131 = (TextView) findViewById(R.id.c131);
        c132 = (TextView) findViewById(R.id.c132);
        c133 = (TextView) findViewById(R.id.c133);
        c134 = (TextView) findViewById(R.id.c134);
        c135 = (TextView) findViewById(R.id.c135);
        c136 = (TextView) findViewById(R.id.c136);
        c140 = (TextView) findViewById(R.id.c140);
        c141 = (TextView) findViewById(R.id.c141);
        c142 = (TextView) findViewById(R.id.c142);
        c143 = (TextView) findViewById(R.id.c143);
        c144 = (TextView) findViewById(R.id.c144);
        c145 = (TextView) findViewById(R.id.c145);
        c146 = (TextView) findViewById(R.id.c146);
        c150 = (TextView) findViewById(R.id.c150);
        c151 = (TextView) findViewById(R.id.c151);
        c152 = (TextView) findViewById(R.id.c152);
        c153 = (TextView) findViewById(R.id.c153);
        c154 = (TextView) findViewById(R.id.c154);
        c155 = (TextView) findViewById(R.id.c155);
        c156 = (TextView) findViewById(R.id.c156);
        c160 = (TextView) findViewById(R.id.c160);
        c161 = (TextView) findViewById(R.id.c161);
        c162 = (TextView) findViewById(R.id.c162);
        c163 = (TextView) findViewById(R.id.c163);
        c164 = (TextView) findViewById(R.id.c164);
        c165 = (TextView) findViewById(R.id.c165);
        c166 = (TextView) findViewById(R.id.c166);
        c170 = (TextView) findViewById(R.id.c170);
        c171 = (TextView) findViewById(R.id.c171);
        c172 = (TextView) findViewById(R.id.c172);
        c173 = (TextView) findViewById(R.id.c173);
        c174 = (TextView) findViewById(R.id.c174);
        c175 = (TextView) findViewById(R.id.c175);
        c176 = (TextView) findViewById(R.id.c176);
        c190 = (TextView) findViewById(R.id.c190);
        c191 = (TextView) findViewById(R.id.c191);
        c192 = (TextView) findViewById(R.id.c192);
        c193 = (TextView) findViewById(R.id.c193);
        c194 = (TextView) findViewById(R.id.c194);
        c195 = (TextView) findViewById(R.id.c195);
        c196 = (TextView) findViewById(R.id.c196);
        c200 = (TextView) findViewById(R.id.c200);
        c201 = (TextView) findViewById(R.id.c201);
        c202 = (TextView) findViewById(R.id.c202);
        c203 = (TextView) findViewById(R.id.c203);
        c204 = (TextView) findViewById(R.id.c204);
        c205 = (TextView) findViewById(R.id.c205);
        c206 = (TextView) findViewById(R.id.c206);
        c210 = (TextView) findViewById(R.id.c210);
        c211 = (TextView) findViewById(R.id.c211);
        c212 = (TextView) findViewById(R.id.c212);
        c213 = (TextView) findViewById(R.id.c213);
        c214 = (TextView) findViewById(R.id.c214);
        c216 = (TextView) findViewById(R.id.c216);
        c220 = (TextView) findViewById(R.id.c220);
        c221 = (TextView) findViewById(R.id.c221);
        c222 = (TextView) findViewById(R.id.c222);
        c223 = (TextView) findViewById(R.id.c223);
        c224 = (TextView) findViewById(R.id.c224);
        c225 = (TextView) findViewById(R.id.c225);
        c226 = (TextView) findViewById(R.id.c226);
        c230 = (TextView) findViewById(R.id.c230);
        c231 = (TextView) findViewById(R.id.c231);
        c232 = (TextView) findViewById(R.id.c232);
        c233 = (TextView) findViewById(R.id.c233);
        c234 = (TextView) findViewById(R.id.c234);
        c235 = (TextView) findViewById(R.id.c235);
        c240 = (TextView) findViewById(R.id.c240);
        c241 = (TextView) findViewById(R.id.c241);
        c242 = (TextView) findViewById(R.id.c242);
        c243 = (TextView) findViewById(R.id.c243);
        c244 = (TextView) findViewById(R.id.c244);
        c245 = (TextView) findViewById(R.id.c245);
        c246 = (TextView) findViewById(R.id.c246);

        c50.setText("");
        c51.setText("");
        c52.setText("");
        c53.setText("");
        c54.setText("");
        c55.setText("");
        c56.setText("");
        c60.setText("");
        c61.setText("");
        c62.setText("");
        c63.setText("");
        c64.setText("");
        c65.setText("");
        c66.setText("");
        c70.setText("");
        c71.setText("");
        c72.setText("");
        c73.setText("");
        c74.setText("");
        c75.setText("");
        c76.setText("");
        c80.setText("");
        c81.setText("");
        c82.setText("");
        c83.setText("");
        c84.setText("");
        c85.setText("");
        c86.setText("");
        c90.setText("");
        c91.setText("");
        c92.setText("");
        c93.setText("");
        c94.setText("");
        c95.setText("");
        c96.setText("");
        c120.setText("");
        c121.setText("");
        c122.setText("");
        c123.setText("");
        c124.setText("");
        c125.setText("");
        c126.setText("");
        c130.setText("");
        c131.setText("");
        c132.setText("");
        c133.setText("");
        c134.setText("");
        c135.setText("");
        c136.setText("");
        c140.setText("");
        c141.setText("");
        c142.setText("");
        c143.setText("");
        c144.setText("");
        c145.setText("");
        c146.setText("");
        c150.setText("");
        c151.setText("");
        c152.setText("");
        c153.setText("");
        c154.setText("");
        c155.setText("");
        c156.setText("");
        c160.setText("");
        c161.setText("");
        c162.setText("");
        c163.setText("");
        c164.setText("");
        c165.setText("");
        c166.setText("");
        c170.setText("");
        c171.setText("");
        c172.setText("");
        c173.setText("");
        c174.setText("");
        c175.setText("");
        c176.setText("");
        c190.setText("");
        c191.setText("");
        c192.setText("");
        c193.setText("");
        c194.setText("");
        c195.setText("");
        c196.setText("");
        c200.setText("");
        c201.setText("");
        c202.setText("");
        c203.setText("");
        c204.setText("");
        c205.setText("");
        c206.setText("");
        c210.setText("");
        c211.setText("");
        c212.setText("");
        c213.setText("");
        c214.setText("");
        c216.setText("");
        c220.setText("");
        c221.setText("");
        c222.setText("");
        c223.setText("");
        c224.setText("");
        c225.setText("");
        c226.setText("");
        c230.setText("");
        c231.setText("");
        c232.setText("");
        c233.setText("");
        c234.setText("");
        c235.setText("");
        c240.setText("");
        c241.setText("");
        c242.setText("");
        c243.setText("");
        c244.setText("");
        c245.setText("");
        c246.setText("");










        String temp_start=null;
        String temp_end = null;
        for (int s=0; s<java_shifts_array.size(); s++){
            temp_start = java_shifts_array.get(s).getStart_time();
            temp_end =java_shifts_array.get(s).getEnd_time();
            Log.d(TAG, "Inside the populate method");
            if (temp_start.contains("Mon") && (temp_start.contains("07:30:00")) && (temp_end.contains("12:15:00"))){
                Log.d(TAG, "Inside the start time container"+java_shifts_array.get(s).getStart_time()+ " hey: "+ java_shifts_array.get(s).getEnd_time());
                if (c50.getText().toString().equalsIgnoreCase("")){

                    Log.d(TAG, "Inside the get checker" + java_shifts_array.get(s).getLastname());

                    c50.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c60.getText().toString().equalsIgnoreCase("")) {
                    c60.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c70.getText().toString().equalsIgnoreCase("")){
                    c70.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c80.getText().toString().equalsIgnoreCase("")){
                    c80.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c90.getText().toString().equalsIgnoreCase("")){
                    c90.setText(java_shifts_array.get(s).getLastname());
                }



            }
            if (temp_start.contains("Mon") && (temp_start.contains("12:15:00")) && (temp_end.contains("17:00:00"))){
                Log.d(TAG, "Inside the start time container"+java_shifts_array.get(s).getStart_time()+ " hey: "+ java_shifts_array.get(s).getEnd_time());
                if (c120.getText().toString().equalsIgnoreCase("")){

                    Log.d(TAG, "Inside the get checker 1 " + java_shifts_array.get(s).getLastname());

                    c120.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c130.getText().toString().equalsIgnoreCase("")){
                    Log.d(TAG, "Inside the get checker 2 " + java_shifts_array.get(s).getLastname());
                    c130.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c140.getText().toString().equalsIgnoreCase("")){
                    Log.d(TAG, "Inside the get checker 3 " + java_shifts_array.get(s).getLastname());
                    c140.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c150.getText().toString().equalsIgnoreCase("")){
                    Log.d(TAG, "Inside the get checker 4 " + java_shifts_array.get(s).getLastname());
                    c150.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c160.getText().toString().equalsIgnoreCase("")){
                    Log.d(TAG, "Inside the get checker 5 " + java_shifts_array.get(s).getLastname());
                    c160.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c170.getText().toString().equalsIgnoreCase("")){
                    Log.d(TAG, "Inside the get checker 6 " + java_shifts_array.get(s).getLastname());
                    c170.setText(java_shifts_array.get(s).getLastname());
                }


            }
            if (temp_start.contains("Mon") && (temp_start.contains("17:00:00")) && (temp_end.contains("22:00:00"))){
                Log.d(TAG, "Inside the start time container"+java_shifts_array.get(s).getStart_time()+ " hey: "+ java_shifts_array.get(s).getEnd_time());
                if (c190.getText().toString().equalsIgnoreCase("")){

                    Log.d(TAG, "Inside the get checker" + java_shifts_array.get(s).getLastname());

                    c190.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c200.getText().toString().equalsIgnoreCase("")){
                    c200.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c210.getText().toString().equalsIgnoreCase("")){
                    c210.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c220.getText().toString().equalsIgnoreCase("")){
                    c220.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c230.getText().toString().equalsIgnoreCase("")){
                    c230.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c240.getText().toString().equalsIgnoreCase("")){
                    c240.setText(java_shifts_array.get(s).getLastname());
                }


            }
            if (temp_start.contains("Tue") && (temp_start.contains("07:30:00")) && (temp_end.contains("12:15:00"))){
                Log.d(TAG, "Inside the start time container"+java_shifts_array.get(s).getStart_time()+ " hey: "+ java_shifts_array.get(s).getEnd_time());
                if (c51.getText().toString().equalsIgnoreCase("")){

                    Log.d(TAG, "Inside the get checker" + java_shifts_array.get(s).getLastname());

                    c51.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c61.getText().toString().equalsIgnoreCase("")){
                    c61.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c71.getText().toString().equalsIgnoreCase("")){
                    c71.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c81.getText().toString().equalsIgnoreCase("")){
                    c81.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c91.getText().toString().equalsIgnoreCase("")){
                    c91.setText(java_shifts_array.get(s).getLastname());
                }



            }
            if (temp_start.contains("Tue") && (temp_start.contains("12:15:00")) && (temp_end.contains("17:00:00"))){
                Log.d(TAG, "Inside the start time container"+java_shifts_array.get(s).getStart_time()+ " hey: "+ java_shifts_array.get(s).getEnd_time());
                if (c121.getText().toString().equalsIgnoreCase("")){

                    Log.d(TAG, "Inside the get checker" + java_shifts_array.get(s).getLastname());

                    c121.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c131.getText().toString().equalsIgnoreCase("")){
                    c131.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c141.getText().toString().equalsIgnoreCase("")){
                    c141.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c151.getText().toString().equalsIgnoreCase("")){
                    c151.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c161.getText().toString().equalsIgnoreCase("")){
                    c161.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c171.getText().toString().equalsIgnoreCase("")){
                    c171.setText(java_shifts_array.get(s).getLastname());
                }


            }
            if (temp_start.contains("Tue") && (temp_start.contains("17:00:00")) && (temp_end.contains("22:00:00"))){
                Log.d(TAG, "Inside the start time container"+java_shifts_array.get(s).getStart_time()+ " hey: "+ java_shifts_array.get(s).getEnd_time());
                if (c191.getText().toString().equalsIgnoreCase("")){

                    Log.d(TAG, "Inside the get checker" + java_shifts_array.get(s).getLastname());

                    c191.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c201.getText().toString().equalsIgnoreCase("")){
                    c201.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c211.getText().toString().equalsIgnoreCase("")){
                    c211.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c221.getText().toString().equalsIgnoreCase("")){
                    c221.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c231.getText().toString().equalsIgnoreCase("")){
                    c231.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c241.getText().toString().equalsIgnoreCase("")){
                    c241.setText(java_shifts_array.get(s).getLastname());
                }


            }
            if (temp_start.contains("Wed") && (temp_start.contains("07:30:00")) && (temp_end.contains("12:15:00"))){
                Log.d(TAG, "Inside the start time container"+java_shifts_array.get(s).getStart_time()+ " hey: "+ java_shifts_array.get(s).getEnd_time());
                if (c52.getText().toString().equalsIgnoreCase("")){

                    Log.d(TAG, "Inside the get checker" + java_shifts_array.get(s).getLastname());

                    c52.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c62.getText().toString().equalsIgnoreCase("")){
                    c62.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c72.getText().toString().equalsIgnoreCase("")){
                    c72.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c82.getText().toString().equalsIgnoreCase("")){
                    c82.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c92.getText().toString().equalsIgnoreCase("")){
                    c92.setText(java_shifts_array.get(s).getLastname());
                }



            }
            if (temp_start.contains("Wed") && (temp_start.contains("12:15:00")) && (temp_end.contains("17:00:00"))){
                Log.d(TAG, "Inside the start time container"+java_shifts_array.get(s).getStart_time()+ " hey: "+ java_shifts_array.get(s).getEnd_time());
                if (c122.getText().toString().equalsIgnoreCase("")){

                    Log.d(TAG, "Inside the get checker" + java_shifts_array.get(s).getLastname());

                    c122.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c132.getText().toString().equalsIgnoreCase("")){
                    c132.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c142.getText().toString().equalsIgnoreCase("")){
                    c142.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c152.getText().toString().equalsIgnoreCase("")){
                    c152.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c162.getText().toString().equalsIgnoreCase("")){
                    c162.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c172.getText().toString().equalsIgnoreCase("")){
                    c172.setText(java_shifts_array.get(s).getLastname());
                }


            }
            if (temp_start.contains("Wed") && (temp_start.contains("17:00:00")) && (temp_end.contains("22:00:00"))){
                Log.d(TAG, "Inside the start time container"+java_shifts_array.get(s).getStart_time()+ " hey: "+ java_shifts_array.get(s).getEnd_time());
                if (c192.getText().toString().equalsIgnoreCase("")){

                    Log.d(TAG, "Inside the get checker" + java_shifts_array.get(s).getLastname());

                    c192.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c202.getText().toString().equalsIgnoreCase("")){
                    c202.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c212.getText().toString().equalsIgnoreCase("")){
                    c212.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c222.getText().toString().equalsIgnoreCase("")){
                    c222.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c232.getText().toString().equalsIgnoreCase("")){
                    c232.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c242.getText().toString().equalsIgnoreCase("")){
                    c242.setText(java_shifts_array.get(s).getLastname());
                }


            }
            if (temp_start.contains("Thu") && (temp_start.contains("07:30:00")) && (temp_end.contains("12:15:00"))){
                Log.d(TAG, "Inside the start time container"+java_shifts_array.get(s).getStart_time()+ " hey: "+ java_shifts_array.get(s).getEnd_time());
                if (c53.getText().toString().equalsIgnoreCase("")){

                    Log.d(TAG, "Inside the get checker" + java_shifts_array.get(s).getLastname());

                    c53.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c63.getText().toString().equalsIgnoreCase("")){
                    c63.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c73.getText().toString().equalsIgnoreCase("")){
                    c73.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c83.getText().toString().equalsIgnoreCase("")){
                    c83.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c93.getText().toString().equalsIgnoreCase("")){
                    c93.setText(java_shifts_array.get(s).getLastname());
                }



            }
            if (temp_start.contains("Thu") && (temp_start.contains("12:15:00")) && (temp_end.contains("17:00:00"))){
                Log.d(TAG, "Inside the start time container"+java_shifts_array.get(s).getStart_time()+ " hey: "+ java_shifts_array.get(s).getEnd_time());
                if (c123.getText().toString().equalsIgnoreCase("")){

                    Log.d(TAG, "Inside the get checker" + java_shifts_array.get(s).getLastname());

                    c123.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c133.getText().toString().equalsIgnoreCase("")){
                    c133.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c143.getText().toString().equalsIgnoreCase("")){
                    c143.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c153.getText().toString().equalsIgnoreCase("")){
                    c153.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c163.getText().toString().equalsIgnoreCase("")){
                    c163.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c173.getText().toString().equalsIgnoreCase("")){
                    c173.setText(java_shifts_array.get(s).getLastname());
                }


            }
            if (temp_start.contains("Thu") && (temp_start.contains("17:00:00")) && (temp_end.contains("22:00:00"))){
                Log.d(TAG, "Inside the start time container"+java_shifts_array.get(s).getStart_time()+ " hey: "+ java_shifts_array.get(s).getEnd_time());
                if (c193.getText().toString().equalsIgnoreCase("")){

                    Log.d(TAG, "Inside the get checker" + java_shifts_array.get(s).getLastname());

                    c193.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c203.getText().toString().equalsIgnoreCase("")){
                    c203.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c213.getText().toString().equalsIgnoreCase("")){
                    c213.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c223.getText().toString().equalsIgnoreCase("")){
                    c223.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c233.getText().toString().equalsIgnoreCase("")){
                    c233.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c243.getText().toString().equalsIgnoreCase("")){
                    c243.setText(java_shifts_array.get(s).getLastname());
                }


            }
            if (temp_start.contains("Fri") && (temp_start.contains("07:30:00")) && (temp_end.contains("12:15:00"))){
                Log.d(TAG, "Inside the start time container"+java_shifts_array.get(s).getStart_time()+ " hey: "+ java_shifts_array.get(s).getEnd_time());
                if (c54.getText().toString().equalsIgnoreCase("")){

                    Log.d(TAG, "Inside the get checker" + java_shifts_array.get(s).getLastname());

                    c54.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c64.getText().toString().equalsIgnoreCase("")){
                    c64.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c74.getText().toString().equalsIgnoreCase("")){
                    c74.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c84.getText().toString().equalsIgnoreCase("")){
                    c84.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c94.getText().toString().equalsIgnoreCase("")){
                    c94.setText(java_shifts_array.get(s).getLastname());
                }



            }
            if (temp_start.contains("Fri") && (temp_start.contains("12:15:00")) && (temp_end.contains("17:00:00"))){
                Log.d(TAG, "Inside the start time container"+java_shifts_array.get(s).getStart_time()+ " hey: "+ java_shifts_array.get(s).getEnd_time());
                if (c124.getText().toString().equalsIgnoreCase("")){

                    Log.d(TAG, "Inside the get checker" + java_shifts_array.get(s).getLastname());

                    c124.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c134.getText().toString().equalsIgnoreCase("")){
                    c134.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c144.getText().toString().equalsIgnoreCase("")){
                    c144.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c154.getText().toString().equalsIgnoreCase("")){
                    c154.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c164.getText().toString().equalsIgnoreCase("")){
                    c164.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c174.getText().toString().equalsIgnoreCase("")){
                    c174.setText(java_shifts_array.get(s).getLastname());
                }


            }
            if (temp_start.contains("Fri") && (temp_start.contains("17:00:00")) && (temp_end.contains("22:00:00"))){
                Log.d(TAG, "Inside the start time container"+java_shifts_array.get(s).getStart_time()+ " hey: "+ java_shifts_array.get(s).getEnd_time());
                if (c194.getText().toString().equalsIgnoreCase("")){

                    Log.d(TAG, "Inside the get checker" + java_shifts_array.get(s).getLastname());

                    c194.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c204.getText().toString().equalsIgnoreCase("")){
                    c204.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c214.getText().toString().equalsIgnoreCase("")){
                    c214.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c224.getText().toString().equalsIgnoreCase("")){
                    c224.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c234.getText().toString().equalsIgnoreCase("")){
                    c234.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c244.getText().toString().equalsIgnoreCase("")){
                    c244.setText(java_shifts_array.get(s).getLastname());
                }


            }
            if (temp_start.contains("Sat") && (temp_start.contains("08:00:00")) && (temp_end.contains("13:00:00"))){
                Log.d(TAG, "Inside the start time container"+java_shifts_array.get(s).getStart_time()+ " hey: "+ java_shifts_array.get(s).getEnd_time());
                if (c55.getText().toString().equalsIgnoreCase("")){

                    Log.d(TAG, "Inside the get checker" + java_shifts_array.get(s).getLastname());

                    c55.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c65.getText().toString().equalsIgnoreCase("")){
                    c65.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c75.getText().toString().equalsIgnoreCase("")){
                    c75.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c85.getText().toString().equalsIgnoreCase("")){
                    c85.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c95.getText().toString().equalsIgnoreCase("")){
                    c95.setText(java_shifts_array.get(s).getLastname());
                }



            }
            if (temp_start.contains("Sat") && (temp_start.contains("13:00:00")) && (temp_end.contains("18:00:00"))){
                Log.d(TAG, "Inside the start time container"+java_shifts_array.get(s).getStart_time()+ " hey: "+ java_shifts_array.get(s).getEnd_time());
                if (c125.getText().toString().equalsIgnoreCase("")){

                    Log.d(TAG, "Inside the get checker" + java_shifts_array.get(s).getLastname());

                    c125.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c135.getText().toString().equalsIgnoreCase("")){
                    c135.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c145.getText().toString().equalsIgnoreCase("")){
                    c145.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c155.getText().toString().equalsIgnoreCase("")){
                    c155.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c165.getText().toString().equalsIgnoreCase("")){
                    c164.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c175.getText().toString().equalsIgnoreCase("")){
                    c175.setText(java_shifts_array.get(s).getLastname());
                }


            }
            if (temp_start.contains("Sun") && (temp_start.contains("12:00:00")) && (temp_end.contains("17:00:00"))){
                Log.d(TAG, "Inside the start time container"+java_shifts_array.get(s).getStart_time()+ " hey: "+ java_shifts_array.get(s).getEnd_time());
                if (c126.getText().toString().equalsIgnoreCase("")){

                    Log.d(TAG, "Inside the get checker" + java_shifts_array.get(s).getLastname());

                    c126.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c136.getText().toString().equalsIgnoreCase("")){
                    c136.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c146.getText().toString().equalsIgnoreCase("")){
                    c146.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c156.getText().toString().equalsIgnoreCase("")){
                    c156.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c166.getText().toString().equalsIgnoreCase("")){
                    c166.setText(java_shifts_array.get(s).getLastname());
                }
                else if (c176.getText().toString().equalsIgnoreCase("")){
                    c176.setText(java_shifts_array.get(s).getLastname());
                }


            }





        }
    }

    public  class JSONTask extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("W-Token", mytoken);
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

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            JSONObject parent_object = null;
            try {
                java_users_array.clear();
                java_shifts_array.clear();
                if (result == null) {
                    AlertDialog.Builder builer1 = new AlertDialog.Builder(getApplicationContext());
                    builer1.setMessage("Dude, Check the internet Connection!");
                    builer1.setCancelable(true);
                    builer1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = builer1.create();
                    alertDialog.show();

                } else {
                    parent_object = new JSONObject(result);
                    JSONArray users_json_array = parent_object.getJSONArray("users");
                    JSONArray shifts_json_array = parent_object.getJSONArray("shifts");

                    JSONObject user_json_object = users_json_array.getJSONObject(0);
                    //temporary user for the array.


                    int size_users = users_json_array.length();
                    int size_shifts = shifts_json_array.length();


                    String lastname = null;


                    for (int i = 0; i < size_users; i++) {
                        Users temp = new Users();
                        JSONObject user_json_object_temp = users_json_array.getJSONObject(i);
                        temp.setLast_name(user_json_object_temp.getString("last_name"));
                        temp.setFirst_name(user_json_object_temp.getString("first_name"));
                        temp.setUser_id(user_json_object_temp.getInt("id"));

                        java_users_array.add(temp);
                        Log.d(TAG, "Users Array" + (java_users_array.get(i).getFirst_name() + " " + java_users_array.get(i).getLast_name()));

                    }


                    for (int i = 0; i < size_shifts; i++) {
                        Shiftdata tempshift = new Shiftdata();
                        JSONObject shift_json_object_temp = shifts_json_array.getJSONObject(i);
                        tempshift.setStart_time(shift_json_object_temp.getString("start_time"));
                        tempshift.setEnd_time(shift_json_object_temp.getString("end_time"));
                        tempshift.setUser_id(shift_json_object_temp.getInt("user_id"));
                        tempshift.setShift_id(shift_json_object_temp.getInt("id"));
                        tempshift.setLastname(getName(shift_json_object_temp.getInt("user_id")));

                        java_shifts_array.add(i, tempshift);


                    }
                    for (int m = 0; m < size_shifts; m++) {
                        Log.d(TAG, "lastname: " + java_shifts_array.get(m).getLastname() + " Start: " + java_shifts_array.get(m).getStart_time() + " End: " + java_shifts_array.get(m).getEnd_time());
                        //java_shifts_array.get(i).getUser_id()
                    }


                    String name = user_json_object.getString("first_name");
                }
                }catch(JSONException e){
                    e.printStackTrace();
                }


            // data.setText(result);
        }
        public String getName(int id){
            String lastname=null;
            int sz = java_users_array.size();
            int user ;
            for (int m=0; m<sz; m++){
                user = java_users_array.get(m).getUser_id();
                //Log.d(TAG, "userid: " + user);

                if (id==java_users_array.get(m).getUser_id()){

                    lastname = java_users_array.get(m).getFirst_name()+ " " +java_users_array.get(m).getLast_name();
                    //Log.d(TAG, "lastname: " + lastname);

                }
            }
            return lastname;





        }

    }

    @Override
    protected void onStop() {
        SharedPreferences sharedPreferences = getSharedPreferences("Wheniwork data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Monday", Monday.getText().toString());
        editor.putString("Tuesday", Tuesday.getText().toString());
        editor.putString("Wednesday", Wednesday.getText().toString());
        editor.putString("Thursday", Thursday.getText().toString());
        editor.putString("Friday", Friday.getText().toString());
        editor.putString("Saturday", Saturday.getText().toString());
        editor.putString("Sunday", Sunday.getText().toString());

        editor.commit();
        Toast.makeText(this, "Data was saved!", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }
}