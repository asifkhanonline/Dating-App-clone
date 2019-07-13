package com.lco.dating;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main2Activity extends AppCompatActivity {
    TextView named,emaild,phoned,addrd,cityd,passd;
    TextView dated;
    Button show;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        named=findViewById(R.id.named);
        emaild=findViewById(R.id.emaild);
        phoned=findViewById(R.id.phoned);
        dated=findViewById(R.id.dated);
        addrd=findViewById(R.id.addrd);
        cityd=findViewById(R.id.cityd);
        passd=findViewById(R.id.passd);
        show=findViewById(R.id.btnsubmit);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reff= FirebaseDatabase.getInstance().getReference().child("id");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String name=dataSnapshot.child("username").getValue().toString();
                        String email=dataSnapshot.child("email").getValue().toString();
                        String date=dataSnapshot.child("dob").getValue().toString();
                        String city=dataSnapshot.child("city").getValue().toString();
                        String addr=dataSnapshot.child("address").getValue().toString();
                        String pass=dataSnapshot.child("Password").getValue().toString();
                        named.setText(name);
                        emaild.setText(email);
                        dated.setText(date);
                        cityd.setText(city);
                        addrd.setText(addr);
                        passd.setText(pass);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
