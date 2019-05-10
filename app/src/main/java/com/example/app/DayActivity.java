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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DayActivity extends AppCompatActivity {
    private TextView header;
    private EditText a,b,c,d,e,f,g,h;
    private ArrayList<EditText> lista = new ArrayList<>();
    private Button btn_confirm;

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
        lista.add(a);lista.add(b);lista.add(c);lista.add(d);lista.add(e);lista.add(f);lista.add(g);lista.add(h);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(EditText a : lista) {
                    String string = a.getText().toString().trim();
                    if(!string.isEmpty()){

                        Toast.makeText(DayActivity.this, string ,Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        Intent intent = getIntent();
        int year = intent.getIntExtra("year", 0);
        int month = intent.getIntExtra("month", 0);
        int dayOfMonth = intent.getIntExtra("dayOfMonth", 0);
        ArrayList<String> hours = intent.getStringArrayListExtra("hours");
        header.setText(Integer.toString(dayOfMonth)+"-"+Integer.toString(month)+"-"+Integer.toString(year));
        for(int i=0;i<hours.size();i++){
            if(hours.get(i).equals("0")) {
                lista.get(i).setText("");
            }else{
                lista.get(i).setHint("not available");
                lista.get(i).setInputType(InputType.TYPE_NULL);
            }
        }
    }
}
