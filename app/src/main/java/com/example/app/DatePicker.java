package com.example.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

//import com.squareup.timessquare.CalendarPickerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class DatePicker extends AppCompatActivity {


    private CalendarView calendar;
    private static String URL_LOGIN="http://192.168.0.38/calendar/getDay.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Date today = new Date();
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR,1);

        calendar = findViewById(R.id.calendar1);
        //dataPicker.init(today,nextYear.getTime()).withSelectedDate(today);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange( CalendarView view, int year, int month, int dayOfMonth) {
                //Date date = new GregorianCalendar(year, month, dayOfMonth).getGregorianChange();

                getDay(year, month+1, dayOfMonth);
                /*Intent intent = new Intent(DatePicker.this, DayActivity.class);
                intent.putExtra("year", year);
                intent.putExtra("month", month);
                intent.putExtra("dayOfMonth", dayOfMonth);
                startActivity(intent);*/



            }
        });


    }

    private void getDay(final int year,final int month,final int dayOfMonth){
        final String date = dayOfMonth+"-"+month+"-"+year;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);//format odpowiedzi
                            String success = jsonObject.getString("success");


                            if(success.equals("1")){

                                ArrayList<String> hours = new ArrayList<>();
                                hours.add(jsonObject.getString("hour9"));
                                hours.add(jsonObject.getString("hour10"));
                                hours.add(jsonObject.getString("hour11"));
                                hours.add(jsonObject.getString("hour12"));
                                hours.add(jsonObject.getString("hour13"));
                                hours.add(jsonObject.getString("hour14"));
                                hours.add(jsonObject.getString("hour15"));
                                hours.add(jsonObject.getString("hour16"));

                                //displaying all paramiters on login screen
                                Toast.makeText(DatePicker.this,
                                        "Success Login. \nHello "
                                        , Toast.LENGTH_SHORT)
                                        .show();
                                Intent intent = new Intent(DatePicker.this, DayActivity.class);
                                intent.putExtra("hours", hours);
                                intent.putExtra("year", year);
                                intent.putExtra("month", month);
                                intent.putExtra("dayOfMonth", dayOfMonth);

                                startActivity(intent);


                                //}
                            }else{
                                Toast.makeText(DatePicker.this,
                                        "Login Error \nWrong Email or Password"

                                        , Toast.LENGTH_SHORT)
                                        .show();
                            }
                        }catch(JSONException e) {

                            Toast.makeText(DatePicker.this, "Error JSON "+e.toString(), Toast.LENGTH_SHORT).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(DatePicker.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("date", date);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
