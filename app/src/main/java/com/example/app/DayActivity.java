package com.example.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DayActivity extends AppCompatActivity {
    private TextView header;
    private EditText a, b, c, d, e, f, g, h;
    private ArrayList<EditText> lista = new ArrayList<>();
    private Button btn_confirm;
    private static String URL_REGIST = "http://192.168.0.38/calendar/reservation.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        header = findViewById(R.id.textViewHeader);
        btn_confirm = findViewById(R.id.textViewFooter);

        a = findViewById(R.id.view9);
        b = findViewById(R.id.view10);
        c = findViewById(R.id.view11);
        d = findViewById(R.id.view12);
        e = findViewById(R.id.view13);
        f = findViewById(R.id.view14);
        g = findViewById(R.id.view15);
        h = findViewById(R.id.view16);
        lista.add(a);
        lista.add(b);
        lista.add(c);
        lista.add(d);
        lista.add(e);
        lista.add(f);
        lista.add(g);
        lista.add(h);
        Intent intent = getIntent();
        int year = intent.getIntExtra("year", 0);
        int month = intent.getIntExtra("month", 0);
        int dayOfMonth = intent.getIntExtra("dayOfMonth", 0);
        ArrayList<String> hours = intent.getStringArrayListExtra("hours");
        final String date = dayOfMonth+"-"+month+"-"+year;
        header.setText((dayOfMonth < 10 ? "0" + Integer.toString(dayOfMonth) : Integer.toString(dayOfMonth)) + "-" + (month < 10 ? "0" + Integer.toString(month) : Integer.toString(month)) + "-" + Integer.toString(year));

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (EditText a : lista) {
                    String string = a.getText().toString().trim();
                    if (!string.isEmpty()) {

                        reservation(Integer.toString(lista.indexOf(a)), a.getText().toString().trim(), date);
                        Toast.makeText(DayActivity.this, string, Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        for (int i = 0; i < hours.size(); i++) {
            if (hours.get(i).equals("0")) {
                lista.get(i).setText("");
            } else {
                lista.get(i).setHint("not available");
                lista.get(i).setInputType(InputType.TYPE_NULL);
            }
        }
    }

    private void reservation(final String id, final String email, final String date){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if(success.equals("1")){
                                Toast.makeText(DayActivity.this, "Reservation completed", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(DayActivity.this, DatePicker.class);

                                startActivity(intent);
                            }
                        }catch(JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(DayActivity.this, "Reservation Error! JSON"+ e.toString(), Toast.LENGTH_SHORT).show();



                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DayActivity.this, "Reservation Error!"+ error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("id", id);
                params.put("email", email);
                params.put("date", date);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }





}
