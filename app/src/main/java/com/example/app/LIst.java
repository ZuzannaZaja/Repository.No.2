package com.example.app;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;


public class LIst extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        setupUIViews();
        initToolbar();
        setupListView();



    }


    /*@Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());//jakbysmy chcialy gdzies wyswitlic ta date wybrana


    }*/

    private void setupUIViews(){
        toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.ToolbarMain);
        listView=(ListView)findViewById(R.id.lvMain);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("STUDIO 31");
    }

    private void setupListView(){
        String[] title = getResources().getStringArray(R.array.List);
        String[] description = getResources().getStringArray(R.array.Description);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, title, description);
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0: {
                        //Intent intent = new Intent(LIst.this, com.example.app.DatePicker.class);//source and destination
                        //startActivity(intent);
                        /*DialogFragment datePicker= new com.example.app.DatePicker();
                        datePicker.show(getSupportFragmentManager(),"date picker");*/
                        Intent intent = new Intent(LIst.this, DatePicker.class);
                        startActivity(intent);
                        break;
                    }
                    case 1:{
                        Uri url = Uri.parse("https://www.google.pl/maps/place/Wojska+Polskiego+185,+97-300+Piotrk%C3%B3w+Trybunalski/@51.4207792,19.6584067,17z/data=!3m1!4b1!4m5!3m4!1s0x471a207d03c69439:0x23a807bf3ccae274!8m2!3d51.4207759!4d19.6605954");
                        Intent intent = new Intent(Intent.ACTION_VIEW, url);
                        startActivity(intent);
                        break;}
                    case 2:{


                        break;}
                    case 3:{break;}
                    case 4:{break;}
                    case 5:{break;}

                }
            }
        });
    }

    public class SimpleAdapter extends BaseAdapter{

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title, description;
        private String[] titleArray;
        private String[] descriptionArray;
        private ImageView imageView;

        public SimpleAdapter(Context context, String[] title, String[] description){
            mContext = context;
            titleArray= title;
            descriptionArray= description;
            layoutInflater= LayoutInflater.from(context);// pozwala na rozny layout

        }

        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int position) {
            return titleArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView== null){
                convertView= layoutInflater.inflate(R.layout.main_list_single_item, null);
            }
            title= (TextView)convertView.findViewById(R.id.tvMain);
            description= (TextView)convertView.findViewById(R.id.tvDescription);
            imageView= (ImageView)convertView.findViewById(R.id.ivMain);

            title.setText(titleArray[position]);
            description.setText(descriptionArray[position]);

            if(titleArray[position].equalsIgnoreCase("Calendar")){
                imageView.setImageResource(R.drawable.calendar);
            }else if(titleArray[position].equalsIgnoreCase("Location")){
                imageView.setImageResource(R.drawable.location);
            }else if(titleArray[position].equalsIgnoreCase("Services")){
                imageView.setImageResource(R.drawable.hair1);
            }else if(titleArray[position].equalsIgnoreCase("next item")){
                imageView.setImageResource(R.drawable.book1);
            }


            return convertView;
        }

    }
    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Profile:
                Intent intentMain = getIntent();
                String name = intentMain.getStringExtra("name");
                String email = intentMain.getStringExtra("email");
                int points = intentMain.getIntExtra("points", 0);

                Intent intent = new Intent(LIst.this, HomeActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("points", points);
                startActivity(intent);
                return true;

            case R.id.LogOut:
                finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }

    }
}
