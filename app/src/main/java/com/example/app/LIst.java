package com.example.app;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;



public class LIst extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

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
                imageView.setImageResource(R.drawable.book1);
            }else if(titleArray[position].equalsIgnoreCase("Subjects")){
                imageView.setImageResource(R.drawable.book1);
            }else if(titleArray[position].equalsIgnoreCase("Faculty")){
                imageView.setImageResource(R.drawable.book1);
            }else if(titleArray[position].equalsIgnoreCase("Resources")){
                imageView.setImageResource(R.drawable.book1);
            }


            return convertView;
        }
    }


}
