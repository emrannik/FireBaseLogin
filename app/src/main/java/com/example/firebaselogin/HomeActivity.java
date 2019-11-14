package com.example.firebaselogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
private Button btnLogout;
private FirebaseAuth firebaseAuth;
private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnLogout=(Button)findViewById(R.id.btn_logout) ;

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.getInstance().signOut();
                Intent intent=new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
