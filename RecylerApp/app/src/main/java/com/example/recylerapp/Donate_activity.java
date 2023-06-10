package com.example.recylerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Donate_activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    EditText Name,Number,desc,location;
    String Name1,Number1,desc1,location1,item;
    Button donate_btn;
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        Name=findViewById(R.id.Name);
        Number=findViewById(R.id.phoneno);
        desc=findViewById(R.id.quantity);
        location=findViewById(R.id.location);
        donate_btn=findViewById(R.id.donate_btn);

       //spinner
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Waste_Types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        donate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name1,Number1,desc1,location1,item1;
                Name1=Name.getText().toString();
                Number1=Number.getText().toString();
                desc1=desc.getText().toString();
                location1=location.getText().toString();
               if(!Name1.isEmpty() && !Number1.isEmpty() && !desc1.isEmpty() && !location1.isEmpty() && !item.isEmpty())
                {
                    Users users=new Users(Name1,Number1,desc1,location1,item);
                    db=FirebaseDatabase.getInstance();
                    reference= db.getReference("Users ");
                    reference.child(Name1).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Name.setText("");
                            Number.setText("");
                            desc.setText("");
                            location.setText("");
                            Toast.makeText(Donate_activity.this, "Donation Posted !", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
         item = parent.getItemAtPosition(position).toString(); //item selected in spinner

        // Display the selected item as a Toast message
        Toast.makeText(parent.getContext(), "Selected item: " + item, Toast.LENGTH_SHORT).show();




    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}