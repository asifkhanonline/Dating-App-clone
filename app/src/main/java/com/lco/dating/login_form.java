package com.lco.dating;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login_form extends AppCompatActivity {
Button btn_login,signUp;
EditText name,email,password1;
FirebaseAuth mfirebaseAuth;

    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        getSupportActionBar().setTitle("login");
        mfirebaseAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password1 = findViewById(R.id.Password);
        btn_login = findViewById(R.id.btn_login);
        signUp = findViewById(R.id.signup);
        pd = new ProgressDialog(this);

        String pass1;
        pass1 = password1.getText().toString();


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                senddata();

                if(email.getText().toString()!=null){
                    if(password1.getText().toString().length()>=7){
                        String mail=email.getText().toString();
                        String pass=password1.getText().toString();
                        login(mail,pass);
                        pd.setMessage("loading...");
                        pd.show();

                    }
                    else {
                        Toast.makeText(login_form.this,"enter password",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(login_form.this,"enter email",Toast.LENGTH_SHORT).show();

                }
            }
        });

         signUp.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 senddata();
                 //Intent intent=new Intent(login_form.this,Signup_form.class);
                // startActivity(intent);
             }
         });
    }
    private void senddata(){
        Intent i=new Intent(login_form.this,Signup_form.class);
        i.putExtra("name",name.getText().toString());
        i.putExtra("email",email.getText().toString());
        i.putExtra("password1",password1.getText().toString());
        startActivity(i);

    }
    public void login(String email,String password){
        mfirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();

                    Intent i=new Intent(login_form.this,Signup_form.class);
                    startActivity(i);
                    pd.dismiss();
                }
                else{
                    Toast.makeText(login_form.this,"log failed",Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            }
        });
    }


}
