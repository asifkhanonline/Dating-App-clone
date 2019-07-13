package com.lco.dating;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity ;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Signup_form extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    private static final String TAG="Signup_form";
    private String gen;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatabaseReference mDatabase;
    private static final int PICK_IMAGE_REQUEST=1;
    private Button file;
    private Uri mImageUri;
    EditText name,email,phone,addr,city,pass;
    Button btnsubmit;
    RadioGroup radioGroup;
    RadioButton genderbutton;
    TextView gender;
    FirebaseAuth mFirebaseAuth;


    //final String id="12345";
    //final String dob=mDisplayDate.getText().toString();
   // final String Phone=phone.getText().toString();
    //final String address=addr.getText().toString();
    //final String citya=city.getText().toString();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);
        getSupportActionBar().setTitle("Signup");
        mDatabase=FirebaseDatabase.getInstance().getReference();
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        radioGroup=findViewById(R.id.gender_group);
        //////////
        ///////////////image upload
        file=findViewById(R.id.file);

        file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });
        final String name1 = getIntent().getStringExtra("name");
        final String email1=getIntent().getStringExtra("email");
        final String password=getIntent().getStringExtra("password1");
        name.setText(name1);
        email.setText(email1);
       // pass.setText(password);


        /////////////////////radio button checeked
        radioGroup.setOnCheckedChangeListener(this);



        mDisplayDate=findViewById(R.id.date);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(Signup_form.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,year,month,day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month + 1;
                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);


            }
        };
        ///////////////////////////////////////////////////////////////////////
        mFirebaseAuth= FirebaseAuth.getInstance();

        email=findViewById(R.id.email);
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        city=findViewById(R.id.city);
        addr=findViewById(R.id.addr);
        pass=findViewById(R.id.pass);
        mDisplayDate=findViewById(R.id.date);
        btnsubmit=findViewById(R.id.btnsubmit);
        name.setEnabled(false);
        email.setEnabled(false);
        pass.setEnabled(false);






        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                {
                    String username=name.getText().toString();
                    String emailid=email.getText().toString();
                    String phoneno=phone.getText().toString();
                    String dateof=mDisplayDate.getText().toString();
                    String address=addr.getText().toString();
                    String cityM=city.getText().toString();
                    String passwordl=pass.getText().toString();
                    if(username.isEmpty()){
                        name.setError("Please enter email id");
                        name.requestFocus();
                    }
                    else if(emailid.isEmpty()){
                        email.setError("Please Enter name");
                        email.requestFocus();

                    }
                    else if(phoneno.isEmpty()){
                        phone.setError("Please Eeter phone no.");
                        phone.requestFocus();

                    }

                    else if(dateof.isEmpty()){
                        mDisplayDate.setError("Please Eeter DOB");
                        mDisplayDate.requestFocus();

                    }
                    else if(address.isEmpty()){
                        addr.setError("Please Eenter Address");
                        addr.requestFocus();

                    }
                    else if(cityM.isEmpty()){
                        city.setError("Please Eenter City");
                        city.requestFocus();

                    }
                    else if(passwordl.isEmpty()){
                        pass.setError("Please Eenter Password");
                        pass.requestFocus();

                    }


                    else if(emailid.isEmpty()&&passwordl.isEmpty()&&cityM.isEmpty()&&address.isEmpty()&&phoneno.isEmpty()&&dateof.isEmpty()&&username.isEmpty()){
                        Toast.makeText(Signup_form.this,"Feilds are empty",Toast.LENGTH_SHORT).show();
                    }
//                    else if(!(emailid.isEmpty()&&passwordl.isEmpty())){
//                        mFirebaseAuth.createUserWithEmailAndPassword(emailid,passwordl).addOnCompleteListener(Signup_form.this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if(!task.isSuccessful()){
//                                    Toast.makeText(Signup_form.this,"SignUp Unsuccessful,please try again",Toast.LENGTH_SHORT).show();
//
//                                }
//                                else{
//                                    startActivity(new Intent(Signup_form.this,MainActivity.class));
//                                    Toast.makeText(Signup_form.this,"SignUp successfull",Toast.LENGTH_SHORT).show();
//
//                                }
//                            }
//                        });
//                    }
                    else{
                        String name2=name1;
                        String email2=email1;
                        String pass=password;
                        String date=mDisplayDate.getText().toString();
                        String addres=addr.getText().toString();
                        String City=city.getText().toString();

                        writeNewUser(name2,email2,pass,date,addres,City);
                        Intent intent=new Intent(Signup_form.this,MainActivity.class);
                        startActivity(intent);
                    }
                }


            }
        });

        name.setText(name1);
        email.setText(email1);
        pass.setText(password);




    }
    private void writeNewUser(String name, String email, String phone,String date,String Addre,String City){
        String userId=mDatabase.push().getKey();
        User user =new User(name,email,phone,date,Addre,City);
        //User.child(id).setValue(user);
        mDatabase.child(userId).setValue(user);
    }
    //////////////////////////////
    ///radio male and female
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        switch (i){
            case R.id.male:
               // gender="Male";
                break;
            case R.id.female:
               // gender="Female";
                break;

        }
    }
   // private void malefemale(){
      //  int SelectedId=radioGroup.getCheckedRadioButtonId();
      //  genderbutton=findViewById(SelectedId);
       // if(SelectedId==-1){
        //    Toast.makeText(getApplicationContext(),"No choice",Toast.LENGTH_SHORT).show();
       // }
       // else{

       // }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       // if (requestCode==PICK_IMAGE_REQUEST&& resultCode==RESULT_OK&&data !=null&&data.getData()!=null){
         //   mImageUri=data.getData();
          //  Picasso.with(this).load(mImageUri).into(mImageView);
        //}
    }

    //}
    private void openFileChooser(){
        Intent intent=new Intent();
        intent.setType("img/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }
}
