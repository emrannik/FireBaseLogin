package com.example.firebaselogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private AppCompatButton btnSignUp;
    private FirebaseAuth firebaseAuth;
    private TextView tv_signIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         firebaseAuth=FirebaseAuth.getInstance();
         email=(EditText)findViewById(R.id.input_email);
         password=(EditText)findViewById(R.id.input_password);
         btnSignUp=(AppCompatButton) findViewById(R.id.btn_signup);
         tv_signIn=(TextView)findViewById(R.id.textView_signin);


         btnSignUp.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String emailTxt=email.getText().toString();
                 String pwd=password.getText().toString();

                 if(emailTxt.isEmpty()){
                     email.setError("Plase enter email ");
                     email.requestFocus();
                 }
                 else if(pwd.isEmpty()){
                     password.setError("Plase enter your password");
                     password.requestFocus();
                 }
                 else if(emailTxt.isEmpty() && pwd.isEmpty()){
                     Toast.makeText(MainActivity.this, "Fields are Empty", Toast.LENGTH_SHORT).show();
                 }
                 else if(!(emailTxt.isEmpty() && pwd.isEmpty())){
                     firebaseAuth.createUserWithEmailAndPassword(emailTxt,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if(!task.isSuccessful()){
                                 Toast.makeText(MainActivity.this,"SignUp unsuccesful, try again",Toast.LENGTH_LONG).show();
                             }

                             else {
                                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                             }

                         }
                     });
                 }
                 else{
                     Toast.makeText(MainActivity.this,"Error Occured",Toast.LENGTH_LONG).show();
                 }
             }
         });

         tv_signIn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                 startActivity(intent);
             }
         });


    }
}
