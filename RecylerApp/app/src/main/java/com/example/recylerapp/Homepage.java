package com.example.recylerapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recylerapp.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Homepage extends AppCompatActivity {
    Button btn_logout;
    FirebaseAuth auth;
    FirebaseUser user;
    ListView list;
    String titles[] = {"DONATE", "RECYCLE", "YOUR DONATIONS", "CONTACT US", "ABOUT US", "LOG OUT"};
    int imgs[] = {R.drawable.trashbag_donate, R.drawable.recyling, R.drawable.yourdonations, R.drawable.contact,
            R.drawable.aboutus, R.drawable.logout};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        auth = FirebaseAuth.getInstance();
        // btn_logout=findViewById(R.id.btn_logout);
        user = auth.getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(Homepage.this, Loginactivity.class);
            startActivity(intent);
            finish();
        }


         list=findViewById(R.id.list1);

        MyAdapter adapter=new MyAdapter(this,titles,imgs);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0){
                    Intent intent1=new Intent(Homepage.this,Donate_activity.class);
                    startActivity(intent1);
                }
                if (position == 1){
                    Intent intent2=new Intent(Homepage.this,Recycle_activity.class);
                    startActivity(intent2);
                }
                if (position == 2){
                    Intent intent3=new Intent(Homepage.this,your_donation_acticity.class);
                    startActivity(intent3);
                }
                if (position == 3){
                    Intent intent4=new Intent(Homepage.this,Contact_us_activity.class);
                    startActivity(intent4);
                }
                if (position == 4){
                    Intent intent5=new Intent(Homepage.this,About_us_activity.class);
                    startActivity(intent5);
                }
                if (position == 5){
                    Toast.makeText(Homepage.this, "logging out...", Toast.LENGTH_SHORT).show();
                    FirebaseAuth.getInstance().signOut();
                    Intent intent6=new Intent(Homepage.this,Loginactivity.class);
                    startActivity(intent6);
                    finish();
                }







            }
        });


    /*  btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent1=new Intent(Homepage.this,Loginactivity.class);
                startActivity(intent1);
                finish();

            }
        }); */
    }


    class MyAdapter extends ArrayAdapter<String>{
        Context context;
        String myTitles;
        int[] imgs;

        MyAdapter(Context c,String[] titles,int[] imgs){
            super(c,R.layout.row,R.id.text1,titles);
            this.context=c;
            this.imgs=imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=layoutInflater.inflate(R.layout.row,parent,false);
            ImageView images=row.findViewById(R.id.img1);
            TextView mytitle=row.findViewById(R.id.text1);
            images.setImageResource(imgs[position]);
            mytitle.setText(titles[position]);
            return row;
        }
    }
}
